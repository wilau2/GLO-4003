package ca.ulaval.glo4003.b6.housematch.anticorruption.estate;

import javax.inject.Inject;

import com.google.common.base.Strings;

import ca.ulaval.glo4003.b6.housematch.anticorruption.estate.exceptions.AddressFieldInvalidException;
import ca.ulaval.glo4003.b6.housematch.anticorruption.estate.exceptions.InvalidEstateFieldException;
import ca.ulaval.glo4003.b6.housematch.dto.AddressDto;
import ca.ulaval.glo4003.b6.housematch.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.dto.EstateEditDto;

public class EstateCorruptionVerificator {

   private AddressCorruptionVerificator addressCorruptionVerificator;

   @Inject
   public EstateCorruptionVerificator(AddressCorruptionVerificator addressCorruptionVerificator) {
      this.addressCorruptionVerificator = addressCorruptionVerificator;
   }

   public void validateEstateCorruption(EstateDto estateDto) throws InvalidEstateFieldException {
      validateAddress(estateDto.getAddress());
      validateType(estateDto.getType());
      validatePrice(estateDto.getPrice());
      validateSeller(estateDto.getSeller());
   }

   public void validateEstateEditCorruption(EstateEditDto estateEditDto) throws InvalidEstateFieldException {
      validateType(estateEditDto.getType());
      validatePrice(estateEditDto.getPrice());
   }

   private void validateSeller(String seller) throws InvalidEstateFieldException {
      if (Strings.isNullOrEmpty(seller)) {
         throw new InvalidEstateFieldException("Seller is invalid");
      }
   }

   private void validatePrice(Integer price) throws InvalidEstateFieldException {
      if (price < 0) {
         throw new InvalidEstateFieldException("The price was negative");
      }
   }

   private void validateType(String type) throws InvalidEstateFieldException {
      if (Strings.isNullOrEmpty(type)) {
         throw new InvalidEstateFieldException("The selected type is empty");
      }
   }

   private void validateAddress(AddressDto address) throws InvalidEstateFieldException {
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
