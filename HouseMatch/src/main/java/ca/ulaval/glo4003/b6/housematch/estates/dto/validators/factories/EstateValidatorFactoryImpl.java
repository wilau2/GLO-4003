package ca.ulaval.glo4003.b6.housematch.estates.dto.validators.factories;

import ca.ulaval.glo4003.b6.housematch.estates.dto.validators.EstateValidator;

public class EstateValidatorFactoryImpl implements EstateValidatorFactory {

   public EstateValidator getValidator() {
      return new EstateValidator();
   }

}
