package ca.ulaval.glo4003.b6.housematch.web.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ca.ulaval.glo4003.b6.housematch.anticorruption.estate.DescriptionCorruptionVerificator;
import ca.ulaval.glo4003.b6.housematch.anticorruption.estate.EstateCorruptionVerificator;
import ca.ulaval.glo4003.b6.housematch.anticorruption.estate.exceptions.InvalidDescriptionFieldException;
import ca.ulaval.glo4003.b6.housematch.anticorruption.estate.exceptions.InvalidEstateFieldException;
import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.EstateNotFoundException;
import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.SellerNotFoundException;
import ca.ulaval.glo4003.b6.housematch.domain.user.Role;
import ca.ulaval.glo4003.b6.housematch.dto.DescriptionDto;
import ca.ulaval.glo4003.b6.housematch.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.dto.PictureDto;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.estate.EstatePicturesService;
import ca.ulaval.glo4003.b6.housematch.services.estate.EstatesFetcher;
import ca.ulaval.glo4003.b6.housematch.services.estate.exceptions.InvalidDescriptionException;
import ca.ulaval.glo4003.b6.housematch.services.estate.exceptions.InvalidEstateException;
import ca.ulaval.glo4003.b6.housematch.services.user.UserAuthorizationService;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.InvalidAccessException;

@Controller
public class SellerEstateController {

   private static final String EXPECTED_ROLE = Role.SELLER;

   private EstateCorruptionVerificator estateCorruptionVerificator;

   private DescriptionCorruptionVerificator descriptionCorruptionVerificator;

   private UserAuthorizationService userAuthorizationService;

   private EstatesFetcher estatesFetcher;

   private EstatePicturesService estatePicturesService;

   @Autowired
   public SellerEstateController(EstateCorruptionVerificator estateCorruptionVerificator,
         UserAuthorizationService userAuthorizationService, EstatesFetcher estatesFetcher,
         DescriptionCorruptionVerificator descriptionCorruptionVerificator,
         EstatePicturesService estatePicturesService) {

      this.estateCorruptionVerificator = estateCorruptionVerificator;
      this.userAuthorizationService = userAuthorizationService;
      this.estatesFetcher = estatesFetcher;
      this.descriptionCorruptionVerificator = descriptionCorruptionVerificator;
      this.estatePicturesService = estatePicturesService;
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

      List<EstateDto> estatesFromSeller = estatesFetcher.getEstatesBySeller(userId);

      ModelAndView sellerEstatesViewModel = new ModelAndView("seller_estates");
      sellerEstatesViewModel.addObject("estates", estatesFromSeller);

      return sellerEstatesViewModel;
   }

   @RequestMapping(value = "/seller/{userId}/estates/{address}", method = RequestMethod.GET)
   public ModelAndView getEstateByAddress(@PathVariable("address") String address, HttpServletRequest request)
         throws EstateNotFoundException, CouldNotAccessDataException, InvalidAccessException, IOException {

      userAuthorizationService.verifySessionIsAllowed(request, EXPECTED_ROLE);

      EstateDto estateByAddress = estatesFetcher.getEstateByAddress(address);

      DescriptionDto descriptionDto = estateByAddress.getDescriptionDto();

      List<PictureDto> pictures = estatePicturesService.getPicturesOfEstate(address);

      ModelAndView sellerEstateViewModel = new ModelAndView("estate");
      sellerEstateViewModel.addObject("estate", estateByAddress);
      sellerEstateViewModel.addObject("description", descriptionDto);
      sellerEstateViewModel.addObject("pictures", pictures);

      return sellerEstateViewModel;
   }

   @RequestMapping(value = "/seller/{userId}/estates/{address}", method = RequestMethod.POST)
   public String editDescription(@PathVariable("address") String address, HttpServletRequest request,
         @ModelAttribute("description") DescriptionDto descriptionDto) throws InvalidEstateFieldException,
               CouldNotAccessDataException, InvalidAccessException, InvalidDescriptionFieldException,
               InvalidDescriptionException, EstateNotFoundException, InvalidEstateException {

      userAuthorizationService.verifySessionIsAllowed(request, EXPECTED_ROLE);

      descriptionCorruptionVerificator.editDescription(address, descriptionDto);

      return "redirect:/seller/{userId}/estates/{address}";
   }

   @RequestMapping(value = "/seller/{userId}/estates/{address}/addPicture", method = RequestMethod.POST)
   public String addPicture(@PathVariable("address") String address, @RequestParam("name") final String name,
         @RequestParam("file") MultipartFile file, HttpServletRequest request)
               throws IOException, InvalidAccessException, EstateNotFoundException, CouldNotAccessDataException {

      userAuthorizationService.verifySessionIsAllowed(request, EXPECTED_ROLE);

      estatePicturesService.addPicture(address, name, file);

      return "redirect:/seller/{userId}/estates/{address}";
   }

   @RequestMapping(value = "/seller/{userId}/estates/{address}/deletePicture", method = RequestMethod.POST)
   public String deletePicture(@PathVariable("address") String address, @RequestParam("name") final String name,
         HttpServletRequest request)
               throws IOException, InvalidAccessException, EstateNotFoundException, CouldNotAccessDataException {

      userAuthorizationService.verifySessionIsAllowed(request, EXPECTED_ROLE);

      estatePicturesService.deletePicture(address, name);

      return "redirect:/seller/{userId}/estates/{address}";
   }

}
