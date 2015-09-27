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

   private void validateEstateCorruption(EstateDto estateDto) throws InvalidEstateFieldException {
      AddressDto addressDto = estateDto.getAddress();
      if (addressDto == null) {
         throw new InvalidEstateFieldException("Address is invalid");
      }
      try {
         addressCorruptionVerificator.validate(addressDto);
      } catch (AddressFieldInvalidException exception) {
         throw new InvalidEstateFieldException("Address fields are invalid", exception);
      }

      String type = estateDto.getType();
      if (type == null || type.isEmpty()) {
         throw new InvalidEstateFieldException("The selected type is empty");
      }
      int price = estateDto.getPrice();
      if (price < 0) {
         throw new InvalidEstateFieldException("The price was negative");
      }
      String seller = estateDto.getSeller();
      if (seller == null || seller.isEmpty()) {
         throw new InvalidEstateFieldException("Seller is invalid");
      }
   }

}
