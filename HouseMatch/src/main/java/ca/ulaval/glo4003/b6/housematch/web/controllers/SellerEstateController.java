package ca.ulaval.glo4003.b6.housematch.web.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ca.ulaval.glo4003.b6.housematch.estates.anticorruption.DescriptionCorruptionVerificator;
import ca.ulaval.glo4003.b6.housematch.estates.anticorruption.EstateCorruptionVerificator;
import ca.ulaval.glo4003.b6.housematch.estates.anticorruption.exceptions.InvalidDescriptionFieldException;
import ca.ulaval.glo4003.b6.housematch.estates.anticorruption.exceptions.InvalidEstateFieldException;
import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.estates.dto.DescriptionDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.EstateNotFoundException;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.InvalidDescriptionException;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.InvalidEstateException;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.SellerNotFoundException;
import ca.ulaval.glo4003.b6.housematch.estates.repository.factory.EstateRepositoryFactory;
import ca.ulaval.glo4003.b6.housematch.estates.services.EstatesFetcher;
import ca.ulaval.glo4003.b6.housematch.estates.services.EstatesFetcherFactory;
import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.user.domain.Role;
import ca.ulaval.glo4003.b6.housematch.user.services.UserAuthorizationService;
import ca.ulaval.glo4003.b6.housematch.user.services.exceptions.InvalidAccessException;

@Controller
public class SellerEstateController {

   private static final String EXPECTED_ROLE = Role.SELLER;

   private EstateCorruptionVerificator estateCorruptionVerificator;

   private DescriptionCorruptionVerificator descriptionCorruptionVerificator;

   private UserAuthorizationService userAuthorizationService;

   private EstateAssemblerFactory estateAssembleFactory;

   private EstateRepositoryFactory estateRepositoryFactory;

   private EstatesFetcherFactory estatesFetcherFactory;

   @Autowired
   public SellerEstateController(EstateCorruptionVerificator estateCorruptionVerificator,
         UserAuthorizationService userAuthorizationService, EstateAssemblerFactory estateAssembleFactory,

   EstatesFetcherFactory estatesFetcherFactory, EstateRepositoryFactory estateRepositoryFactory,
         DescriptionCorruptionVerificator descriptionCorruptionVerificator) {

      this.estateCorruptionVerificator = estateCorruptionVerificator;
      this.userAuthorizationService = userAuthorizationService;
      this.estateAssembleFactory = estateAssembleFactory;
      this.estateRepositoryFactory = estateRepositoryFactory;
      this.estatesFetcherFactory = estatesFetcherFactory;

      this.descriptionCorruptionVerificator = descriptionCorruptionVerificator;

   }

   @RequestMapping(value = "/seller/{userId}/estates/add", method = RequestMethod.POST)
   public String addEstate(HttpServletRequest request, EstateDto estateDto, @PathVariable("userId") String userId)
         throws InvalidEstateFieldException, CouldNotAccessDataException, InvalidAccessException {

      userAuthorizationService.verifySessionIsAllowed(request, EXPECTED_ROLE);
      estateDto.setSellerId(userId);

      estateCorruptionVerificator.addEstate(estateDto);

      return "redirect:/seller/" + userId + "/estates";
   }

   @RequestMapping(value = "/seller/{userId}/estates/add", method = RequestMethod.GET)
   public String getSellEstatePage(HttpServletRequest request, Model model) throws InvalidAccessException {
      userAuthorizationService.verifySessionIsAllowed(request, EXPECTED_ROLE);
      model.addAttribute("estate", new EstateDto());
      return "sell_estate";
   }

   @RequestMapping(value = "/seller/{userId}/estates", method = RequestMethod.GET)
   public ModelAndView getSellerEstates(@PathVariable("userId") String userId, HttpServletRequest request)
         throws SellerNotFoundException, CouldNotAccessDataException, InvalidAccessException {

      userAuthorizationService.verifySessionIsAllowed(request, EXPECTED_ROLE);

      EstatesFetcher estatesFetcher = estatesFetcherFactory.newInstance(estateAssembleFactory, estateRepositoryFactory);
      List<EstateDto> estatesFromSeller = estatesFetcher.getEstatesBySeller(userId);

      ModelAndView sellerEstatesViewModel = new ModelAndView("seller_estates");
      sellerEstatesViewModel.addObject("estates", estatesFromSeller);

      return sellerEstatesViewModel;
   }

   @RequestMapping(value = "/seller/{userId}/estates/{address}", method = RequestMethod.GET)
   public ModelAndView getEstateByAddress(@PathVariable("address") String address, HttpServletRequest request)
         throws EstateNotFoundException, CouldNotAccessDataException, InvalidAccessException {

      userAuthorizationService.verifySessionIsAllowed(request, EXPECTED_ROLE);

      EstatesFetcher estatesFetcher = estatesFetcherFactory.newInstance(estateAssembleFactory, estateRepositoryFactory);
      EstateDto estateByAddress = estatesFetcher.getEstateByAddress(address);

      DescriptionDto descriptionDto = estateByAddress.getDescriptionDto();

      ModelAndView sellerEstateViewModel = new ModelAndView("estate");
      sellerEstateViewModel.addObject("estate", estateByAddress);
      sellerEstateViewModel.addObject("description", descriptionDto);

      return sellerEstateViewModel;
   }

   @RequestMapping(value = "/seller/{userId}/estates/{address}", method = RequestMethod.POST)
   public String editDescription(@PathVariable("address") String address, HttpServletRequest request,
         @ModelAttribute("description") DescriptionDto descriptionDto) throws InvalidEstateFieldException,
               CouldNotAccessDataException, InvalidAccessException, InvalidDescriptionFieldException,
               InvalidDescriptionException, EstateNotFoundException, InvalidEstateException {
      System.out.println(descriptionDto.getYearOfConstruction());
      userAuthorizationService.verifySessionIsAllowed(request, EXPECTED_ROLE);

      descriptionCorruptionVerificator.editDescription(address, descriptionDto);

      return "redirect:/seller/{userId}/estates/{address}";
   }
}
