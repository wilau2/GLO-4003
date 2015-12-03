package ca.ulaval.glo4003.b6.housematch.web.controllers;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.services.user.UserSessionAuthorizationService;

public class HomeControllerTest {

   private HomeController homeController;

   @Mock
   private HttpServletRequest request;

   @Mock
   private UserSessionAuthorizationService userAuthorizationService;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);

      homeController = new HomeController(userAuthorizationService);

   }

   @Test
   public void rendersIndex() {
      // Given
      when(userAuthorizationService.isUserLogged(request)).thenReturn(true);

      // When
      String returnedViewNAme = homeController.index(request);

      // Then
      assertEquals("index", returnedViewNAme);
   }

   @Test
   public void renderApprobationWarningPage() {
      // Given no changes
      String expectedViewName = "approbation_warning";

      // When
      String returnedViewName = homeController.approbationWarning();

      // Then
      assertEquals(expectedViewName, returnedViewName);
   }

   @Test
   public void gettingTheHomePageWhenUserIsAnonymeShouldRedirectToTheAnonymousDashboard() {
      // Given
      String expectedRedirection = "redirect:/home";
      when(userAuthorizationService.isUserLogged(request)).thenReturn(false);

      // When
      String viewName = homeController.index(request);

      // Then
      assertEquals(expectedRedirection, viewName);
   }

   @Test
   public void whenGettingTheHomePageShouldCallUserAuthorizationServiceMethodForLoggedUser() {
      // Given

      // When
      homeController.index(request);

      // Then
      verify(userAuthorizationService, times(1)).isUserLogged(request);
   }
}
