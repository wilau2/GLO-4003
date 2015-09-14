package org.ulaval.teamb6.housematch2.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.ulaval.teamb6.housematch2.domain.User;
import org.ulaval.teamb6.housematch2.repository.InMemoryUserRepository;
import org.ulaval.teamb6.housematch2.repository.UserRepository;
import org.ulaval.teamb6.housematch2.web.converters.SignupUserConverter;
import org.ulaval.teamb6.housematch2.web.viewModel.SignupUserModel;

@Controller
public class SignupController {

  private UserRepository repository;

  private SignupUserConverter converter;

  @Autowired
  public SignupController(InMemoryUserRepository repository, SignupUserConverter converter) {
    this.repository = repository;
    this.converter = converter;
  }

  @RequestMapping(value = "/signup", method = RequestMethod.POST)
  public String signup(SignupUserModel viewModel) {
    User user = converter.convert(viewModel);
    repository.add(user);
    return "index";
  }

  @RequestMapping(value = "/signup", method = RequestMethod.GET)
  public String signup(Model model) {
    model.addAttribute("user", new SignupUserModel());
    return "signup";
  }
}
