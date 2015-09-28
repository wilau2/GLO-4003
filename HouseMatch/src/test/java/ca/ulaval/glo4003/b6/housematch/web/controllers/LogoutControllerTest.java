package ca.ulaval.glo4003.b6.housematch.web.controllers;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.user.services.UserAuthorisationService;

public class LogoutControllerTest {

   @Mock
   private HttpSession session;

   @Mock
   private HttpServletRequest request;

   @Mock
   private UserAuthorisationService userAuthorisationService;

   @InjectMocks
   public LogoutController controller;

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
      verify(userAuthorisationService.closeSession(request));
   }

   private void configureUserAuthorisationService() {
      given(userAuthorisationService.closeSession(request)).willReturn(request);

   }

}
