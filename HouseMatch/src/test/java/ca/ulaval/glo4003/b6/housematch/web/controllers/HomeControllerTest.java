package ca.ulaval.glo4003.b6.housematch.web.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HomeControllerTest {

   @Test
   public void rendersIndex() {
      assertEquals("index", new HomeController().index());
   }

}
