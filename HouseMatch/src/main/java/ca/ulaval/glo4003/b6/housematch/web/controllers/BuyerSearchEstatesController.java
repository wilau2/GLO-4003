package ca.ulaval.glo4003.b6.housematch.web.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.EstateNotFoundException;
import ca.ulaval.glo4003.b6.housematch.estates.repository.factory.EstateRepositoryFactory;
import ca.ulaval.glo4003.b6.housematch.estates.services.EstatesFetcher;
import ca.ulaval.glo4003.b6.housematch.estates.services.EstatesFetcherFactory;
import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.user.domain.Role;
import ca.ulaval.glo4003.b6.housematch.user.services.UserAuthorizationService;
import ca.ulaval.glo4003.b6.housematch.user.services.exceptions.InvalidAccessException;

@Controller
public class BuyerSearchEstatesController {

   private static final String EXPECTED_ROLE = Role.BUYER;

   private EstatesFetcherFactory estatesFetcherFactory;

   private EstateAssemblerFactory estateAssemblerFactory;

   private EstateRepositoryFactory estateRepositoryFactory;

   private UserAuthorizationService userAuthorizationService;

   @Autowired
   public BuyerSearchEstatesController(EstatesFetcherFactory estatesFetcherFactory,
         EstateAssemblerFactory estateAssemblerFactory, EstateRepositoryFactory estateRepositoryFactory,
         UserAuthorizationService userAuthorizationService) {
      this.estatesFetcherFactory = estatesFetcherFactory;
      this.estateAssemblerFactory = estateAssemblerFactory;
      this.estateRepositoryFactory = estateRepositoryFactory;
      this.userAuthorizationService = userAuthorizationService;
   }

   @RequestMapping(method = RequestMethod.GET, path = "/buyer/{userId}/estates")
   public ModelAndView searchAllEstates(HttpServletRequest request)
         throws CouldNotAccessDataException, InvalidAccessException {
      userAuthorizationService.verifySessionIsAllowed(request, EXPECTED_ROLE);

      ModelAndView modelAndView = new ModelAndView("buyer_search");

      EstatesFetcher estatesFetcher = estatesFetcherFactory.newInstance(estateAssemblerFactory,
            estateRepositoryFactory);

      List<EstateDto> allEstates = estatesFetcher.getAllEstates();
      modelAndView.addObject("estates", allEstates);

      return modelAndView;
   }

   @RequestMapping(method = RequestMethod.GET, path = "/buyer/{userId}/estates/{address}")
   public ModelAndView getEstateByAddress(@PathVariable("address") String address, HttpServletRequest request)
         throws EstateNotFoundException, CouldNotAccessDataException, InvalidAccessException {
      userAuthorizationService.verifySessionIsAllowed(request, EXPECTED_ROLE);

      ModelAndView modelAndView = new ModelAndView("estate");

      EstatesFetcher estatesFetcher = estatesFetcherFactory.newInstance(estateAssemblerFactory,
            estateRepositoryFactory);

      EstateDto estateByAddress = estatesFetcher.getEstateByAddress(address);
      modelAndView.addObject("estate", estateByAddress);

      return modelAndView;
   }

}
