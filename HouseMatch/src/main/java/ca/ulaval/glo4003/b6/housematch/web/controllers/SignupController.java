package ca.ulaval.glo4003.b6.housematch.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.ulaval.glo4003.b6.housematch.user.dto.UserDto;
import ca.ulaval.glo4003.b6.housematch.user.model.User;
import ca.ulaval.glo4003.b6.housematch.user.repository.UserRepository;
import ca.ulaval.glo4003.b6.housematch.web.converters.SignupUserConverter;
import ca.ulaval.glo4003.b6.housematch.web.viewModel.SignupUserModel;

@Controller
public class SignupController {

   private UserRepository userRepository;

   private SignupUserConverter converter;

   public void setUserRepository(UserRepository userRepository) {
      this.userRepository = userRepository;
   }

   @Autowired
   public SignupController(UserRepository userRepository, SignupUserConverter converter) {
      this.userRepository = userRepository;
      this.converter = converter;
   }

   @RequestMapping(value = "/signup", method = RequestMethod.POST)
   public String signup(HttpServletRequest request, SignupUserModel viewModel) {
      UserDto user = converter.convert(viewModel);
      // TODO FIX THAT WITH ASSEMBLER
      User user1 = new User(null, null, null, null);
      userRepository.add(user1);
      request.getSession().setAttribute("loggedInUserEmail", user.getEmail());
      return "index";
   }

   @RequestMapping(value = "/signup", method = RequestMethod.GET)
   public String signup(Model model) {
      model.addAttribute("user", new SignupUserModel());
      return "signup";
   }
}
