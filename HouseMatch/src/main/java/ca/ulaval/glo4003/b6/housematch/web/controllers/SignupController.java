package ca.ulaval.glo4003.b6.housematch.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.ulaval.glo4003.b6.housematch.user.anticorruption.UserSignupCorruptionVerificator;
import ca.ulaval.glo4003.b6.housematch.user.anticorruption.exceptions.InvalidUserSignupFieldException;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserSignupDto;
import ca.ulaval.glo4003.b6.housematch.user.repository.UserDao;
import ca.ulaval.glo4003.b6.housematch.web.converters.SignupUserConverter;
import ca.ulaval.glo4003.b6.housematch.web.viewModel.SignupUserModel;

@Controller
public class SignupController {

   private SignupUserConverter converter;

   private UserSignupCorruptionVerificator userSignupCorruptionVerificator;

   @Autowired
   public SignupController(UserDao userRepository, SignupUserConverter converter,
         UserSignupCorruptionVerificator userSignupCorruptionVerificator) {
      this.converter = converter;
      this.userSignupCorruptionVerificator = userSignupCorruptionVerificator;
   }

   @RequestMapping(value = "/signup", method = RequestMethod.POST)
   public String signup(HttpServletRequest request, SignupUserModel viewModel) throws InvalidUserSignupFieldException {
      UserSignupDto userDto = converter.convertToDto(viewModel);
      userSignupCorruptionVerificator.signup(request, userDto);
      // TODO SECURITY
      request.getSession().setAttribute("loggedInUserEmail", userDto.getUsername());
      return "index";
   }

   @RequestMapping(value = "/signup", method = RequestMethod.GET)
   public String signup(Model model) {
      model.addAttribute("user", new SignupUserModel());
      return "signup";
   }
}
