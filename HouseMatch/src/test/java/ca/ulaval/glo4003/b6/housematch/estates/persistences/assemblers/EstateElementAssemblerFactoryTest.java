package ca.ulaval.glo4003.b6.housematch.estates.persistences.assemblers;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.estates.persistences.assemblers.EstateElementAssembler;
import ca.ulaval.glo4003.b6.housematch.estates.persistences.assemblers.EstateElementAssemblerFactory;

public class EstateElementAssemblerFactoryTest {

   @InjectMocks
   private EstateElementAssemblerFactory estateElementAssemblerFactory;

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
      assertTrue(returnedObject instanceof EstateElementAssembler);
   }
}
