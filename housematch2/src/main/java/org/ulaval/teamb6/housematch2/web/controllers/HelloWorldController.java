package org.ulaval.teamb6.housematch2.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class HelloWorldController {

  @RequestMapping("/")
  public String index() {
    return "index";
  }

}
