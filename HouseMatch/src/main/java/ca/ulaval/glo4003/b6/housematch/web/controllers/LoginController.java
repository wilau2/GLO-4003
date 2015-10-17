package ca.ulaval.glo4003.b6.housematch.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.ulaval.glo4003.b6.housematch.user.anticorruption.UserLoginCorruptionVerificator;
import ca.ulaval.glo4003.b6.housematch.user.anticorruption.exceptions.InvalidUserLoginFieldException;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserDto;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.CouldNotAccessUserDataException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.user.services.exceptions.InvalidPasswordException;
import ca.ulaval.glo4003.b6.housematch.user.services.exceptions.UserActivationException;
import ca.ulaval.glo4003.b6.housematch.web.converters.LoginUserConverter;
import ca.ulaval.glo4003.b6.housematch.web.viewmodels.LoginUserViewModel;

@Controller
public class LoginController {

   private LoginUserConverter loginUserConverter;

   private UserLoginCorruptionVerificator userCorruptionVerificator;

   @Autowired
   public LoginController(LoginUserConverter loginUserConverter,
         UserLoginCorruptionVerificator userCorruptionVerificator) {
      this.loginUserConverter = loginUserConverter;
      this.userCorruptionVerificator = userCorruptionVerificator;
   }

   @RequestMapping(value = "/login", method = RequestMethod.POST)
   public String login(HttpServletRequest request, LoginUserViewModel viewModel) throws InvalidUserLoginFieldException,
         UserNotFoundException, CouldNotAccessUserDataException, InvalidPasswordException, UserActivationException {

      UserDto userDto = loginUserConverter.convertViewModelToDto(viewModel);

      userCorruptionVerificator.login(request, userDto);
      return "redirect:/";
   }

   @RequestMapping(value = "/login", method = RequestMethod.GET)
   public String login(Model model) {
      model.addAttribute("user", new LoginUserViewModel());
      return "login";
   }

}
