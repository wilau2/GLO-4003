package ca.ulaval.glo4003.b7.housematch.web.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ca.ulaval.glo4003.b7.housematch.web.controllers.HomeController;

public class HomeControllerTest {

  @Test
  public void rendersIndex() {
    assertEquals("index", new HomeController().index());
  }

}
