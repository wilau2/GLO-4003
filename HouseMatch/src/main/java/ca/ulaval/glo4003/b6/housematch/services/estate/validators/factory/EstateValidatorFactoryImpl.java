package ca.ulaval.glo4003.b6.housematch.services.estate.validators.factory;

import ca.ulaval.glo4003.b6.housematch.services.estate.validators.EstateValidator;

public class EstateValidatorFactoryImpl implements EstateValidatorFactory {

   public EstateValidator getValidator() {
      return new EstateValidator();
   }

}
