package ca.ulaval.glo4003.b6.housematch.web.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
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
import ca.ulaval.glo4003.b6.housematch.user.anticorruption.exceptions.InvalidUserSignupFieldException;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserLoginDto;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserSignupDto;
import ca.ulaval.glo4003.b6.housematch.user.repository.XMLUserRepository;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.CouldNotAccessUserDataException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserAlreadyExistsException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.user.services.InvalidPasswordException;
import ca.ulaval.glo4003.b6.housematch.user.services.UserLoginService;
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
   private SignupUserModel signupNewUser;

   @Mock
   private XMLUserRepository userRepository;

   @Mock
   private HttpServletRequest request;

   @InjectMocks
   public SignupController controller;

   private BindingAwareModelMap model;

   @Mock
   private UserLoginDto userLoginDto;

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
   public void postRequestSignupReturnsTheIndexView() throws InvalidUserSignupFieldException, UserNotFoundException,
         CouldNotAccessUserDataException, InvalidPasswordException, UserAlreadyExistsException {
      model = new BindingAwareModelMap();

      String view = controller.signup(request, signupNewUser);
      assertEquals("redirect:/", view);
   }

   @Test
   public void postRequestSignupShouldUseTheConverter() throws InvalidUserSignupFieldException, UserNotFoundException,
         CouldNotAccessUserDataException, InvalidPasswordException, UserAlreadyExistsException {
      controller.signup(request, signupNewUser);

      verify(converter).convertViewModelToSignupDto(signupNewUser);
   }

   @Test
   public void postRequestSignupShouldUseTheRepository() throws InvalidUserSignupFieldException, UserNotFoundException,
         CouldNotAccessUserDataException, InvalidPasswordException, UserAlreadyExistsException {
      controller.signup(request, signupNewUser);

      verify(userRepository).add(userSignupDto);
   }

   @Test
   public void postRequestSignupShouldSetALoggedUser() throws InvalidUserSignupFieldException, UserNotFoundException,
         CouldNotAccessUserDataException, InvalidPasswordException, UserAlreadyExistsException {
      controller.signup(request, signupNewUser);

       assertEquals(signupNewUser.getUsername(),
       request.getAttribute("loggedInUser"));
   }

   private void configureConverter() {
      given(converter.convertViewModelToSignupDto(signupNewUser)).willReturn(userSignupDto);
      given(converter.convertSignupDtoToLoginDto(userSignupDto)).willReturn(userLoginDto);
   }

   private void configureRequest() {
      given(request.getSession()).willReturn(session);
   }
}
