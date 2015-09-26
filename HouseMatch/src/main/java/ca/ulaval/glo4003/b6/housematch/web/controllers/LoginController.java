package ca.ulaval.glo4003.b6.housematch.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.ulaval.glo4003.b6.housematch.admin.repository.exception.CouldNotAccesAdminDataException;
import ca.ulaval.glo4003.b6.housematch.user.anticorruption.UserLoginCorruptionVerificator;
import ca.ulaval.glo4003.b6.housematch.user.anticorruption.exceptions.InvalidUserLoginFieldException;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserLoginDto;
import ca.ulaval.glo4003.b6.housematch.web.converters.LoginUserConverter;
import ca.ulaval.glo4003.b6.housematch.web.viewModel.LoginUserViewModel;

@Controller
public class LoginController {

   private LoginUserConverter loginUserConverter;

   private UserLoginCorruptionVerificator userCorruptionVerificator;

   public void setLoginUserConverter(LoginUserConverter loginUserConverter) {
      this.loginUserConverter = loginUserConverter;
   }

   public void setUserCorruptionVerificator(UserLoginCorruptionVerificator userCorruptionVerificator) {
      this.userCorruptionVerificator = userCorruptionVerificator;
   }

   @Autowired
   public LoginController(LoginUserConverter loginUserConverter,
         UserLoginCorruptionVerificator userCorruptionVerificator) {
      this.loginUserConverter = loginUserConverter;
      this.userCorruptionVerificator = userCorruptionVerificator;
   }

   @RequestMapping(value = "/login", method = RequestMethod.POST)
   public String login(HttpServletRequest request, LoginUserViewModel viewModel)
         throws InvalidUserLoginFieldException, CouldNotAccesAdminDataException {
      UserLoginDto userDto = loginUserConverter.convertToDto(viewModel);
      userCorruptionVerificator.login(request, userDto);
      return "redirect:/";
   }

   @RequestMapping(value = "/login", method = RequestMethod.GET)
   public String login(Model model) {
      model.addAttribute("user", new LoginUserViewModel());
      return "login";
   }

}
