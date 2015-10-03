package ca.ulaval.glo4003.b6.housematch.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.ulaval.glo4003.b6.housematch.user.services.UserAuthorizationService;

@Controller
public class LogoutController {

   private UserAuthorizationService userAuthorizationService;

   @Autowired
   public LogoutController(UserAuthorizationService userAuthorizationService) {
      this.userAuthorizationService = userAuthorizationService;
   }

   @RequestMapping(value = "/logout", method = RequestMethod.GET)
   public String logout(HttpServletRequest request) {
      request = userAuthorizationService.closeSession(request);
      return "redirect:/";
   }

}
