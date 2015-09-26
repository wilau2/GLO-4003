package ca.ulaval.glo4003.b6.housematch.web.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.support.BindingAwareModelMap;

import ca.ulaval.glo4003.b6.housematch.user.anticorruption.exceptions.InvalidUserSignupFieldException;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserSignupDto;
import ca.ulaval.glo4003.b6.housematch.user.repository.XMLUserRepository;
import ca.ulaval.glo4003.b6.housematch.web.converters.SignupUserConverter;
import ca.ulaval.glo4003.b6.housematch.web.viewModel.SignupUserModel;

public class SignupControllerTest {

   @Mock
   private HttpSession session;

   @Mock
   private UserSignupDto userSignupDto;

   @Mock
   private SignupUserModel signupNewUser;

   @Mock
   private XMLUserRepository userRepository;

   @Mock
   private SignupUserConverter converter;

   @Mock
   private HttpServletRequest request;

   @InjectMocks
   public SignupController controller;

   private BindingAwareModelMap model;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);
      configureConverter();
      configureRequest();
   }

   @Test
   public void getRequestSignupReturnsTheSignupView() {
      model = new BindingAwareModelMap();
      String view = controller.signup(model);

      assertEquals("signup", view);
   }

   @Test
   public void postRequestSignupReturnsTheIndexView() throws InvalidUserSignupFieldException {
      model = new BindingAwareModelMap();
      // String view = controller.signup(request, signupNewUser);
      // assertEquals("index", view);
   }

   @Test
   public void postRequestSignupShouldUseTheConverter() throws InvalidUserSignupFieldException {
      // controller.signup(request, signupNewUser);

      // verify(converter).convertToDto(signupNewUser);
   }

   @Ignore
   @Test
   public void postRequestSignupShouldUseTheRepository() throws InvalidUserSignupFieldException {
      controller.signup(request, signupNewUser);

      // verify(userRepository).add(userSignupDto);
   }

   @Test
   public void postRequestSignupShouldSetALoggedUser() throws InvalidUserSignupFieldException {
      // controller.signup(request, signupNewUser);

      // assertEquals(signupNewUser.getUsername(),
      // request.getAttribute("loggedInUser"));
   }

   private void configureConverter() {
      given(converter.convertToDto(signupNewUser)).willReturn(userSignupDto);
   }

   private void configureRequest() {
      given(request.getSession()).willReturn(session);
   }
}
