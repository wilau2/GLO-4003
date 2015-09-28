package ca.ulaval.glo4003.b6.housematch.estates.dto.validators.factories;

import ca.ulaval.glo4003.b6.housematch.estates.dto.validators.AddressValidator;

public interface AddressValidatorFactory {

   AddressValidator getValidator();

}
