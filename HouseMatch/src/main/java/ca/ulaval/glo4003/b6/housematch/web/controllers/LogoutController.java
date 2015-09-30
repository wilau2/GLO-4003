package ca.ulaval.glo4003.b6.housematch.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.ulaval.glo4003.b6.housematch.user.services.UserAuthorisationService;

@Controller
public class LogoutController {

   private UserAuthorisationService userAuthorisationService;

   @Autowired
   public LogoutController(UserAuthorisationService userAuthorisationService) {
      this.userAuthorisationService = userAuthorisationService;
   }

   @RequestMapping(value = "/logout", method = RequestMethod.GET)
   public String logout(HttpServletRequest request) {
      request = userAuthorisationService.closeSession(request);
      return "redirect:/";
   }

}
