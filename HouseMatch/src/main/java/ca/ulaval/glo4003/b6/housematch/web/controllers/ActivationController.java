package ca.ulaval.glo4003.b6.housematch.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ActivationController {
   
   @RequestMapping(value = "/confirmation/{username}")
   public String confirmation(@PathVariable("username") String username) {
      return "confirmation";
    }

}
