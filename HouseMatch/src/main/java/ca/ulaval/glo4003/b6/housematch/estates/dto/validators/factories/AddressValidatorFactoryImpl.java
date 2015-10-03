package ca.ulaval.glo4003.b6.housematch.estates.dto.validators.factories;

import ca.ulaval.glo4003.b6.housematch.estates.dto.validators.AddressValidator;

public class AddressValidatorFactoryImpl implements AddressValidatorFactory {

   public AddressValidator getValidator() {
      return new AddressValidator();
   }

}
