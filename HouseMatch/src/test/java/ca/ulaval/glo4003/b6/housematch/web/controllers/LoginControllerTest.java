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

import ca.ulaval.glo4003.b6.housematch.user.anticorruption.UserLoginCorruptionVerificator;
import ca.ulaval.glo4003.b6.housematch.user.anticorruption.exceptions.InvalidUserLoginFieldException;
import ca.ulaval.glo4003.b6.housematch.user.domain.User;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserLoginDto;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.CouldNotAccessUserDataException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.user.services.exceptions.InvalidPasswordException;
import ca.ulaval.glo4003.b6.housematch.web.converters.LoginUserConverter;
import ca.ulaval.glo4003.b6.housematch.web.viewModel.LoginUserViewModel;

public class LoginControllerTest {

   @InjectMocks
   public LoginController controller;

   @Mock
   private UserLoginCorruptionVerificator userCorruptionVerificatior;

   @Mock
   private HttpSession session;

   @Mock
   private UserLoginDto userDto;

   @Mock
   private User user;

   @Mock
   private LoginUserViewModel loginUserViewModel;

   @Mock
   private LoginUserConverter converter;

   @Mock
   private HttpServletRequest request;

   private BindingAwareModelMap model;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);
      configureConverter();
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
         CouldNotAccessUserDataException, InvalidPasswordException {

      // Given
      model = new BindingAwareModelMap();

      // When

      String view = controller.login(request, loginUserViewModel);

      // Then
      assertEquals("redirect:/", view);
   }

   @Test
   public void postRequestLoginShouldUseTheConverter() throws InvalidUserLoginFieldException, UserNotFoundException,
         CouldNotAccessUserDataException, InvalidPasswordException {

      // When

      controller.login(request, loginUserViewModel);

      // Then
      verify(converter).convertViewModelToDto(loginUserViewModel);
   }

   @Test

   public void postRequestLoginShouldUseTheUserCorruptionVerificator() throws InvalidUserLoginFieldException,
         UserNotFoundException, CouldNotAccessUserDataException, InvalidPasswordException {

      // When
      controller.login(request, loginUserViewModel);

      // Then
      verify(userCorruptionVerificatior).login(request, userDto);
   }

   @Test(expected = InvalidUserLoginFieldException.class)
   public void givenInvalidFieldOnUserLoginViewModelPostRequestLogingShouldThrowException()
         throws InvalidUserLoginFieldException, UserNotFoundException, CouldNotAccessUserDataException,
         InvalidPasswordException {

      doThrow(new InvalidUserLoginFieldException(null)).when(userCorruptionVerificatior).login(request, userDto);
      // When
      controller.login(request, loginUserViewModel);

      // Then an InvalidUserLoginFieldException is thrown
   }

   @Test(expected = UserNotFoundException.class)
   public void givenNotExistingUserPostRequestLogingShouldThrowException() throws InvalidUserLoginFieldException,
         UserNotFoundException, CouldNotAccessUserDataException, InvalidPasswordException {

      doThrow(new UserNotFoundException(null)).when(userCorruptionVerificatior).login(request, userDto);
      // When
      controller.login(request, loginUserViewModel);

      // Then an UserNotFoundException is thrown
   }

   @Test(expected = InvalidPasswordException.class)
   public void givenInvalidPasswordPostRequestLogingShouldThrowException() throws InvalidUserLoginFieldException,
         UserNotFoundException, CouldNotAccessUserDataException, InvalidPasswordException {

      doThrow(new InvalidPasswordException(null)).when(userCorruptionVerificatior).login(request, userDto);
      // When
      controller.login(request, loginUserViewModel);

      // Then an InvalidPasswordException is thrown
   }

   @Test(expected = CouldNotAccessUserDataException.class)
   public void givenNoAccessToDataPostRequestLogingShouldThrowException() throws InvalidUserLoginFieldException,
         UserNotFoundException, CouldNotAccessUserDataException, InvalidPasswordException {

      doThrow(new CouldNotAccessUserDataException(null)).when(userCorruptionVerificatior).login(request, userDto);
      // When
      controller.login(request, loginUserViewModel);

      // Then an CouldNotAccessUserDataException is thrown
   }

   private void configureConverter() {
      given(converter.convertViewModelToDto(loginUserViewModel)).willReturn(userDto);
   }

   private void configureRequest() {
      given(request.getSession()).willReturn(session);
   }

}
