package ca.ulaval.glo4003.b6.housematch.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.ulaval.glo4003.b6.housematch.services.user.UserSessionAuthorizationService;

@Controller
public class LogoutController {

   private UserSessionAuthorizationService userSessionAuthorizationService;

   @Autowired
   public LogoutController(UserSessionAuthorizationService userSessionAuthorizationService) {
      this.userSessionAuthorizationService = userSessionAuthorizationService;
   }

   @RequestMapping(value = "/logout", method = RequestMethod.GET)
   public String logout(HttpServletRequest request) {
      request = userSessionAuthorizationService.closeSession(request);
      return "redirect:/";
   }

}
