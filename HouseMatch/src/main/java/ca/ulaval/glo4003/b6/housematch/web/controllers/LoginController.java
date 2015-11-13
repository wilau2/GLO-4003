package ca.ulaval.glo4003.b6.housematch.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.ulaval.glo4003.b6.housematch.anticorruption.user.UserLoginCorruptionVerificator;
import ca.ulaval.glo4003.b6.housematch.anticorruption.user.exceptions.InvalidUserLoginFieldException;
import ca.ulaval.glo4003.b6.housematch.domain.user.exceptions.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.dto.UserDto;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.InvalidPasswordException;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.UserActivationException;

@Controller
public class LoginController {

   private UserLoginCorruptionVerificator userCorruptionVerificator;

   @Autowired
   public LoginController(UserLoginCorruptionVerificator userCorruptionVerificator) {
      this.userCorruptionVerificator = userCorruptionVerificator;
   }

   @RequestMapping(value = "/login", method = RequestMethod.POST)
   public String login(HttpServletRequest request, UserDto userDto) throws InvalidUserLoginFieldException,
         UserNotFoundException, CouldNotAccessDataException, InvalidPasswordException, UserActivationException {

      userCorruptionVerificator.login(request, userDto);
      return "redirect:/";
   }

   @RequestMapping(value = "/login", method = RequestMethod.GET)
   public String login(Model model) {
      model.addAttribute("user", new UserDto());
      return "login";
   }

}
