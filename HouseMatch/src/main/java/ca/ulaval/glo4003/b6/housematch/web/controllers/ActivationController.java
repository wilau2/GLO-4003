package ca.ulaval.glo4003.b6.housematch.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.ulaval.glo4003.b6.housematch.user.repository.exception.CouldNotAccessUserDataException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.user.services.ActivationService;

@Controller
public class ActivationController {

   private ActivationService activationService;

   @Autowired
   public ActivationController(ActivationService activationService) {
      this.activationService = activationService;
   }

   @RequestMapping(value = "/{username}/confirmation")
   public String confirmation(@PathVariable("username") String username)
         throws UserNotFoundException, CouldNotAccessUserDataException {

      return "confirmation";
   }

   @RequestMapping(value = "/{username}/confirmation", method = RequestMethod.POST)
   public String confirmation(HttpServletRequest request, @PathVariable("username") String username)
         throws UserNotFoundException, CouldNotAccessUserDataException {
      activationService.activateAccount(username);
      return "redirect:/";
   }

}
