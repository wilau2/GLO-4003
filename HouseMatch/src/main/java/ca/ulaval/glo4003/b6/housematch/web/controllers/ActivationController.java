package ca.ulaval.glo4003.b6.housematch.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.ulaval.glo4003.b6.housematch.domain.user.exceptions.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.user.ActivationService;

@Controller
public class ActivationController {

   private ActivationService activationService;

   @Autowired
   public ActivationController(ActivationService activationService) {
      this.activationService = activationService;
   }

   @RequestMapping(value = "/{username}/confirmation")
   public String confirmation(@PathVariable("username") String username)
         throws UserNotFoundException, CouldNotAccessDataException {

      return "confirmation";
   }

   @RequestMapping(value = "/{username}/confirmation", method = RequestMethod.POST)
   public String confirmation(HttpServletRequest request, @PathVariable("username") String username)
         throws UserNotFoundException, CouldNotAccessDataException {
      activationService.activateAccount(username);
      return "redirect:/";
   }

   @RequestMapping(value = "/confirmation", method = RequestMethod.GET)
   public String getConfirmationNotice() {
      return "need_email_confirmation";
   }

}
