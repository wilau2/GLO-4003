package ca.ulaval.glo4003.b7.housematch.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerExemple {

   @RequestMapping("/")
   public String index() {
      return "Greetings from Spring Boot!";
   }

}
