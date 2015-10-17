package ca.ulaval.glo4003.b6.housematch.persistance.estate.converter;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.persistance.estate.converter.EstateElementConverter;
import ca.ulaval.glo4003.b6.housematch.persistance.estate.converter.EstateElementConverterFactory;

public class EstateElementConverterFactoryTest {

   @InjectMocks
   private EstateElementConverterFactory estateElementAssemblerFactory;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);
   }

   @Test
   public void whenGettingNewEstateElementAssemblerShouldReturnGoodInstance() {
      // Given no changes

      // When
      Object returnedObject = estateElementAssemblerFactory.createAssembler();

      // Then
      assertTrue(returnedObject instanceof EstateElementConverter);
   }
}
