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

import ca.ulaval.glo4003.b6.housematch.estates.anticorruption.EstateCorruptionVerificator;
import ca.ulaval.glo4003.b6.housematch.estates.anticorruption.exceptions.InvalidEstateFieldException;
import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.EstateNotFoundException;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.SellerNotFoundException;
import ca.ulaval.glo4003.b6.housematch.estates.repository.factory.EstateRepositoryFactory;
import ca.ulaval.glo4003.b6.housematch.estates.services.EstatesFetcher;
import ca.ulaval.glo4003.b6.housematch.estates.services.EstatesFetcherFactory;
import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.user.domain.Role;
import ca.ulaval.glo4003.b6.housematch.user.services.UserAuthorizationService;
import ca.ulaval.glo4003.b6.housematch.user.services.exceptions.InvalidAccessException;
import ca.ulaval.glo4003.b6.housematch.web.converters.EstateConverter;
import ca.ulaval.glo4003.b6.housematch.web.viewModel.EstateModel;

@Controller
public class SellerEstateController {

   private static final String EXPECTED_ROLE = Role.SELLER;

   private EstateConverter estateConverter;

   private EstateCorruptionVerificator estateCorruptionVerificator;

   private UserAuthorizationService userAuthorizationService;

   private EstateAssemblerFactory estateAssembleFactory;

   private EstateRepositoryFactory estateRepositoryFactory;

   private EstatesFetcherFactory estatesFetcherFactory;

   @Autowired
   public SellerEstateController(EstateConverter estateConverter,
         EstateCorruptionVerificator estateCorruptionVerificator, UserAuthorizationService userAuthorizationService,
         EstateAssemblerFactory estateAssembleFactory, EstatesFetcherFactory estatesFetcherFactory,
         EstateRepositoryFactory estateRepositoryFactory) {
      this.estateConverter = estateConverter;
      this.estateCorruptionVerificator = estateCorruptionVerificator;
      this.userAuthorizationService = userAuthorizationService;
      this.estateAssembleFactory = estateAssembleFactory;
      this.estateRepositoryFactory = estateRepositoryFactory;
      this.estatesFetcherFactory = estatesFetcherFactory;
   }

   @RequestMapping(value = "/seller/{userId}/estates/add", method = RequestMethod.POST)
   public String addEstate(HttpServletRequest request, EstateModel estateModel, @PathVariable("userId") String userId)
         throws InvalidEstateFieldException, CouldNotAccessDataException, InvalidAccessException {

      userAuthorizationService.verifySessionIsAllowed(request, EXPECTED_ROLE);
      estateModel.setSeller(userId);

      EstateDto estateDto = estateConverter.convertToDto(estateModel);
      estateCorruptionVerificator.addEstate(estateDto);

      return "redirect:/";
   }

   @RequestMapping(value = "/seller/{userId}/estates/add", method = RequestMethod.GET)
   public String getSellEstatePage(HttpServletRequest request, Model model) throws InvalidAccessException {
      userAuthorizationService.verifySessionIsAllowed(request, EXPECTED_ROLE);
      model.addAttribute("estate", new EstateModel());
      return "sell_estate";
   }

   @RequestMapping(value = "/seller/{userId}/estates", method = RequestMethod.GET)
   public ModelAndView getSellerEstates(@PathVariable("userId") String userId, HttpServletRequest request)
         throws SellerNotFoundException, CouldNotAccessDataException, InvalidAccessException {

      userAuthorizationService.verifySessionIsAllowed(request, EXPECTED_ROLE);

      EstatesFetcher estatesFetcher = estatesFetcherFactory.newInstance(estateAssembleFactory, estateRepositoryFactory);
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

      userAuthorizationService.verifySessionIsAllowed(request, EXPECTED_ROLE);
      EstatesFetcher estatesFetcher = estatesFetcherFactory.newInstance(estateAssembleFactory, estateRepositoryFactory);
      EstateDto estateByAddress = estatesFetcher.getEstateByAddress(address);

      EstateModel estateModel = estateConverter.convertToModel(estateByAddress);

      ModelAndView estateViewModel = new ModelAndView("estate");
      estateViewModel.addObject("estate", estateModel);

      return estateViewModel;
   }

}
