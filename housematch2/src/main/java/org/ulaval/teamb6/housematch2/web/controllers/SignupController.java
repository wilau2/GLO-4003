package org.ulaval.teamb6.housematch2.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.ulaval.teamb6.housematch2.dao.UserRepository;
import org.ulaval.teamb6.housematch2.model.User;
import org.ulaval.teamb6.housematch2.web.converters.SignupUserConverter;
import org.ulaval.teamb6.housematch2.web.viewmodels.SignupUserViewModel;

@Controller
public class SignupController {

   private UserRepository repository;

   private SignupUserConverter converter;

   @Autowired
   public SignupController(UserRepository repository, SignupUserConverter converter) {
      this.repository = repository;
      this.converter = converter;
   }

   @RequestMapping(value = "/signup", method = RequestMethod.POST)
   public String signup(SignupUserViewModel viewModel) {
      User entry = converter.convert(viewModel);
      repository.add(entry);
      return "index";
   }

   @RequestMapping(value = "/signup", method = RequestMethod.GET)
   public String signup(Model model) {
      model.addAttribute("entry", new SignupUserViewModel());
      return "signup";
   }

}
