package ca.ulaval.glo4003.b6.housematch.web.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.EstateNotFoundException;
import ca.ulaval.glo4003.b6.housematch.estates.services.EstatesFetcher;
import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.user.domain.Role;
import ca.ulaval.glo4003.b6.housematch.user.services.UserAuthorizationService;
import ca.ulaval.glo4003.b6.housematch.user.services.exceptions.InvalidAccessException;
import ca.ulaval.glo4003.b6.housematch.web.converters.DescriptionConverter;
import ca.ulaval.glo4003.b6.housematch.web.converters.EstateConverter;
import ca.ulaval.glo4003.b6.housematch.web.viewModel.DescriptionModel;
import ca.ulaval.glo4003.b6.housematch.web.viewModel.EstateModel;

@Controller
public class BuyerEstateController {
   
   private EstateConverter estateConverter;
   
   private DescriptionConverter descriptionConverter;

   private EstatesFetcher estatesFetcher;

   private UserAuthorizationService userAuthorizationService;

   private final static String expectedRole = Role.BUYER;

   @Autowired
   public BuyerEstateController(EstateConverter estateConverter,
            DescriptionConverter descriptionConverter,
            EstatesFetcher estatesFetcher,
            UserAuthorizationService userAuthorizationService) {
      
      this.descriptionConverter = descriptionConverter;
      this.estateConverter = estateConverter;
      this.estatesFetcher = estatesFetcher;
      this.userAuthorizationService = userAuthorizationService;
   }
   
   @RequestMapping(value = "/buyer/{userId}/estates", method = RequestMethod.GET)
   public ModelAndView getBuyerEstates(@PathVariable("userId") String userId, HttpServletRequest request, Model model) throws InvalidAccessException, CouldNotAccessDataException {
      userAuthorizationService.isSessionAloud(request, expectedRole);
      
      List<EstateDto> allEstates = estatesFetcher.getAllEstates();
      List<EstateModel> estatesModel = new ArrayList<EstateModel>();
      
      for (EstateDto estateDto : allEstates) {
         estatesModel.add(estateConverter.convertToModel(estateDto));
      }
      ModelAndView buyerEstatesViewModel = new ModelAndView("buyer_estates");
      buyerEstatesViewModel.addObject("estates", estatesModel);
      
      return buyerEstatesViewModel;
   }
   
   @RequestMapping(value = "/buyer/{userId}/estates/{address}", method = RequestMethod.GET)
   public ModelAndView getEstateByAddress(@PathVariable("address") String address, HttpServletRequest request)
         throws EstateNotFoundException, CouldNotAccessDataException, InvalidAccessException {
      userAuthorizationService.isSessionAloud(request, expectedRole);
      
      EstateDto estateByAddress = estatesFetcher.getEstateByAddress(address);
      
      EstateModel estateModel = estateConverter.convertToModel(estateByAddress);
      DescriptionModel descriptionModel = descriptionConverter.convertToModel(estateByAddress.getDescriptionDto());
      
      ModelAndView buyerEstateViewModel = new ModelAndView("buy_estate");
      buyerEstateViewModel.addObject("estate", estateModel);
      buyerEstateViewModel.addObject("description", descriptionModel);
      
      return buyerEstateViewModel;
   }

}
