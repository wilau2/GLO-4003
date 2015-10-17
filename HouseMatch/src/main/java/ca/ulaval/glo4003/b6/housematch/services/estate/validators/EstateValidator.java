package ca.ulaval.glo4003.b6.housematch.services.estate.validators;

import java.util.Arrays;
import java.util.List;

import ca.ulaval.glo4003.b6.housematch.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.services.estate.exceptions.InvalidEstateException;

public class EstateValidator {

   private List<String> estateList = Arrays.asList("CONDO", "APPARTMENT", "SINGLE_FAMILY", "MULTIPLEX", "LOT", "FARM",
         "COTTAGE", "COMMERCIAL");

   public void validate(EstateDto estateDto) throws InvalidEstateException {
      String type = estateDto.getType();
      if (!estateList.contains(type)) {
         throw new InvalidEstateException("Type is not supported");
      }
   }

}
