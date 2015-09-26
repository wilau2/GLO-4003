package ca.ulaval.glo4003.b6.housematch.estates.dto.validators;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class AddressValidatorFactoryTest {

   private AddressValidatorFactory addressValidatorFactory;

   @Before
   public void setup() {
      addressValidatorFactory = new AddressValidatorFactory();
   }

   @Test
   public void whenRequestingAddressValidatorShouldReturnInstanceOfAddressValidator() {
      // Given

      // When
      Object returnedObject = addressValidatorFactory.getValidator();

      // Then
      assertTrue(returnedObject instanceof AddressValidator);
   }
}
