package ca.ulaval.glo4003.b7.housematch.web.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.support.BindingAwareModelMap;

import ca.ulaval.glo4003.b7.housematch.user.model.User;
import ca.ulaval.glo4003.b7.housematch.user.repository.XMLUserRepository;
import ca.ulaval.glo4003.b7.housematch.web.converters.SignupUserConverter;
import ca.ulaval.glo4003.b7.housematch.web.viewModel.SignupUserModel;

public class SignupControllerTest {

  @Mock
  private User user;

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
  }

  @Test
  public void getRequestSignupReturnsTheSignupView() {
    model = new BindingAwareModelMap();
    String view = controller.signup(model);

    assertEquals("signup", view);
  }

  @Test
  public void postRequestSignupReturnsTheIndexView() {
    model = new BindingAwareModelMap();
    String view = controller.signup(request, signupNewUser);

    assertEquals("index", view);
  }

  @Test
  public void postRequestSignupShouldUseTheConverter() {
    controller.signup(request, signupNewUser);

    verify(converter).convert(signupNewUser);
  }

  @Test
  public void postRequestSignupShouldUseTheRepository() {
    controller.signup(request, signupNewUser);

    verify(userRepository).add(user);
  }

  @Test
  public void postRequestSignupShouldSetALoggedUser() {
    controller.signup(request, signupNewUser);

    assertEquals(signupNewUser.email, request.getAttribute("loggedInUser"));
  }

  private void configureConverter() {
    given(converter.convert(signupNewUser)).willReturn(user);
  }
}
