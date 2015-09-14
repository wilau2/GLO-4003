package org.ulaval.teamb6.housematch2.web.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HomeControllerTest {

  @Test
  public void rendersIndex() {
    assertEquals("index", new HomeController().index());
  }

}
