package ca.ulaval.glo4003.b6.housematch.web.controllers;

import static org.mockito.BDDMockito.given;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.user.services.UserAuthorizationService;

public class LogoutControllerTest {

   @InjectMocks
   public LogoutController controller;

   @Mock
   private HttpSession session;

   @Mock
   private HttpServletRequest request;

   @Mock
   private UserAuthorizationService userAuthorizationService;

   @Before
   public void setup() {

      MockitoAnnotations.initMocks(this);
      configureUserAuthorisationService();
   }

   @Test
   public void logoutShouldDelegateToUserAuthorisationService() {

      // When
      controller.logout(request);

      // Then
      verify(userAuthorizationService, times(1)).closeSession(request);
   }

   private void configureUserAuthorisationService() {
      given(userAuthorizationService.closeSession(request)).willReturn(request);

   }

}
