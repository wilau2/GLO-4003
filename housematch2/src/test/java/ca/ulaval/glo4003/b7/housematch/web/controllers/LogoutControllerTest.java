package ca.ulaval.glo4003.b7.housematch.web.controllers;

import static org.junit.Assert.assertEquals;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class LogoutControllerTest {

  @Mock
  private HttpServletRequest request;

  @InjectMocks
  public LogoutController controller;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void logoutShouldSetLoggedUserToNull() {
    controller.logout(request);

    assertEquals(null, request.getAttribute("loggedInUser"));
  }

}
