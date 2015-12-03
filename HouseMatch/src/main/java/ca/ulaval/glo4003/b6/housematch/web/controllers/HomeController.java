package ca.ulaval.glo4003.b6.housematch.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ca.ulaval.glo4003.b6.housematch.services.user.UserSessionAuthorizationService;

@Controller
public class HomeController {

   private UserSessionAuthorizationService userSessionAuthorizationService;

   public HomeController(UserSessionAuthorizationService userSessionAuthorizationService) {
      this.userSessionAuthorizationService = userSessionAuthorizationService;
   }

   @RequestMapping("/")
   public String index(HttpServletRequest request) {
      if (!userSessionAuthorizationService.isUserLogged(request)) {
         return "redirect:/home";
      }
      return "index";
   }

   @RequestMapping("/approbationWarning")
   public String approbationWarning() {
      return "approbation_warning";
   }

}
