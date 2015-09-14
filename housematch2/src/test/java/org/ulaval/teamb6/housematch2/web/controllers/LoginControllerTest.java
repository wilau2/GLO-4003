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
import org.ulaval.teamb6.housematch2.web.converters.LoginUserConverter;
import org.ulaval.teamb6.housematch2.web.viewModel.LoginUserModel;

public class LoginControllerTest {

  @Mock
  private User user;

  @Mock
  private LoginUserModel loginExistingUser;

  @Mock
  private InMemoryUserRepository userRepository;

  @Mock
  private LoginUserConverter converter;

  @InjectMocks
  public LoginController controller;

  private BindingAwareModelMap model;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    configureConverter();
    configureRepository();
  }

  @Test
  public void getRequestLoginReturnsTheLoginView() {
    model = new BindingAwareModelMap();
    String view = controller.login(model);

    assertEquals("login", view);
  }

  @Test
  public void postRequestLoginReturnsTheIndexView() {
    model = new BindingAwareModelMap();
    String view = controller.login(loginExistingUser);

    assertEquals("index", view);
  }

  @Test
  public void postRequestLoginShouldUseTheConverter() {
    controller.login(loginExistingUser);

    verify(converter).convert(loginExistingUser);
  }

  @Test
  public void postRequestLoginShouldUseTheRepository() {
    controller.login(loginExistingUser);

    verify(userRepository).getByEmail(user);
  }

  private void configureConverter() {
    given(converter.convert(loginExistingUser)).willReturn(user);
  }

  private void configureRepository() {
    given(userRepository.getByEmail(user)).willReturn(user);
  }
}
