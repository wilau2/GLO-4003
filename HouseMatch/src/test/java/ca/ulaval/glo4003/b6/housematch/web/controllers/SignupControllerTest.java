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

import ca.ulaval.glo4003.b6.housematch.anticorruption.user.UserSignupCorruptionVerificator;
import ca.ulaval.glo4003.b6.housematch.anticorruption.user.exceptions.InvalidContactInformationFieldException;
import ca.ulaval.glo4003.b6.housematch.anticorruption.user.exceptions.InvalidUserSignupFieldException;
import ca.ulaval.glo4003.b6.housematch.domain.user.User;
import ca.ulaval.glo4003.b6.housematch.domain.user.exceptions.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.domain.user.exceptions.UsernameAlreadyExistsException;
import ca.ulaval.glo4003.b6.housematch.dto.UserDto;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.persistence.user.XMLUserRepository;
import ca.ulaval.glo4003.b6.housematch.services.user.UserLoginService;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.InvalidPasswordException;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.UserActivationException;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.UserNotifyingException;

public class SignupControllerTest {

   private static final String NEED_EMAIL_CONFIRMATION = "redirect:/confirmation";

   private static final String SIGNUP = "signup";

   @Mock
   private UserSignupCorruptionVerificator userSignupCorruptionVerificator;

   @Mock
   private HttpSession session;

   @Mock
   private UserDto userDto;

   @Mock
   private UserLoginService userLoginService;

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
         CouldNotAccessDataException, InvalidPasswordException, UsernameAlreadyExistsException,
         InvalidContactInformationFieldException, UserNotifyingException, UserActivationException {
      // Given
      model = new BindingAwareModelMap();

      // When
      String view = controller.signup(request, userDto);

      // Then
      assertEquals(NEED_EMAIL_CONFIRMATION, view);
   }

   @Test
   public void postRequestShouldSignupUsingUserSignupCorruptionVerificator() throws InvalidUserSignupFieldException,
         UserNotFoundException, CouldNotAccessDataException, InvalidPasswordException, UsernameAlreadyExistsException,
         InvalidContactInformationFieldException, UserNotifyingException, UserActivationException {
      // Given

      // When
      controller.signup(request, userDto);

      // Then

      verify(userSignupCorruptionVerificator).validateSignup(userDto);
   }

   @Test(expected = InvalidUserSignupFieldException.class)
   public void givenInvalidFieldsWhenSignupShouldThrowException() throws InvalidUserSignupFieldException,
         UserNotFoundException, CouldNotAccessDataException, InvalidPasswordException, UsernameAlreadyExistsException,
         InvalidContactInformationFieldException, UserNotifyingException, UserActivationException {
      // Given
      doThrow(new InvalidUserSignupFieldException(null)).when(userSignupCorruptionVerificator).validateSignup(userDto);

      // When
      controller.signup(request, userDto);

      // Then an InvalidUserSignupFieldException is thrown
   }

   @Test(expected = CouldNotAccessDataException.class)

   public void givenInvalidDataAccessWhenSignupShouldThrowException() throws InvalidUserSignupFieldException,
         UserNotFoundException, CouldNotAccessDataException, InvalidPasswordException, UsernameAlreadyExistsException,
         InvalidContactInformationFieldException, UserNotifyingException, UserActivationException {
      // Given
      doThrow(new CouldNotAccessDataException(null, null)).when(userSignupCorruptionVerificator).validateSignup(userDto);

      // When
      controller.signup(request, userDto);

      // Then an CouldNotAccessUserDataException is thrown
   }

   @Test(expected = UsernameAlreadyExistsException.class)

   public void givenAlreadyUsedUsernameWhenSignupShouldThrowException() throws InvalidUserSignupFieldException,
         UserNotFoundException, CouldNotAccessDataException, InvalidPasswordException, UsernameAlreadyExistsException,
         InvalidContactInformationFieldException, UserNotifyingException, UserActivationException {
      // Given
      doThrow(new UsernameAlreadyExistsException(null)).when(userSignupCorruptionVerificator).validateSignup(userDto);

      // When
      controller.signup(request, userDto);

      // Then an UsernameAlreadyExistsException is thrown
   }

   @Test
   public void givenValidSignupShouldNotThrowException() throws InvalidUserSignupFieldException, UserNotFoundException,
         CouldNotAccessDataException, InvalidPasswordException, UsernameAlreadyExistsException,
         InvalidContactInformationFieldException, UserNotifyingException, UserActivationException {
      // Given

      // When
      controller.signup(request, userDto);

      // Then should not throw exception
   }

   private void configureRequest() {
      given(request.getSession()).willReturn(session);
   }
}
