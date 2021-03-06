package ca.ulaval.glo4003.b6.housematch.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.ulaval.glo4003.b6.housematch.anticorruption.user.exceptions.InvalidContactInformationFieldException;
import ca.ulaval.glo4003.b6.housematch.anticorruption.user.exceptions.InvalidUserSignupFieldException;
import ca.ulaval.glo4003.b6.housematch.domain.user.exceptions.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.domain.user.exceptions.UsernameAlreadyExistsException;
import ca.ulaval.glo4003.b6.housematch.dto.UserDto;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.user.UserSignupService;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.InvalidPasswordException;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.UserActivationException;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.UserNotifyingException;

@Controller
public class SignupController {

   private UserSignupService userSignupService;

   @Autowired
   public SignupController(UserSignupService userSignupService) {
      this.userSignupService = userSignupService;

   }

   @RequestMapping(value = "/signup", method = RequestMethod.POST)
   public String signup(HttpServletRequest request, UserDto userDto) throws InvalidUserSignupFieldException,
         UserNotFoundException, CouldNotAccessDataException, InvalidPasswordException, UsernameAlreadyExistsException,
         InvalidContactInformationFieldException, UserNotifyingException, UserActivationException {

      userSignupService.signup(userDto);

      return "redirect:/confirmation";
   }

   @RequestMapping(value = "/signup", method = RequestMethod.GET)
   public String signup(Model model) {
      model.addAttribute("user", new UserDto());
      return "signup";
   }
}
