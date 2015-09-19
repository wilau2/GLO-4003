package ca.ulaval.glo4003.b6.housematch.estates.anticorruption;

import org.springframework.beans.factory.annotation.Autowired;

import ca.ulaval.glo4003.b6.housematch.estates.anticorruption.exceptions.InvalidEstateFieldException;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.InvalidEstateException;
import ca.ulaval.glo4003.b6.housematch.estates.services.EstatesService;

public class EstateCorruptionVerificator {

   private EstatesService estateService;

   @Autowired
   public EstateCorruptionVerificator(EstatesService estateService) {
      this.estateService = estateService;
   }

   public void addEstate(EstateDto estateDto) throws InvalidEstateFieldException {
      validateEstateCorruption(estateDto);

      try {
         estateService.addEstate(estateDto);
      } catch (InvalidEstateException e) {
         System.out.println(e.getMessage());
         throw new InvalidEstateFieldException(e.getMessage(), e);

      }
   }

   private void validateEstateCorruption(EstateDto estateDto) throws InvalidEstateFieldException {
      String address = estateDto.getAddress();
      if (address == null || address.isEmpty()) {
         throw new InvalidEstateFieldException("The entered address is null");
      }
      String type = estateDto.getType();
      if (type == null || type.isEmpty()) {
         throw new InvalidEstateFieldException("The selected type is null");
      }
      int price = estateDto.getPrice();
      if (price < 0) {
         throw new InvalidEstateFieldException("The price was negative");
      }
   }

}
