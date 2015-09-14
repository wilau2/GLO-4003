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

import ca.ulaval.glo4003.b7.housematch.domain.User;
import ca.ulaval.glo4003.b7.housematch.repository.InMemoryUserRepository;
import ca.ulaval.glo4003.b7.housematch.web.controllers.LoginController;
import ca.ulaval.glo4003.b7.housematch.web.converters.LoginUserConverter;
import ca.ulaval.glo4003.b7.housematch.web.viewModel.LoginUserModel;

public class LoginControllerTest {

  @Mock
  private User user;

  @Mock
  private LoginUserModel loginExistingUser;

  @Mock
  private InMemoryUserRepository userRepository;

  @Mock
  private LoginUserConverter converter;

  @Mock
  private HttpServletRequest request;

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

    String view = controller.login(request, loginExistingUser);

    assertEquals("index", view);
  }

  @Test
  public void postRequestLoginShouldUseTheConverter() {
    controller.login(request, loginExistingUser);

    verify(converter).convert(loginExistingUser);
  }

  @Test
  public void postRequestLoginShouldUseTheRepository() {
    controller.login(request, loginExistingUser);

    verify(userRepository).getByEmail(user);
  }

  private void configureConverter() {
    given(converter.convert(loginExistingUser)).willReturn(user);
  }

  private void configureRepository() {
    given(userRepository.getByEmail(user)).willReturn(user);
  }
}
