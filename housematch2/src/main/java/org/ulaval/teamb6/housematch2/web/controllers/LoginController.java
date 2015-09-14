package org.ulaval.teamb6.housematch2.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.ulaval.teamb6.housematch2.dao.UserRepository;
import org.ulaval.teamb6.housematch2.model.User;
import org.ulaval.teamb6.housematch2.web.converters.LoginUserConverter;
import org.ulaval.teamb6.housematch2.web.viewmodels.LoginUserViewModel;

@Controller
public class LoginController {

  private UserRepository repository;

  private LoginUserConverter converter;

  @Autowired
  public LoginController(UserRepository repository, LoginUserConverter converter) {
    this.repository = repository;
    this.converter = converter;
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public String login(LoginUserViewModel viewModel) {
    User user = converter.convert(viewModel);
    repository.getByEmail(user);
    return "index";
  }

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String login(Model model) {
    model.addAttribute("user", new LoginUserViewModel());
    return "login";

  }

}
