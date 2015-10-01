package ca.ulaval.glo4003.b6.housematch.user.services;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.user.domain.Role;
import ca.ulaval.glo4003.b6.housematch.user.domain.User;

public class UserAuthorizationServiceTest {

   @Mock
   User user;

   @Mock
   HttpServletRequest request;

   @InjectMocks
   UserAuthorizationService userAuthorizationService;

   @Mock
   private HttpSession session;

   private static String USERNAME = "username";

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);
      configureUser();
      configureRequest();
   }

   @Test
   public void givenSellerUserWhenSetSessionAuthorisationShouldAddSellerToSessionRoles() {
      // Given
      given(user.isSeller()).willReturn(true);
      // When
      userAuthorizationService.setSessionUserAuthorisation(request, user);

      // Then
      verify(session).setAttribute(UserAuthorizationService.LOGGED_IN_USER_ROLE, Role.SELLER);
   }

   @Test
   public void givenAdminUserWhenSetSessionAuthorisationShouldAddAdminToSessionRoles() {
      // Given
      given(user.isAdmin()).willReturn(true);
      // When
      userAuthorizationService.setSessionUserAuthorisation(request, user);

      // Then
      verify(session).setAttribute(UserAuthorizationService.LOGGED_IN_USER_ROLE, Role.ADMIN);
   }

   @Test
   public void givenBuyerUserWhenSetSessionAuthorisationShouldAddBuyerToSessionRoles() {
      // Given
      given(user.isBuyer()).willReturn(true);
      // When
      userAuthorizationService.setSessionUserAuthorisation(request, user);

      // Then
      verify(session).setAttribute(UserAuthorizationService.LOGGED_IN_USER_ROLE, Role.BUYER);
   }

   @Test
   public void givenAnyRolesWhenSetSessionAuthorisationShouldSetUsername() {
      // Given

      // When
      userAuthorizationService.setSessionUserAuthorisation(request, user);

      // Then
      verify(session).setAttribute(UserAuthorizationService.LOGGED_IN_USERNAME, USERNAME);
   }

   @Test
   public void givenValidRequestWhenCloseSessionShouldResterUsername() {
      // Given

      // When
      userAuthorizationService.closeSession(request);

      // Then
      verify(session).setAttribute(UserAuthorizationService.LOGGED_IN_USERNAME, null);
   }

   @Test
   public void givenValidRequestWhenCloseSessionShouldResterRole() {
      // Given

      // When
      userAuthorizationService.closeSession(request);

      // Then
      verify(session).setAttribute(UserAuthorizationService.LOGGED_IN_USER_ROLE, null);
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
