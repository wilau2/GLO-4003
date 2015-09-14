package org.ulaval.teamb6.housematch2.web.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.support.BindingAwareModelMap;
import org.ulaval.teamb6.housematch2.domain.User;
import org.ulaval.teamb6.housematch2.repository.InMemoryUserRepository;
import org.ulaval.teamb6.housematch2.web.converters.SignupUserConverter;
import org.ulaval.teamb6.housematch2.web.viewModel.SignupUserModel;

public class SignupControllerTest {

  @Mock
  private User user;

  @Mock
  private SignupUserModel signupNewUser;

  @Mock
  private InMemoryUserRepository userRepository;

  @Mock
  private SignupUserConverter converter;

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
    String view = controller.signup(signupNewUser);

    assertEquals("index", view);
  }

  @Test
  public void postRequestSignupShouldUseTheConverter() {
    controller.signup(signupNewUser);

    verify(converter).convert(signupNewUser);
  }

  @Test
  public void postRequestSignupShouldUseTheRepository() {
    controller.signup(signupNewUser);

    verify(userRepository).add(user);
  }

  private void configureConverter() {
    given(converter.convert(signupNewUser)).willReturn(user);
  }
}
