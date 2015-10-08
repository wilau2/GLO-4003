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
import ca.ulaval.glo4003.b6.housematch.user.dto.UserLoginDto;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserSignupDto;
import ca.ulaval.glo4003.b6.housematch.user.repository.XMLUserRepository;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.CouldNotAccessUserDataException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UsernameAlreadyExistsException;
import ca.ulaval.glo4003.b6.housematch.user.services.UserLoginService;
import ca.ulaval.glo4003.b6.housematch.user.services.exceptions.InvalidPasswordException;
import ca.ulaval.glo4003.b6.housematch.web.converters.SignupUserConverter;
import ca.ulaval.glo4003.b6.housematch.web.viewModel.SignupUserModel;

public class SignupControllerTest {

   @Mock
   private SignupUserConverter converter;

   @Mock
   private UserSignupCorruptionVerificator userSignupCorruptionVerificator;

   @Mock
   private UserLoginService userLoginService;

   @Mock
   private HttpSession session;

   @Mock
   private UserSignupDto userSignupDto;

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
   private UserLoginDto userLoginDto;

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
      assertEquals("signup", view);
   }

   @Test
   public void postRequestSignupRedirectToRoot()
         throws InvalidUserSignupFieldException, UserNotFoundException, CouldNotAccessUserDataException,
         InvalidPasswordException, UsernameAlreadyExistsException, InvalidContactInformationFieldException {
      // Given
      model = new BindingAwareModelMap();

      // When
      String view = controller.signup(request, userSignupViewModel);

      // Then
      assertEquals("redirect:/", view);
   }

   @Test
   public void postRequestSignupShouldUseTheConverter()
         throws InvalidUserSignupFieldException, UserNotFoundException, CouldNotAccessUserDataException,
         InvalidPasswordException, UsernameAlreadyExistsException, InvalidContactInformationFieldException {
      // Given

      // When
      controller.signup(request, userSignupViewModel);

      // Then
      verify(converter).convertViewModelToSignupDto(userSignupViewModel);
   }

   @Test
   public void postRequestShouldSignupUsingUserSignupCorruptionVerificator()
         throws InvalidUserSignupFieldException, UserNotFoundException, CouldNotAccessUserDataException,
         InvalidPasswordException, UsernameAlreadyExistsException, InvalidContactInformationFieldException {
      // Given

      // When
      controller.signup(request, userSignupViewModel);

      // Then
      verify(userSignupCorruptionVerificator).signup(userSignupDto);
   }

   @Test
   public void postRequestShouldConvertSignupDtoToLoginDtoUsingConverter()
         throws InvalidUserSignupFieldException, UserNotFoundException, CouldNotAccessUserDataException,
         InvalidPasswordException, UsernameAlreadyExistsException, InvalidContactInformationFieldException {
      // Given

      // When
      controller.signup(request, userSignupViewModel);

      // Then
      verify(converter).convertSignupDtoToLoginDto(userSignupDto);
   }

   @Test
   public void postRequestShouldLoginUsingUserLoginService()
         throws InvalidUserSignupFieldException, UserNotFoundException, CouldNotAccessUserDataException,
         InvalidPasswordException, UsernameAlreadyExistsException, InvalidContactInformationFieldException {
      // Given

      // When
      controller.signup(request, userSignupViewModel);

      // Then
      verify(userLoginService).login(request, userLoginDto);

   }

   @Test(expected = InvalidUserSignupFieldException.class)
   public void givenInvalidFieldsWhenSignupShouldThrowException()
         throws InvalidUserSignupFieldException, UserNotFoundException, CouldNotAccessUserDataException,
         InvalidPasswordException, UsernameAlreadyExistsException, InvalidContactInformationFieldException {
      // Given
      doThrow(new InvalidUserSignupFieldException(null)).when(userSignupCorruptionVerificator).signup(userSignupDto);

      // When
      controller.signup(request, userSignupViewModel);

      // Then an InvalidUserSignupFieldException is thrown
   }

   @Test(expected = UserNotFoundException.class)
   public void givenInvalidUserWhenSignupShouldThrowException()
         throws InvalidUserSignupFieldException, UserNotFoundException, CouldNotAccessUserDataException,
         InvalidPasswordException, UsernameAlreadyExistsException, InvalidContactInformationFieldException {
      // Given
      doThrow(new UserNotFoundException(null)).when(userLoginService).login(request, userLoginDto);

      // When
      controller.signup(request, userSignupViewModel);

      // Then an UserNotFoundException is thrown
   }

   @Test(expected = CouldNotAccessUserDataException.class)
   public void givenInvalidDataAccessWhenSignupShouldThrowException()
         throws InvalidUserSignupFieldException, UserNotFoundException, CouldNotAccessUserDataException,
         InvalidPasswordException, UsernameAlreadyExistsException, InvalidContactInformationFieldException {
      // Given
      doThrow(new CouldNotAccessUserDataException(null)).when(userSignupCorruptionVerificator).signup(userSignupDto);

      // When
      controller.signup(request, userSignupViewModel);

      // Then an CouldNotAccessUserDataException is thrown
   }

   @Test(expected = InvalidPasswordException.class)
   public void givenInvalidPasswordWhenSignupShouldThrowException()
         throws InvalidUserSignupFieldException, UserNotFoundException, CouldNotAccessUserDataException,
         InvalidPasswordException, UsernameAlreadyExistsException, InvalidContactInformationFieldException {
      // Given
      doThrow(new InvalidPasswordException(null)).when(userLoginService).login(request, userLoginDto);

      // When
      controller.signup(request, userSignupViewModel);

      // Then an InvalidPasswordException is thrown
   }

   @Test(expected = UsernameAlreadyExistsException.class)
   public void givenAlreadyUsedUsernameWhenSignupShouldThrowException()
         throws InvalidUserSignupFieldException, UserNotFoundException, CouldNotAccessUserDataException,
         InvalidPasswordException, UsernameAlreadyExistsException, InvalidContactInformationFieldException {
      // Given
      doThrow(new UsernameAlreadyExistsException(null)).when(userSignupCorruptionVerificator).signup(userSignupDto);

      // When
      controller.signup(request, userSignupViewModel);

      // Then an UsernameAlreadyExistsException is thrown
   }

   @Test
   public void givenValidSignupShouldNotThrowException()
         throws InvalidUserSignupFieldException, UserNotFoundException, CouldNotAccessUserDataException,
         InvalidPasswordException, UsernameAlreadyExistsException, InvalidContactInformationFieldException {
      // Given

      // When
      controller.signup(request, userSignupViewModel);

      // Then should not throw exception
   }

   private void configureConverter() {
      given(converter.convertViewModelToSignupDto(userSignupViewModel)).willReturn(userSignupDto);
      given(converter.convertSignupDtoToLoginDto(userSignupDto)).willReturn(userLoginDto);
   }

   private void configureRequest() {
      given(request.getSession()).willReturn(session);
   }
}
