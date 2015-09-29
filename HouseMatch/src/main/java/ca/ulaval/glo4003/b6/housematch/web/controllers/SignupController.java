package ca.ulaval.glo4003.b6.housematch.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.user.domain.User;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserSignupDto;
import ca.ulaval.glo4003.b6.housematch.user.repository.UserDao;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserAlreadyExistsException;
import ca.ulaval.glo4003.b6.housematch.web.converters.SignupUserConverter;
import ca.ulaval.glo4003.b6.housematch.web.viewModel.SignupUserModel;

@Controller
public class SignupController {

   private UserDao userRepository;

   private SignupUserConverter converter;

   public void setUserRepository(UserDao userRepository) {
      this.userRepository = userRepository;
   }

   @Autowired
   public SignupController(UserDao userRepository, SignupUserConverter converter) {
      this.userRepository = userRepository;
      this.converter = converter;
   }

   @RequestMapping(value = "/signup", method = RequestMethod.POST)
   public String signup(HttpServletRequest request, SignupUserModel viewModel)
         throws UserAlreadyExistsException, CouldNotAccessDataException {
      UserSignupDto user = converter.convertToDto(viewModel);
      // TODO FIX THAT WITH ASSEMBLER
      User user1 = new User(null, null, null, null, null, null);
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
