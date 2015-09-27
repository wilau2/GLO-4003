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

import ca.ulaval.glo4003.b6.housematch.estates.anticorruption.EstateCorruptionVerificator;
import ca.ulaval.glo4003.b6.housematch.estates.anticorruption.exceptions.InvalidEstateFieldException;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.SellerNotFoundException;
import ca.ulaval.glo4003.b6.housematch.estates.services.EstatesFetcher;
import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.web.converters.EstateConverter;
import ca.ulaval.glo4003.b6.housematch.web.viewModel.EstateModel;

@Controller
public class SellerEstateController {

   private EstateConverter estateConverter;

   private EstateCorruptionVerificator estateCorruptionVerificator;

   private EstatesFetcher estatesFetcher;

   @Autowired
   public SellerEstateController(EstateConverter estateConverter,
         EstateCorruptionVerificator estateCorruptionVerificator, EstatesFetcher estatesFetcher) {
      this.estateConverter = estateConverter;
      this.estateCorruptionVerificator = estateCorruptionVerificator;
      this.estatesFetcher = estatesFetcher;
   }

   @RequestMapping(value = "/seller/{userId}/estates/add", method = RequestMethod.POST)
   public String addEstate(HttpServletRequest request, EstateModel estateModel, @PathVariable("userId") String userId)
         throws InvalidEstateFieldException, CouldNotAccessDataException {
      estateModel.setSeller(userId);
      EstateDto estateDto = estateConverter.convertToDto(estateModel);
      estateCorruptionVerificator.addEstate(estateDto);
      return "redirect:/";
   }

   @RequestMapping(value = "/seller/{userId}/estates/add", method = RequestMethod.GET)
   public String getSellEstatePage(Model model) {
      model.addAttribute("estate", new EstateModel());
      return "sell_estate";
   }

   @RequestMapping(value = "/seller/{userId}/estates", method = RequestMethod.GET)
   public String getSellerEstates(@PathVariable("userId") String userId, Model model)
         throws SellerNotFoundException, CouldNotAccessDataException {
      List<EstateDto> estatesFromSeller = estatesFetcher.getEstatesBySeller(userId);

      List<EstateModel> estatesModel = new ArrayList<EstateModel>();
      for (EstateDto estateDto : estatesFromSeller) {
         estatesModel.add(estateConverter.convertToModel(estateDto));
      }
      model.addAttribute("estates", estatesModel);
      return "seller_estates";

   }

}
