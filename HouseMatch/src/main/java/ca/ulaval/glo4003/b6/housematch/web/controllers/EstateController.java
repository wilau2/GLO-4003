package ca.ulaval.glo4003.b6.housematch.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.ulaval.glo4003.b6.housematch.estates.anticorruption.EstateCorruptionVerificator;
import ca.ulaval.glo4003.b6.housematch.estates.anticorruption.exceptions.InvalidEstateFieldException;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.web.converters.EstateConverter;
import ca.ulaval.glo4003.b6.housematch.web.viewModel.EstateModel;

@Controller
public class EstateController {

   private EstateConverter estateConverter;

   private EstateCorruptionVerificator estateCorruptionVerificator;

   @Autowired
   public EstateController(EstateConverter estateConverter, EstateCorruptionVerificator estateCorruptionVerificator) {
      this.estateConverter = estateConverter;
      this.estateCorruptionVerificator = estateCorruptionVerificator;
   }

   @RequestMapping(value = "/sell", method = RequestMethod.POST)
   public String addEstate(HttpServletRequest request, EstateModel estateModel) throws InvalidEstateFieldException {
      EstateDto estateDto = estateConverter.convertToDto(estateModel);
      estateCorruptionVerificator.addEstate(estateDto);
      return "redirect:/";
   }

   @RequestMapping(value = "/sell", method = RequestMethod.GET)
   public String getSellEstatePage(Model model) {
      model.addAttribute("estate", new EstateModel());
      return "sell_estate";
   }

}
