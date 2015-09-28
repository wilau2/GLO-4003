package ca.ulaval.glo4003.b6.housematch.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import ca.ulaval.glo4003.b6.housematch.estates.anticorruption.exceptions.InvalidEstateFieldException;

@ControllerAdvice
public class ExceptionHandlingController {

   @ExceptionHandler(InvalidEstateFieldException.class)
   public ModelAndView handleInvalidFieldException(HttpServletRequest servletRequest, Exception exception) {
      ModelAndView modelAndView = new ModelAndView("exception");
      modelAndView.addObject("errorMessage", exception.getMessage());
      return modelAndView;
   }
}
