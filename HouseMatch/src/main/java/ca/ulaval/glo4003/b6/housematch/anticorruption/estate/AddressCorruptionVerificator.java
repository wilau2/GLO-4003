package ca.ulaval.glo4003.b6.housematch.anticorruption.estate;

import com.google.common.base.Strings;

import ca.ulaval.glo4003.b6.housematch.anticorruption.estate.exceptions.AddressFieldInvalidException;
import ca.ulaval.glo4003.b6.housematch.dto.AddressDto;

public class AddressCorruptionVerificator {

   private static final int DEFAULT_APPARTMENT = 0;

   public void validate(AddressDto addressDto) throws AddressFieldInvalidException {
      String street = addressDto.getStreet();
      if (Strings.isNullOrEmpty(street)) {
         throw new AddressFieldInvalidException("Street name is empty");
      }
      Integer civicNumber = addressDto.getCivicNumber();
      if (civicNumber < 0) {
         throw new AddressFieldInvalidException("Civic number is below 0");
      }

      String country = addressDto.getCountry();
      if (Strings.isNullOrEmpty(country)) {
         throw new AddressFieldInvalidException("Country name is empty");
      }

      String postalCode = addressDto.getPostalCode();
      if (Strings.isNullOrEmpty(postalCode)) {
         throw new AddressFieldInvalidException("Postal code is empty");
      }

      String state = addressDto.getState();
      if (Strings.isNullOrEmpty(state)) {
         throw new AddressFieldInvalidException("State name is empty");
      }

      Integer appartment = addressDto.getAppartment();
      if (appartment == null) {
         addressDto.setAppartment(DEFAULT_APPARTMENT);

      } else
         if (appartment < 0) {
            throw new AddressFieldInvalidException("Appartment number is negative");
         }
   }

}
