package ca.ulaval.glo4003.b7.housematch.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.ulaval.glo4003.b7.housematch.repository.InMemoryUserRepository;
import ca.ulaval.glo4003.b7.housematch.repository.UserRepository;
import ca.ulaval.glo4003.b7.housematch.web.converters.LoginUserConverter;

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
