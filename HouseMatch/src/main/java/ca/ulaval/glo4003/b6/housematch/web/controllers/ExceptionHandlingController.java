package ca.ulaval.glo4003.b6.housematch.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import ca.ulaval.glo4003.b6.housematch.anticorruption.estate.exceptions.InvalidEstateFieldException;
import ca.ulaval.glo4003.b6.housematch.anticorruption.user.exceptions.InvalidContactInformationFieldException;
import ca.ulaval.glo4003.b6.housematch.anticorruption.user.exceptions.InvalidUserLoginFieldException;
import ca.ulaval.glo4003.b6.housematch.anticorruption.user.exceptions.InvalidUserSignupFieldException;

@ControllerAdvice
public class ExceptionHandlingController {

   @ExceptionHandler(InvalidEstateFieldException.class)
   public ModelAndView handleInvalidFieldException(HttpServletRequest servletRequest, Exception exception) {
      ModelAndView modelAndView = new ModelAndView("exception");
      modelAndView.addObject("errorMessage", exception.getMessage());
      return modelAndView;
   }

   @ExceptionHandler(InvalidUserLoginFieldException.class)
   public ModelAndView handleInvalidUserLoginFieldException(HttpServletRequest servletRequest, Exception exception) {
      ModelAndView modelAndView = new ModelAndView("exception");
      modelAndView.addObject("errorMessage", exception.getMessage());
      return modelAndView;
   }

   @ExceptionHandler(InvalidUserSignupFieldException.class)
   public ModelAndView handleInvalidUserSignupFieldException(HttpServletRequest servletRequest, Exception exception) {
      ModelAndView modelAndView = new ModelAndView("exception");
      modelAndView.addObject("errorMessage", exception.getMessage());
      return modelAndView;
   }

   @ExceptionHandler(InvalidContactInformationFieldException.class)
   public ModelAndView handleInvalidContactInformationFieldException(HttpServletRequest servletRequest,
         Exception exception) {
      ModelAndView modelAndView = new ModelAndView("exception");
      modelAndView.addObject("errorMessage", exception.getMessage());
      return modelAndView;
   }

}
