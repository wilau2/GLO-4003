package ca.ulaval.glo4003.b6.housematch.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import ca.ulaval.glo4003.b6.housematch.anticorruption.estate.exceptions.InvalidDescriptionFieldException;
import ca.ulaval.glo4003.b6.housematch.anticorruption.estate.exceptions.InvalidEstateFieldException;
import ca.ulaval.glo4003.b6.housematch.anticorruption.user.exceptions.InvalidContactInformationFieldException;
import ca.ulaval.glo4003.b6.housematch.anticorruption.user.exceptions.InvalidUserLoginFieldException;
import ca.ulaval.glo4003.b6.housematch.anticorruption.user.exceptions.InvalidUserSignupFieldException;
import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.EstateNotFoundException;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.estate.exceptions.InvalidDescriptionException;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.InvalidAccessException;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.InvalidPasswordException;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.UserActivationException;

@ControllerAdvice
public class ExceptionHandlingController {

   @ExceptionHandler(InvalidEstateFieldException.class)
   public ModelAndView handleInvalidEstateFieldException(HttpServletRequest servletRequest, Exception exception) {
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

   @ExceptionHandler(InvalidAccessException.class)
   public ModelAndView handleInvalidAccessException(HttpServletRequest servletRequest, Exception exception) {
      ModelAndView modelAndView = new ModelAndView("exception");
      modelAndView.addObject("errorMessage", exception.getMessage());
      return modelAndView;
   }

   @ExceptionHandler(CouldNotAccessDataException.class)
   public ModelAndView handleCouldNotAccessDataException(HttpServletRequest request, Exception exceptionThrown) {
      ModelAndView modelAndView = new ModelAndView("exception");
      modelAndView.addObject("errorMessage", exceptionThrown.getMessage());
      return modelAndView;
   }

   @ExceptionHandler(InvalidDescriptionException.class)
   public ModelAndView handleInvalidDescriptionException(HttpServletRequest request, Exception exceptionThrown) {
      ModelAndView modelAndView = new ModelAndView("exception");
      modelAndView.addObject("errorMessage", exceptionThrown.getMessage());
      return modelAndView;
   }

   @ExceptionHandler(InvalidDescriptionFieldException.class)
   public ModelAndView handleInvalidDescriptionFieldException(HttpServletRequest request, Exception exceptionThrown) {
      ModelAndView modelAndView = new ModelAndView("exception");
      modelAndView.addObject("errorMessage", exceptionThrown.getMessage());
      return modelAndView;
   }

   @ExceptionHandler(EstateNotFoundException.class)
   public ModelAndView handleEstateNotFoundException(HttpServletRequest request, Exception exceptionThrown) {
      ModelAndView modelAndView = new ModelAndView("exception");
      modelAndView.addObject("errorMessage", exceptionThrown.getMessage());
      return modelAndView;
   }

   @ExceptionHandler(InvalidPasswordException.class)
   public ModelAndView handleInvalidPasswordException(HttpServletRequest request, Exception exceptionThrown) {
      ModelAndView modelAndView = new ModelAndView("exception");
      modelAndView.addObject("errorMessage", exceptionThrown.getMessage());
      return modelAndView;
   }

   @ExceptionHandler(UserActivationException.class)
   public String handleUserActivationException(HttpServletRequest request, Exception exceptionThrown) {
      return "redirect:/confirmation";
   }

}
