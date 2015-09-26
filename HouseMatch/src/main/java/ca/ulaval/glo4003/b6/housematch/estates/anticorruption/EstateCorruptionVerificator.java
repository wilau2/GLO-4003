package ca.ulaval.glo4003.b6.housematch.estates.anticorruption;

import org.springframework.beans.factory.annotation.Autowired;

import ca.ulaval.glo4003.b6.housematch.estates.anticorruption.exceptions.InvalidAddressFieldException;
import ca.ulaval.glo4003.b6.housematch.estates.anticorruption.exceptions.InvalidEstateFieldException;
import ca.ulaval.glo4003.b6.housematch.estates.dto.AddressDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.InvalidEstateException;
import ca.ulaval.glo4003.b6.housematch.estates.services.EstatesService;

public class EstateCorruptionVerificator {

   private EstatesService estateService;

   @Autowired
   public EstateCorruptionVerificator(EstatesService estateService) {
      this.estateService = estateService;
   }

   public void addEstate(EstateDto estateDto) throws InvalidEstateFieldException, InvalidAddressFieldException {
      validateAddingEstateCorruption(estateDto);

      try {
         estateService.addEstate(estateDto);
      } catch (InvalidEstateException e) {
         System.out.println(e.getMessage());
         throw new InvalidEstateFieldException(e.getMessage(), e);

      }
   }
   
   public void editEstate(EstateDto estateDto) throws InvalidEstateFieldException, InvalidAddressFieldException {
      validateAddingEstateCorruption(estateDto);
      
      try {
         estateService.editEstate(estateDto);
      } catch (InvalidEstateException e) {
         System.out.println(e.getMessage());
         throw new InvalidEstateFieldException(e.getMessage(), e);

      }
   }

   private void validateAddingEstateCorruption(EstateDto estateDto) throws InvalidEstateFieldException, 
   InvalidAddressFieldException {
      
      String seller = estateDto.getSeller();
      if (seller == null || seller.isEmpty()) {
         throw new InvalidEstateFieldException("Seller is invalid");
      }
      Integer price = estateDto.getPrice();
      if ((price == null) || (price < 0)) {
         throw new InvalidEstateFieldException("The price was negative or null");
      }
      String type = estateDto.getType();
      if (type == null || type.isEmpty()) {
         throw new InvalidEstateFieldException("The selected type is empty");
      }
      validateAddress(estateDto);
      
   }

   private void validateAddress(EstateDto estateDto) throws InvalidAddressFieldException{
      AddressDto address = estateDto.getAddress();
      
      Integer appartment = address.getAppartment();
      if(appartment == null || appartment < 0){
         throw new InvalidAddressFieldException("The entered appartment is empty");
      }

      Integer civicNumber = address.getCivicNumber();
      
      }
}


