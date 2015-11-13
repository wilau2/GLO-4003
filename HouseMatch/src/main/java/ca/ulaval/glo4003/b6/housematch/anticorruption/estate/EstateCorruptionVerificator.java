package ca.ulaval.glo4003.b6.housematch.anticorruption.estate;

import javax.inject.Inject;

import ca.ulaval.glo4003.b6.housematch.anticorruption.estate.exceptions.AddressFieldInvalidException;
import ca.ulaval.glo4003.b6.housematch.anticorruption.estate.exceptions.InvalidDescriptionFieldException;
import ca.ulaval.glo4003.b6.housematch.anticorruption.estate.exceptions.InvalidEstateFieldException;
import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.EstateNotFoundException;
import ca.ulaval.glo4003.b6.housematch.dto.AddressDto;
import ca.ulaval.glo4003.b6.housematch.dto.DescriptionDto;
import ca.ulaval.glo4003.b6.housematch.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.dto.EstateEditDto;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.estate.EstatesService;
import ca.ulaval.glo4003.b6.housematch.services.estate.exceptions.InvalidDescriptionException;
import ca.ulaval.glo4003.b6.housematch.services.estate.exceptions.InvalidEstateException;

public class EstateCorruptionVerificator {

   private EstatesService estateService;

   private AddressCorruptionVerificator addressCorruptionVerificator;

   @Inject
   public EstateCorruptionVerificator(EstatesService estateService,
         AddressCorruptionVerificator addressCorruptionVerificator) {
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
   
   public void editEstate(String address, EstateEditDto estateEditDto) throws InvalidEstateFieldException, EstateNotFoundException, CouldNotAccessDataException {
      validateEstateEditCorruption(estateEditDto);
      estateService.editEstate(address, estateEditDto);
      
}

   private void validateEstateCorruption(EstateDto estateDto) throws InvalidEstateFieldException {
      validateAddress(estateDto.getAddress());
      validateType(estateDto.getType());
      validatePrice(estateDto.getPrice());
      validateSeller(estateDto.getSeller());
   }
   
   private void validateEstateEditCorruption(EstateEditDto estateEditDto) throws InvalidEstateFieldException {
      validateType(estateEditDto.getType());
      validatePrice(estateEditDto.getPrice());
   }

   private void validateSeller(String seller) throws InvalidEstateFieldException {
      if (seller == null || seller.isEmpty()) {
         throw new InvalidEstateFieldException("Seller is invalid");
      }
   }

   private void validatePrice(Integer price) throws InvalidEstateFieldException {
      if (price < 0) {
         throw new InvalidEstateFieldException("The price was negative");
      }
   }

   private void validateType(String type) throws InvalidEstateFieldException {
      if (type == null || type.isEmpty()) {
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
