package org.ulaval.teamb6.housematch2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.ulaval.teamb6.housematch2.web.controllers.HelloWorldController;

public class HelloWorldControllerTest {

  @Test
  public void rendersIndex() {
    assertEquals("index", new HelloWorldController().index());
  }

}
