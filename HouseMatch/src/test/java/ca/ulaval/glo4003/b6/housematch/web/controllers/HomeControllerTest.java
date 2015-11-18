package ca.ulaval.glo4003.b6.housematch.web.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class HomeControllerTest {

   private HomeController homeController;

   @Before
   public void setup() {

      homeController = new HomeController();
   }

   @Test
   public void rendersIndex() {
      assertEquals("index", homeController.index());
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
}
