package org.ulaval.teamb6.housematch2.web.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.support.BindingAwareModelMap;
import org.ulaval.teamb6.housematch2.repository.UserRepository;
import org.ulaval.teamb6.housematch2.web.converters.SignupUserConverter;

public class SignupControllerTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private SignupUserConverter converter;

  @InjectMocks
  public SignupController controller;

  private BindingAwareModelMap model;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void loginReturnsTheLoginView() {
    model = new BindingAwareModelMap();
    String view = controller.signup(model);

    assertEquals("signup", view);
  }
}
