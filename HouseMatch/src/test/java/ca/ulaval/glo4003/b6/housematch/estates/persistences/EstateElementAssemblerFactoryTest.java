package ca.ulaval.glo4003.b6.housematch.estates.persistences;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

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
