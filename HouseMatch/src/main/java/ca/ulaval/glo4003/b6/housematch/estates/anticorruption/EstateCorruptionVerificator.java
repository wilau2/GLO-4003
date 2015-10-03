package ca.ulaval.glo4003.b6.housematch.estates.anticorruption;

import org.springframework.beans.factory.annotation.Autowired;

import ca.ulaval.glo4003.b6.housematch.estates.anticorruption.exceptions.AddressFieldInvalidException;
import ca.ulaval.glo4003.b6.housematch.estates.anticorruption.exceptions.InvalidEstateFieldException;
import ca.ulaval.glo4003.b6.housematch.estates.dto.AddressDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.InvalidEstateException;
import ca.ulaval.glo4003.b6.housematch.estates.services.EstatesService;
import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;

public class EstateCorruptionVerificator {

   private EstatesService estateService;
   
   private AddressCorruptionVerificator addressCorruptionVerificator;

   @Autowired
   public EstateCorruptionVerificator(EstatesService estateService, AddressCorruptionVerificator addressCorruptionVerificator) {
      this.estateService = estateService;
      this.addressCorruptionVerificator = addressCorruptionVerificator;
   }

   public void addEstate(EstateDto estateDto) throws InvalidEstateFieldException, CouldNotAccessDataException {
      validateEstateCorruption(estateDto);

      try {
         estateService.addEstate(estateDto);
      } catch (InvalidEstateException e) {
         throw new InvalidEstateFieldException(e.getMessage(), e);
      }
   }

   private void validateEstateCorruption(EstateDto estateDto) throws InvalidEstateFieldException {
      validateAddress(estateDto);
      validateType(estateDto);
      validatePrice(estateDto);
      validateSeller(estateDto);
   }

   private void validateSeller(EstateDto estateDto) throws InvalidEstateFieldException {
      String seller = estateDto.getSeller();
      if (seller == null || seller.isEmpty()) {
         throw new InvalidEstateFieldException("Seller is invalid");
      }
   }

   private void validatePrice(EstateDto estateDto) throws InvalidEstateFieldException {
      int price = estateDto.getPrice();
      if (price < 0) {
         throw new InvalidEstateFieldException("The price was negative");
      }
   }

   private void validateType(EstateDto estateDto) throws InvalidEstateFieldException {
      String type = estateDto.getType();
      if (type == null || type.isEmpty()) {
         throw new InvalidEstateFieldException("The selected type is empty");
      }
   }

   private void validateAddress(EstateDto estateDto) throws InvalidEstateFieldException {
      AddressDto address = estateDto.getAddress();    
      if (address == null) {
         throw new InvalidEstateFieldException("The entered address is empty");
      }
      try {
         addressCorruptionVerificator.validate(address);
      } catch (AddressFieldInvalidException exception) {
         throw new InvalidEstateFieldException("addres fields are invalid", exception);
      }
   }
   
   

}
