package ca.ulaval.glo4003.b7.housematch.estates.dto.validators;


import java.util.Arrays;
import java.util.List;

import ca.ulaval.glo4003.b7.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b7.housematch.estates.exceptions.InvalidEstateException;

public class EstateValidator {

   private List<String> estateList = Arrays.asList("CONDO", "APPARTMENT", "SINGLE_FAMILY", "MULTIPLEX", "LOT", "FARM", "COTTAGE", "COMMERCIAL");
   
   public void validate(EstateDto estateDto) throws InvalidEstateException {
      if(!estateList.contains(estateDto.getType())){
          throw new InvalidEstateException();
      }
   }

}
