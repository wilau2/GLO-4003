package ca.ulaval.glo4003.b6.housematch.web.controllers;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ca.ulaval.glo4003.b6.housematch.domain.estate.Estate;
import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.EstateNotFoundException;
import ca.ulaval.glo4003.b6.housematch.domain.user.Role;
import ca.ulaval.glo4003.b6.housematch.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.estate.EstatesFetcher;
import ca.ulaval.glo4003.b6.housematch.services.user.UserAuthorizationService;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.InvalidAccessException;

@Controller
public class BuyerSearchEstatesController {

   private static final String EXPECTED_ROLE = Role.BUYER;

   private EstatesFetcher estatesFetcher;

   private UserAuthorizationService userAuthorizationService;
   
   private List<EstateDto> allEstates;

   @Autowired
   public BuyerSearchEstatesController(EstatesFetcher estatesFetcher,
         UserAuthorizationService userAuthorizationService) {
      this.estatesFetcher = estatesFetcher;
      this.userAuthorizationService = userAuthorizationService;
   }

   @RequestMapping(method = RequestMethod.GET, path = "/buyer/{userId}/estates")
   public ModelAndView searchAllEstates(HttpServletRequest request)
         throws CouldNotAccessDataException, InvalidAccessException {
      userAuthorizationService.verifySessionIsAllowed(request, EXPECTED_ROLE);

      ModelAndView modelAndView = new ModelAndView("buyer_search");

      allEstates = estatesFetcher.getAllEstates();
      modelAndView.addObject("estates", allEstates);

      return modelAndView;
   }

   @RequestMapping(method = RequestMethod.GET, path = "/buyer/{userId}/estates/{address}")
   public ModelAndView getEstateByAddress(@PathVariable("address") String address, HttpServletRequest request)
         throws EstateNotFoundException, CouldNotAccessDataException, InvalidAccessException {
      userAuthorizationService.verifySessionIsAllowed(request, EXPECTED_ROLE);

      ModelAndView modelAndView = new ModelAndView("estate");

      EstateDto estateByAddress = estatesFetcher.getEstateByAddress(address);
      modelAndView.addObject("estate", estateByAddress);

      modelAndView.addObject("description", estateByAddress.getDescriptionDto());

      return modelAndView;
   }

}
