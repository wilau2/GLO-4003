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

import ca.ulaval.glo4003.b6.housematch.estates.anticorruption.DescriptionCorruptionVerificator;
import ca.ulaval.glo4003.b6.housematch.estates.anticorruption.EstateCorruptionVerificator;
import ca.ulaval.glo4003.b6.housematch.estates.anticorruption.exceptions.InvalidDescriptionFieldException;
import ca.ulaval.glo4003.b6.housematch.estates.anticorruption.exceptions.InvalidEstateFieldException;
import ca.ulaval.glo4003.b6.housematch.estates.anticorruption.exceptions.InvalidLandFieldException;
import ca.ulaval.glo4003.b6.housematch.estates.anticorruption.exceptions.InvalidRoomFieldException;
import ca.ulaval.glo4003.b6.housematch.estates.dto.DescriptionDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.EstateNotFoundException;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.InvalidDescriptionException;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.SellerNotFoundException;
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
public class SellerEstateController {

   private EstateConverter estateConverter;
   
   private DescriptionConverter descriptionConverter;

   private EstateCorruptionVerificator estateCorruptionVerificator;
   
   private DescriptionCorruptionVerificator descriptionCorruptionVerificator;

   private EstatesFetcher estatesFetcher;

   private UserAuthorizationService userAuthorizationService;

   private final static String expectedRole = Role.SELLER;

   @Autowired
   public SellerEstateController(EstateConverter estateConverter,
            DescriptionConverter descriptionConverter,
            DescriptionCorruptionVerificator descriptionCorruptionVerificator,
            EstateCorruptionVerificator estateCorruptionVerificator, 
            EstatesFetcher estatesFetcher,
            UserAuthorizationService userAuthorizationService) {
      
      this.descriptionConverter = descriptionConverter;
      this.descriptionCorruptionVerificator = descriptionCorruptionVerificator;
      this.estateConverter = estateConverter;
      this.estateCorruptionVerificator = estateCorruptionVerificator;
      this.estatesFetcher = estatesFetcher;
      this.userAuthorizationService = userAuthorizationService;
   }

   @RequestMapping(value = "/seller/{userId}/estates/add", method = RequestMethod.POST)
   public String addEstate(HttpServletRequest request, EstateModel estateModel, @PathVariable("userId") String userId)
         throws InvalidEstateFieldException, CouldNotAccessDataException, InvalidAccessException {

      userAuthorizationService.isSessionAloud(request, expectedRole);
      estateModel.setSeller(userId);

      EstateDto estateDto = estateConverter.convertToDto(estateModel);
      estateCorruptionVerificator.addEstate(estateDto);

      return "redirect:/";
   }

   @RequestMapping(value = "/seller/{userId}/estates/add", method = RequestMethod.GET)
   public String getSellEstatePage(HttpServletRequest request, Model model) throws InvalidAccessException {
      userAuthorizationService.isSessionAloud(request, expectedRole);
      model.addAttribute("estate", new EstateModel());
      return "sell_estate";
   }

   @RequestMapping(value = "/seller/{userId}/estates", method = RequestMethod.GET)
   public ModelAndView getSellerEstates(@PathVariable("userId") String userId, HttpServletRequest request)
         throws SellerNotFoundException, CouldNotAccessDataException, InvalidAccessException {

      userAuthorizationService.isSessionAloud(request, expectedRole);

      List<EstateDto> estatesFromSeller = estatesFetcher.getEstatesBySeller(userId);

      List<EstateModel> estatesModel = new ArrayList<EstateModel>();
      for (EstateDto estateDto : estatesFromSeller) {
         estatesModel.add(estateConverter.convertToModel(estateDto));
      }

      ModelAndView sellerEstatesViewModel = new ModelAndView("seller_estates");
      sellerEstatesViewModel.addObject("estates", estatesModel);

      return sellerEstatesViewModel;
   }

   @RequestMapping(value = "/seller/{userId}/estates/{address}", method = RequestMethod.GET)
   public ModelAndView getEstateByAddress(@PathVariable("address") String address, HttpServletRequest request)
         throws EstateNotFoundException, CouldNotAccessDataException, InvalidAccessException {
      userAuthorizationService.isSessionAloud(request, expectedRole);
      
      EstateDto estateByAddress = estatesFetcher.getEstateByAddress(address);
      
      EstateModel estateModel = estateConverter.convertToModel(estateByAddress);
      //DescriptionModel descriptionModel = descriptionConverter.convertToModel(estateByAddress.getDescriptionDto());
      DescriptionModel descriptionModel = descriptionConverter.convertToModel(descriptionConverter.createTestDescriptionDto());

      ModelAndView estateViewModel = new ModelAndView("estate");
      estateViewModel.addObject("estate", estateModel);
      estateViewModel.addObject("description", descriptionModel);
      
      return estateViewModel;
   }

   @RequestMapping(value = "/seller/{userId}/estates/{address}", method = RequestMethod.POST)
   public String editEstate(HttpServletRequest request, DescriptionModel descriptionModel, @PathVariable("userId") String userId)
         throws InvalidEstateFieldException, CouldNotAccessDataException, InvalidAccessException, InvalidDescriptionFieldException, InvalidRoomFieldException, InvalidLandFieldException, InvalidDescriptionException {

      userAuthorizationService.isSessionAloud(request, expectedRole);
 
      DescriptionDto descriptionDto = descriptionConverter.convertToDto(descriptionModel);
      descriptionCorruptionVerificator.addDescription(descriptionDto);

      return "redirect:/";
   }
}
