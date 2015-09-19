package ca.ulaval.glo4003.b6.housematch.estates.dto.validators;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class EstateValidatorFactoryTest {

   private EstateValidatorFactory estateValidatorFactory;

   @Before
   public void setup() {
      estateValidatorFactory = new EstateValidatorFactory();
   }

   @Test
   public void whenGetEstateValidatorShouldReturnNewValidator() {
      // Given no change

      // When
      Object returnedObject = estateValidatorFactory.getValidator();

      // Then
      assertTrue(returnedObject instanceof EstateValidator);
   }

}
