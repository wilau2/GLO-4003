package ca.ulaval.glo4003.b6.housematch.estates.anticorruption;

import ca.ulaval.glo4003.b6.housematch.estates.anticorruption.exceptions.AddressFieldInvalidException;
import ca.ulaval.glo4003.b6.housematch.estates.dto.AddressDto;

public class AddressCorruptionVerificator {

   private static final int DEFAULT_APPARTMENT = 0;

   public void validate(AddressDto addressDto) throws AddressFieldInvalidException {
      String street = addressDto.getStreet();
      if (street == null || street.isEmpty()) {
         throw new AddressFieldInvalidException("Street name is empty");
      }
      Integer civicNumber = addressDto.getCivicNumber();
      if (civicNumber < 0) {
         throw new AddressFieldInvalidException("Civic number is below 0");
      }

      String country = addressDto.getCountry();
      if (country == null || country.isEmpty()) {
         throw new AddressFieldInvalidException("Country name is empty");
      }

      String postalCode = addressDto.getPostalCode();
      if (postalCode == null || postalCode.isEmpty()) {
         throw new AddressFieldInvalidException("Postal code is empty");
      }
      String state = addressDto.getState();
      if (state == null || state.isEmpty()) {
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
