package ca.ulaval.glo4003.b6.housematch.estates.dto.validators;

import java.util.Arrays;
import java.util.List;

import ca.ulaval.glo4003.b6.housematch.estates.exceptions.InvalidEstateException;

public class EstateValidator {

   private List<String> estateList = Arrays.asList("CONDO", "APPARTMENT", "SINGLE_FAMILY", "MULTIPLEX", "LOT", "FARM",
         "COTTAGE", "COMMERCIAL");

   public void validate(String type, String address, Integer price) throws InvalidEstateException {
      validateType(type);
      validateAddress(address);
      validatePrice(price);
   }

   private void validateType(String type) throws InvalidEstateException {
      if (!estateList.contains(type)) {
         throw new InvalidEstateException();
      }
   }

   private void validateAddress(String address) throws InvalidEstateException {
      if (address == "") {
         throw new InvalidEstateException();
      }
   }

   private void validatePrice(Integer price) throws InvalidEstateException {
      if (price < 1) {
         throw new InvalidEstateException();
      }
   }

}
