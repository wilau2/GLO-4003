package ca.ulaval.glo4003.b6.housematch.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.ulaval.glo4003.b6.housematch.user.anticorruption.exceptions.InvalidUserLoginFieldException;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserLoginDto;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.CouldNotAccessUserDataException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.user.services.ActivationService;
import ca.ulaval.glo4003.b6.housematch.user.services.exceptions.InvalidPasswordException;
import ca.ulaval.glo4003.b6.housematch.user.services.exceptions.UserActivationException;
import ca.ulaval.glo4003.b6.housematch.web.viewModel.LoginUserViewModel;

@Controller
public class ActivationController {
   
   private ActivationService activationService;
   
   @Autowired
   public ActivationController(ActivationService activationService){
      this.activationService = activationService;
   }
   
   @RequestMapping(value = "/confirmation/{username}")
   public String confirmation(@PathVariable("username") String username) throws UserNotFoundException, CouldNotAccessUserDataException {
      
      return "confirmation";
    }
   
   @RequestMapping(value = "/confirmation/{username}", method = RequestMethod.POST)
   public String confirmation(HttpServletRequest request, @PathVariable("username") String username) throws UserNotFoundException, CouldNotAccessUserDataException{
   activationService.activateAccount(username);
      return "redirect:/";
   }

}
