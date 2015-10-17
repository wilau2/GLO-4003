package ca.ulaval.glo4003.b6.housematch.services.estate.validators.factory;

import ca.ulaval.glo4003.b6.housematch.services.estate.validators.DescriptionValidator;

public class DescriptionValidatorFactory {

   public DescriptionValidator createValidator() {
      return new DescriptionValidator();
   }
}

