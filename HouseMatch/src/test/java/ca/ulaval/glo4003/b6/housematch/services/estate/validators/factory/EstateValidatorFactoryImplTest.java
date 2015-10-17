package ca.ulaval.glo4003.b6.housematch.services.estate.validators.factory;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.b6.housematch.services.estate.validators.EstateValidator;
import ca.ulaval.glo4003.b6.housematch.services.estate.validators.factory.EstateValidatorFactoryImpl;

public class EstateValidatorFactoryImplTest {

   private EstateValidatorFactoryImpl estateValidatorFactory;

   @Before
   public void setup() {
      estateValidatorFactory = new EstateValidatorFactoryImpl();
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
