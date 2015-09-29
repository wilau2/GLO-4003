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

public class LogoutControllerTest {

   @Mock
   private HttpSession session;

   @Mock
   private HttpServletRequest request;

   @InjectMocks
   private LogoutController controller;

   @Before
   public void setup() {

      MockitoAnnotations.initMocks(this);
      configureRequest();
   }

   @Test
   public void logoutShouldSetLoggedUserToNull() {
      controller.logout(request);
      assertEquals(null, request.getAttribute("loggedInUser"));
   }

   private void configureRequest() {
      given(request.getSession()).willReturn(session);
   }

}
