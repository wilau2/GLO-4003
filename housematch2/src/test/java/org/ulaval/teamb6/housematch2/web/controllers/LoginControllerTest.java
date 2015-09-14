package org.ulaval.teamb6.housematch2.web.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.support.BindingAwareModelMap;
import org.ulaval.teamb6.housematch2.repository.UserRepository;
import org.ulaval.teamb6.housematch2.web.converters.LoginUserConverter;

public class LoginControllerTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private LoginUserConverter converter;

  @InjectMocks
  public LoginController controller;

  private BindingAwareModelMap model;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void loginReturnsTheLoginView() {
    model = new BindingAwareModelMap();
    String view = controller.login(model);

    assertEquals("login", view);
  }
}
