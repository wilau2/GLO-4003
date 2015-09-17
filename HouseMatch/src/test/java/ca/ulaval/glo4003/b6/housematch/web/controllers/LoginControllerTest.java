package ca.ulaval.glo4003.b6.housematch.web.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.support.BindingAwareModelMap;

import ca.ulaval.glo4003.b6.housematch.user.model.User;
import ca.ulaval.glo4003.b6.housematch.user.repository.XMLUserRepository;
import ca.ulaval.glo4003.b6.housematch.web.controllers.LoginController;
import ca.ulaval.glo4003.b6.housematch.web.converters.LoginUserConverter;
import ca.ulaval.glo4003.b6.housematch.web.viewModel.LoginUserModel;

public class LoginControllerTest {

  @Mock
  private HttpSession session;

  @Mock
  private User user;

  @Mock
  private LoginUserModel loginExistingUser;

  @Mock
  private XMLUserRepository userRepository;

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
    configureRequest();
  }

  @Test
  public void getRequestLoginReturnsTheLoginView() {
    model = new BindingAwareModelMap();
    String view = controller.login(model);

    assertEquals("login", view);
  }

  @Test
  public void postRequestLoginReturnsTheIndexView() {
    // model = new BindingAwareModelMap();

    // String view = controller.login(request, loginExistingUser);

    // assertEquals("redirect:/", view);
  }

  @Test
  public void postRequestLoginShouldUseTheConverter() {
    // controller.login(request, loginExistingUser);

    // verify(converter).convert(loginExistingUser);
  }

  @Test
  public void postRequestLoginShouldUseTheRepository() {
    // controller.login(request, loginExistingUser);

    // verify(userRepository).findByEmail(user);
  }

  @Test
  public void postRequestLoginShouldSetALoggedUser() {
    // controller.login(request, loginExistingUser);
    // assertEquals(loginExistingUser.email, request.getAttribute("loggedInUser"));
  }

  private void configureConverter() {
    given(converter.convert(loginExistingUser)).willReturn(user);
  }

  private void configureRepository() {
    // given(userRepository.findByEmail(user)).willReturn(user);
  }

  private void configureRequest() {
    // given(request.getSession()).willReturn(session);
  }
}
