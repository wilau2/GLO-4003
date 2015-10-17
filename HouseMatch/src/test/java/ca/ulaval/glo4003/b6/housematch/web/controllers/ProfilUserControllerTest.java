package ca.ulaval.glo4003.b6.housematch.web.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import ca.ulaval.glo4003.b6.housematch.anticorruption.user.UserProfilCorruptionVerificator;
import ca.ulaval.glo4003.b6.housematch.anticorruption.user.exceptions.InvalidContactInformationFieldException;
import ca.ulaval.glo4003.b6.housematch.anticorruption.user.exceptions.InvalidUserSignupFieldException;
import ca.ulaval.glo4003.b6.housematch.domain.user.exceptions.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.dto.UserDto;
import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.user.UserAuthorizationService;
import ca.ulaval.glo4003.b6.housematch.services.user.UserFetcher;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.InvalidAccessException;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.UserNotifyingException;

public class ProfilUserControllerTest {

   private static final String USERNAME = "username";

   @InjectMocks
   public ProfilUserController profilUserController;

   @Mock
   private UserAuthorizationService userAuthorizationService;

   @Mock
   private UserProfilCorruptionVerificator userProfilCorruptionVerificator;

   @Mock
   private UserFetcher userFetcher;

   @Mock
   private HttpServletRequest request;

   private String expectedRole = "buyer";

   @Mock
   private UserDto userDto;

   @Mock
   private HttpSession session;

   @Mock
   private Object username;

   @Before
   public void setup() throws InvalidAccessException, UserNotFoundException, CouldNotAccessDataException {
      MockitoAnnotations.initMocks(this);

      configureUserFetcher();
      configureRequest();
      configureSession();
   }

   private void configureSession() {
      given(session.getAttribute(UserAuthorizationService.LOGGED_IN_USERNAME)).willReturn(username);
   }

   private void configureRequest() {
      given(request.getSession()).willReturn(session);
   }

   private void configureUserFetcher() throws UserNotFoundException, CouldNotAccessDataException {
      given(userFetcher.getUserByUsername(USERNAME)).willReturn(userDto);

   }

   @Test
   public void givenValidAccessWhenGetProfilShouldDelegateAccessValidation()
         throws InvalidAccessException, UserNotFoundException, CouldNotAccessDataException {
      // Given

      // When
      profilUserController.getProfil(request);

      // Then
      verify(userAuthorizationService, times(1)).verifySessionIsAllowed(request, expectedRole);
   }

   @Test
   public void givenValidUserWhenGetProfilShouldDelegateGettingUser()
         throws InvalidAccessException, UserNotFoundException, CouldNotAccessDataException {
      // Given

      // When
      profilUserController.getProfil(request);

      // Then
      verify(userFetcher, times(1)).getUserByUsername(username.toString());
   }

   @Test
   public void givenValidAccessWhenGetProfilShouldReturnProfilView()
         throws InvalidAccessException, UserNotFoundException, CouldNotAccessDataException {
      // Given

      // When
      ModelAndView returnedViewModel = profilUserController.getProfil(request);

      // Then
      assertEquals("profil", returnedViewModel.getViewName());
   }

   @Test
   public void givenValidAccessWhenGetProfilShouldAddObjectToView()
         throws InvalidAccessException, UserNotFoundException, CouldNotAccessDataException {
      // Given

      // When
      ModelAndView returnedViewModel = profilUserController.getProfil(request);

      // Then
      assertEquals(userDto, returnedViewModel.getModel().get("user"));
   }

   @Test(expected = InvalidAccessException.class)
   public void givenInvalidAccessWhenGetProfilShouldThrowException()
         throws UserNotFoundException, CouldNotAccessDataException, InvalidAccessException {

      // Given
      doThrow(new InvalidAccessException("")).when(userAuthorizationService).verifySessionIsAllowed(request,
            expectedRole);

      // When
      profilUserController.getProfil(request);

      // Then Exception is thrown

   }

   @Test(expected = UserNotFoundException.class)
   public void givenUserDoesNotExistWhenGetProfilShouldThrowException()
         throws UserNotFoundException, CouldNotAccessDataException, InvalidAccessException {

      // Given
      doThrow(new UserNotFoundException("")).when(userFetcher).getUserByUsername(USERNAME);

      // When
      profilUserController.getProfil(request);

      // Then Exception is thrown

   }

   @Test(expected = CouldNotAccessDataException.class)
   public void givenCouldNotAccessDataWhenGetProfilShouldThrowException()
         throws UserNotFoundException, CouldNotAccessDataException, InvalidAccessException {

      // Given
      doThrow(new CouldNotAccessDataException("", null)).when(userFetcher).getUserByUsername(USERNAME);

      // When
      profilUserController.getProfil(request);

      // Then Exception is thrown

   }

   @Test
   public void givenValidViewModelWhenUpdateProfilShouldDelegateUpdate()
         throws InvalidAccessException, UserNotFoundException, CouldNotAccessDataException,
         InvalidUserSignupFieldException, InvalidContactInformationFieldException, UserNotifyingException {
      // Given

      // When
      profilUserController.updateProfil(request, userDto);

      // Then
      verify(userProfilCorruptionVerificator, times(1)).update(userDto);
   }

   @Test
   public void givenValidViewModelWhenUpdateProfilShouldDelegateAuthentification()
         throws InvalidAccessException, UserNotFoundException, CouldNotAccessDataException,
         InvalidUserSignupFieldException, InvalidContactInformationFieldException, UserNotifyingException {
      // Given

      // When
      profilUserController.updateProfil(request, userDto);

      // Then
      verify(userAuthorizationService, times(1)).verifySessionIsAllowed(request, expectedRole);;
   }

   @Test
   public void givenValidViewModelWhenUpdateProfilShouldRedirectToProfil()
         throws InvalidAccessException, UserNotFoundException, CouldNotAccessDataException,
         InvalidUserSignupFieldException, InvalidContactInformationFieldException, UserNotifyingException {
      // Given

      // When
      String resp = profilUserController.updateProfil(request, userDto);

      // Then
      assertEquals("redirect:/profil", resp);
   }

   @Test(expected = InvalidAccessException.class)
   public void givenInvalidAccessWhenUpdateProfilShouldThrowException()
         throws UserNotFoundException, CouldNotAccessDataException, InvalidAccessException,
         InvalidUserSignupFieldException, InvalidContactInformationFieldException, UserNotifyingException {

      // Given
      doThrow(new InvalidAccessException("")).when(userAuthorizationService).verifySessionIsAllowed(request,
            expectedRole);

      // When
      profilUserController.updateProfil(request, userDto);

      // Then Exception is thrown
   }

}
