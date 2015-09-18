package ca.ulaval.glo4003.b6.housematch.estates.services;

import ca.ulaval.glo4003.b6.housematch.estates.EstateController;
import ca.ulaval.glo4003.b6.housematch.estates.assembler.EstateAssembler;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.validators.EstateValidator;

public class EstatesService {

   EstateValidator estateValidator;

   public EstatesService(EstateValidator estateValidator) {
      this.estateValidator = estateValidator;
   }

   public void addEstate(String type, String address, Integer price) {

      EstateController estateController = new EstateController();

      EstateAssembler estateAssembler = new EstateAssembler(estateValidator);
      EstateDto estateDto;
      try {
         estateDto = estateAssembler.createDTO(type, address, price);
         estateController.addEstate(estateDto);
      } catch (Exception e) {

      }
   }

   public void addEstate(EstateDto estateDto) {

   }

}
