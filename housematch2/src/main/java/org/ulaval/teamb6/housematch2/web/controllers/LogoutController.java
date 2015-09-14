package org.ulaval.teamb6.housematch2.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.ulaval.teamb6.housematch2.repository.InMemoryUserRepository;
import org.ulaval.teamb6.housematch2.repository.UserRepository;
import org.ulaval.teamb6.housematch2.web.converters.LoginUserConverter;

@Controller
public class LogoutController {

  private UserRepository repository;

  private LoginUserConverter converter;

  @Autowired
  public LogoutController(InMemoryUserRepository repository, LoginUserConverter converter) {
    this.repository = repository;
    this.converter = converter;
  }

  @RequestMapping(value = "/logout", method = RequestMethod.GET)
  public String login(HttpServletRequest request) {
    request.setAttribute("loggedInUser", null);
    return "index";
  }
}
