package ca.ulaval.glo4003.b6.housematch.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ca.ulaval.glo4003.b6.housematch.services.user.UserAuthorizationService;

@Controller
public class HomeController {

   private UserAuthorizationService userAuthorizationService;

   public HomeController(UserAuthorizationService userAuthorizationService) {
      this.userAuthorizationService = userAuthorizationService;
   }

   @RequestMapping("/")
   public String index(HttpServletRequest request) {
      if (!userAuthorizationService.isUserLogged(request)) {
         return "redirect:/home";
      }
      return "index";
   }

   @RequestMapping("/approbationWarning")
   public String approbationWarning() {
      return "approbation_warning";
   }

}
