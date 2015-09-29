package ca.ulaval.glo4003.b6.housematch.estates.dto.validators.factories;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.b6.housematch.estates.dto.validators.AddressValidator;

public class AddressValidatorFactoryImplTest {

   private AddressValidatorFactory addressValidatorFactoryImpl;

   @Before
   public void setup() {
      addressValidatorFactoryImpl = new AddressValidatorFactoryImpl();
   }

   @Test
   public void whenRequestingAddressValidatorShouldReturnInstanceOfAddressValidator() {
      // Given

      // When
      Object returnedObject = addressValidatorFactoryImpl.getValidator();

      // Then
      assertTrue(returnedObject instanceof AddressValidator);
   }
}
