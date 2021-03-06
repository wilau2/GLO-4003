package ca.ulaval.glo4003.b6.housematch.services.user;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.domain.user.Role;
import ca.ulaval.glo4003.b6.housematch.domain.user.User;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.InvalidAccessException;

public class UserSessionAuthorizationServiceTest {

   private static final String EXPECTED_BUYER_ROLE = "buyer";

   private static final String EXPECTED_SELLER_ROLE = "seller";

   private static final String USERNAME = "username";

   @Mock
   User user;

   @Mock
   HttpServletRequest request;

   @InjectMocks
   UserSessionAuthorizationService userAuthorizationService;

   @Mock
   private HttpSession session;

   @Mock
   private Object roleObject;

   private List<String> listOfExpectedRoles;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);

      configureUser();
      configureRequest();

      listOfExpectedRoles = new ArrayList<String>();
   }

   @Test
   public void givenSellerUserWhenSetSessionAuthorisationShouldAddSellerToSessionRoles() {
      // Given
      given(user.isSeller()).willReturn(true);
      // When
      userAuthorizationService.setSessionUserAuthorisation(request, user);

      // Then
      verify(session).setAttribute(UserSessionAuthorizationService.LOGGED_IN_USER_ROLE, Role.SELLER);
   }

   @Test
   public void givenAdminUserWhenSetSessionAuthorisationShouldAddAdminToSessionRoles() {
      // Given
      given(user.isAdmin()).willReturn(true);
      // When
      userAuthorizationService.setSessionUserAuthorisation(request, user);

      // Then
      verify(session).setAttribute(UserSessionAuthorizationService.LOGGED_IN_USER_ROLE, Role.ADMIN);
   }

   @Test
   public void givenBuyerUserWhenSetSessionAuthorisationShouldAddBuyerToSessionRoles() {
      // Given
      given(user.isBuyer()).willReturn(true);
      // When
      userAuthorizationService.setSessionUserAuthorisation(request, user);

      // Then
      verify(session).setAttribute(UserSessionAuthorizationService.LOGGED_IN_USER_ROLE, Role.BUYER);
   }

   @Test
   public void givenAnyRolesWhenSetSessionAuthorisationShouldSetUsername() {
      // Given

      // When
      userAuthorizationService.setSessionUserAuthorisation(request, user);

      // Then
      verify(session).setAttribute(UserSessionAuthorizationService.LOGGED_IN_USERNAME, USERNAME);
   }

   @Test
   public void givenValidRequestWhenCloseSessionShouldResterUsername() {
      // Given

      // When
      userAuthorizationService.closeSession(request);

      // Then
      verify(session).setAttribute(UserSessionAuthorizationService.LOGGED_IN_USERNAME, null);
   }

   @Test
   public void givenValidRequestWhenCloseSessionShouldResterRole() {
      // Given

      // When
      userAuthorizationService.closeSession(request);

      // Then
      verify(session).setAttribute(UserSessionAuthorizationService.LOGGED_IN_USER_ROLE, null);
   }

   @Test
   public void givenExpectedRoleSameAsSessionRoleWhenIsSessionAllowedThenReturnTrue() throws InvalidAccessException {

      // Given
      configureBuyerSession();

      // When
      userAuthorizationService.verifySessionIsAllowed(request, EXPECTED_BUYER_ROLE);

      // Then no exception is thrown

   }

   @Test(expected = InvalidAccessException.class)
   public void givenExpectedRoleDifferentThanSessionRoleWhenIsSessionAllowedThenShouldThrowException()
         throws InvalidAccessException {

      // Given
      configureBuyerSession();

      // When
      userAuthorizationService.verifySessionIsAllowed(request, EXPECTED_SELLER_ROLE);

      // Then an InvalidAccessException is thrown

   }

   @Test
   public void givenExpectedRolesDifferentContainedInSessionRoleWhenIsSessionAllowedThenShouldNotThrowException()
         throws InvalidAccessException {
      // Given
      configureBuyerSession();
      listOfExpectedRoles.add(EXPECTED_BUYER_ROLE);

      // When
      userAuthorizationService.verifySessionIsAllowed(request, listOfExpectedRoles);

      // Then no exception is thrown
   }

   @Test(expected = InvalidAccessException.class)
   public void verifingIfSessionContainsRoleInExpectedRolesWhenRoleInSessionNotInListShouldThrowException()
         throws InvalidAccessException {
      // Given
      listOfExpectedRoles.add(EXPECTED_BUYER_ROLE);
      listOfExpectedRoles.add(EXPECTED_SELLER_ROLE);
      configureAdminRole();

      // When
      userAuthorizationService.verifySessionIsAllowed(request, listOfExpectedRoles);

      // Then an invalid access exception is thrown
   }

   @Test
   public void verifyingIfUserIsLoggedWhenUserIsNotLoggedShouldReturnFalse() {
      // Given
      when(session.getAttribute(UserSessionAuthorizationService.LOGGED_IN_USERNAME)).thenReturn(null);

      // When
      boolean userLogged = userAuthorizationService.isUserLogged(request);

      // Then
      assertFalse(userLogged);

   }

   @Test
   public void verifyingIfUserIsLoggedWhenUserIsLoggedShouldReturnTrue() {
      // Given
      when(session.getAttribute(UserSessionAuthorizationService.LOGGED_IN_USERNAME)).thenReturn(USERNAME);

      // When
      boolean userLogged = userAuthorizationService.isUserLogged(request);

      // Then
      assertTrue(userLogged);
   }

   private void configureAdminRole() {
      given(session.getAttribute(UserSessionAuthorizationService.LOGGED_IN_USER_ROLE)).willReturn(roleObject);
      given(roleObject.toString()).willReturn("admin");

   }

   private void configureBuyerSession() {
      given(session.getAttribute(UserSessionAuthorizationService.LOGGED_IN_USER_ROLE)).willReturn(roleObject);
      given(roleObject.toString()).willReturn("buyer");

   }

   private void configureUser() {
      given(user.isAdmin()).willReturn(false);
      given(user.isSeller()).willReturn(false);
      given(user.isBuyer()).willReturn(false);
      given(user.getUsername()).willReturn(USERNAME);
   }

   private void configureRequest() {
      given(request.getSession()).willReturn(session);

   }

}
