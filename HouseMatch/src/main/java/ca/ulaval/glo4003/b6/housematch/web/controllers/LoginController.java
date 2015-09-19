package ca.ulaval.glo4003.b6.housematch.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.ulaval.glo4003.b6.housematch.user.dto.UserDto;
import ca.ulaval.glo4003.b6.housematch.user.service.UserLoginService;
import ca.ulaval.glo4003.b6.housematch.web.converters.LoginUserConverter;
import ca.ulaval.glo4003.b6.housematch.web.viewModel.LoginUserViewModel;

@Controller
public class LoginController {

   private UserLoginService userLoginService;

   private LoginUserConverter converter;

   public void setUserLoginService(UserLoginService userLoginService) {
      this.userLoginService = userLoginService;
   }

   public void setConverter(LoginUserConverter converter) {
      this.converter = converter;
   }

   @Autowired
   public LoginController(UserLoginService userLoginService, LoginUserConverter converter) {
      this.userLoginService = userLoginService;
      this.converter = converter;
   }

   @RequestMapping(value = "/login", method = RequestMethod.POST)
   public String login(HttpServletRequest request, LoginUserViewModel viewModel) {
      UserDto user = converter.convertToDto(viewModel);
      userLoginService.serviceMethod(request, user);
      return "redirect:/";
   }

   @RequestMapping(value = "/login", method = RequestMethod.GET)
   public String login(Model model) {
      model.addAttribute("user", new LoginUserViewModel());
      return "login";

   }

}
