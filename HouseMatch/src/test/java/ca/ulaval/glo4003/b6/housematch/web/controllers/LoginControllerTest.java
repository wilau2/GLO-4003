package ca.ulaval.glo4003.b6.housematch.web.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.support.BindingAwareModelMap;

import ca.ulaval.glo4003.b6.housematch.anticorruption.user.UserLoginCorruptionVerificator;
import ca.ulaval.glo4003.b6.housematch.anticorruption.user.exceptions.InvalidUserLoginFieldException;
import ca.ulaval.glo4003.b6.housematch.domain.user.User;
import ca.ulaval.glo4003.b6.housematch.domain.user.exceptions.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.dto.UserDto;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.InvalidPasswordException;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.UserActivationException;

public class LoginControllerTest {

   @InjectMocks
   public LoginController controller;

   @Mock
   private UserLoginCorruptionVerificator userCorruptionVerificatior;

   @Mock
   private HttpSession session;

   @Mock
   private UserDto userDto;

   @Mock
   private User user;

   @Mock
   private HttpServletRequest request;

   private BindingAwareModelMap model;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);

      configureRequest();
   }

   @Test
   public void getRequestLoginReturnsTheLoginView() {
      // Given
      model = new BindingAwareModelMap();

      // When
      String view = controller.login(model);

      // Then
      assertEquals("login", view);
   }

   @Test
   public void postRequestLoginReturnsRootRedirection() throws InvalidUserLoginFieldException, UserNotFoundException,
         CouldNotAccessDataException, InvalidPasswordException, UserActivationException {

      // Given
      model = new BindingAwareModelMap();

      // When

      String view = controller.login(request, userDto);

      // Then
      assertEquals("redirect:/", view);
   }

   @Test
   public void postRequestLoginShouldUseTheUserCorruptionVerificator() throws InvalidUserLoginFieldException,
         UserNotFoundException, CouldNotAccessDataException, InvalidPasswordException, UserActivationException {
      // Given no changes

      // When
      controller.login(request, userDto);

      // Then
      verify(userCorruptionVerificatior).login(request, userDto);
   }

   @Test(expected = InvalidUserLoginFieldException.class)
   public void givenInvalidFieldOnUserLoginViewModelPostRequestLogingShouldThrowException()
         throws InvalidUserLoginFieldException, UserNotFoundException, CouldNotAccessDataException,
         InvalidPasswordException, UserActivationException {
      // Given
      doThrow(new InvalidUserLoginFieldException(null)).when(userCorruptionVerificatior).login(request, userDto);

      // When
      controller.login(request, userDto);

      // Then an InvalidUserLoginFieldException is thrown
   }

   @Test(expected = UserNotFoundException.class)
   public void givenNotExistingUserPostRequestLogingShouldThrowException() throws InvalidUserLoginFieldException,
         UserNotFoundException, CouldNotAccessDataException, InvalidPasswordException, UserActivationException {
      // given
      doThrow(new UserNotFoundException(null)).when(userCorruptionVerificatior).login(request, userDto);

      // When
      controller.login(request, userDto);

      // Then an UserNotFoundException is thrown
   }

   @Test(expected = InvalidPasswordException.class)
   public void givenInvalidPasswordPostRequestLogingShouldThrowException() throws InvalidUserLoginFieldException,
         UserNotFoundException, CouldNotAccessDataException, InvalidPasswordException, UserActivationException {

      doThrow(new InvalidPasswordException(null)).when(userCorruptionVerificatior).login(request, userDto);
      // When
      controller.login(request, userDto);

      // Then an InvalidPasswordException is thrown
   }

   @Test(expected = CouldNotAccessDataException.class)
   public void givenNoAccessToDataPostRequestLogingShouldThrowException() throws InvalidUserLoginFieldException,
         UserNotFoundException, CouldNotAccessDataException, InvalidPasswordException, UserActivationException {

      doThrow(new CouldNotAccessDataException(null, null)).when(userCorruptionVerificatior).login(request, userDto);
      // When
      controller.login(request, userDto);

      // Then an CouldNotAccessUserDataException is thrown
   }

   private void configureRequest() {
      given(request.getSession()).willReturn(session);
   }

}
