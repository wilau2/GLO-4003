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

import ca.ulaval.glo4003.b6.housematch.user.anticorruption.UserSignupCorruptionVerificator;
import ca.ulaval.glo4003.b6.housematch.user.anticorruption.exceptions.InvalidContactInformationFieldException;
import ca.ulaval.glo4003.b6.housematch.user.anticorruption.exceptions.InvalidUserSignupFieldException;
import ca.ulaval.glo4003.b6.housematch.user.domain.User;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserDto;
import ca.ulaval.glo4003.b6.housematch.user.repository.XMLUserRepository;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.CouldNotAccessUserDataException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UsernameAlreadyExistsException;
import ca.ulaval.glo4003.b6.housematch.user.services.UserLoginService;
import ca.ulaval.glo4003.b6.housematch.user.services.exceptions.InvalidPasswordException;
import ca.ulaval.glo4003.b6.housematch.user.services.exceptions.UserActivationException;
import ca.ulaval.glo4003.b6.housematch.user.services.exceptions.UserNotifyingException;
import ca.ulaval.glo4003.b6.housematch.web.converters.SignupUserConverter;
import ca.ulaval.glo4003.b6.housematch.web.viewModel.SignupUserModel;

public class SignupControllerTest {

   private static final String NEED_EMAIL_CONFIRMATION = "need_email_confirmation";

   private static final String SIGNUP = "signup";

   @Mock
   private SignupUserConverter converter;

   @Mock
   private UserSignupCorruptionVerificator userSignupCorruptionVerificator;

   @Mock
   private HttpSession session;

   @Mock
   private UserDto userDto;

   @Mock
   private UserLoginService userLoginService;

   @Mock
   private SignupUserModel userSignupViewModel;

   @Mock
   private XMLUserRepository userRepository;

   @Mock
   private HttpServletRequest request;

   @InjectMocks
   private SignupController controller;

   private BindingAwareModelMap model;

   @Mock
   private User user;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);
      configureConverter();
      configureRequest();
   }

   @Test
   public void getRequestSignupReturnsTheSignupView() {
      // Given
      model = new BindingAwareModelMap();

      // When
      String view = controller.signup(model);

      // Then
      assertEquals(SIGNUP, view);
   }

   @Test
   public void postRequestSignupRedirectToRoot() throws InvalidUserSignupFieldException, UserNotFoundException,
         CouldNotAccessUserDataException, InvalidPasswordException, UsernameAlreadyExistsException,
         InvalidContactInformationFieldException, UserNotifyingException, UserActivationException {
      // Given
      model = new BindingAwareModelMap();

      // When
      String view = controller.signup(request, userSignupViewModel);

      // Then
      assertEquals(NEED_EMAIL_CONFIRMATION, view);
   }

   @Test
   public void postRequestSignupShouldUseTheConverter() throws InvalidUserSignupFieldException, UserNotFoundException,
         CouldNotAccessUserDataException, InvalidPasswordException, UsernameAlreadyExistsException,
         InvalidContactInformationFieldException, UserNotifyingException, UserActivationException {
      // Given

      // When
      controller.signup(request, userSignupViewModel);

      // Then
      verify(converter).convertViewModelToSignupDto(userSignupViewModel);
   }

   @Test
   public void postRequestShouldSignupUsingUserSignupCorruptionVerificator()
         throws InvalidUserSignupFieldException, UserNotFoundException, CouldNotAccessUserDataException,
         InvalidPasswordException, UsernameAlreadyExistsException, InvalidContactInformationFieldException,
         UserNotifyingException, UserActivationException {
      // Given

      // When
      controller.signup(request, userSignupViewModel);

      // Then

      verify(userSignupCorruptionVerificator).signup(userDto);
   }

   @Test(expected = InvalidUserSignupFieldException.class)
   public void givenInvalidFieldsWhenSignupShouldThrowException()
         throws InvalidUserSignupFieldException, UserNotFoundException, CouldNotAccessUserDataException,
         InvalidPasswordException, UsernameAlreadyExistsException, InvalidContactInformationFieldException,
         UserNotifyingException, UserActivationException {
      // Given
      doThrow(new InvalidUserSignupFieldException(null)).when(userSignupCorruptionVerificator).signup(userDto);

      // When
      controller.signup(request, userSignupViewModel);

      // Then an InvalidUserSignupFieldException is thrown
   }

   @Test(expected = CouldNotAccessUserDataException.class)

   public void givenInvalidDataAccessWhenSignupShouldThrowException()
         throws InvalidUserSignupFieldException, UserNotFoundException, CouldNotAccessUserDataException,
         InvalidPasswordException, UsernameAlreadyExistsException, InvalidContactInformationFieldException,
         UserNotifyingException, UserActivationException {
      // Given
      doThrow(new CouldNotAccessUserDataException(null)).when(userSignupCorruptionVerificator).signup(userDto);

      // When
      controller.signup(request, userSignupViewModel);

      // Then an CouldNotAccessUserDataException is thrown
   }

   @Test(expected = UsernameAlreadyExistsException.class)

   public void givenAlreadyUsedUsernameWhenSignupShouldThrowException()
         throws InvalidUserSignupFieldException, UserNotFoundException, CouldNotAccessUserDataException,
         InvalidPasswordException, UsernameAlreadyExistsException, InvalidContactInformationFieldException,
         UserNotifyingException, UserActivationException {
      // Given
      doThrow(new UsernameAlreadyExistsException(null)).when(userSignupCorruptionVerificator).signup(userDto);

      // When
      controller.signup(request, userSignupViewModel);

      // Then an UsernameAlreadyExistsException is thrown
   }

   @Test
   public void givenValidSignupShouldNotThrowException() throws InvalidUserSignupFieldException, UserNotFoundException,
         CouldNotAccessUserDataException, InvalidPasswordException, UsernameAlreadyExistsException,
         InvalidContactInformationFieldException, UserNotifyingException, UserActivationException {
      // Given

      // When
      controller.signup(request, userSignupViewModel);

      // Then should not throw exception
   }

   private void configureConverter() {
      given(converter.convertViewModelToSignupDto(userSignupViewModel)).willReturn(userDto);
   }

   private void configureRequest() {
      given(request.getSession()).willReturn(session);
   }
}
