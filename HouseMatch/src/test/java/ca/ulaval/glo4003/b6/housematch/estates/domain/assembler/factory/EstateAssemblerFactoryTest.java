package ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.factory;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.AddressAssembler;
import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.EstateAssembler;

public class EstateAssemblerFactoryTest {

   @Mock
   private AddressAssembler addressAssembler;

   private EstateAssemblerFactory estateAssemblerFactory;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);

      estateAssemblerFactory = new EstateAssemblerFactory();

   }

   @Test
   public void whenCreatingAnEstateAssemblerShouldReturnInstanceOfEstateAssembler() {
      // Given

      // When
      Object returnedObject = estateAssemblerFactory.createEstateAssembler();

      // Then
      assertTrue(returnedObject instanceof EstateAssembler);
   }

   @Test
   public void whenCreatingMultipleEstateAssemblerShouldNotReturnSameObject() {
      // Given

      // When
      EstateAssembler firstReturnedAssembler = estateAssemblerFactory.createEstateAssembler();
      EstateAssembler secondReturnedAssembler = estateAssemblerFactory.createEstateAssembler();

      // Then
      assertNotEquals(secondReturnedAssembler, firstReturnedAssembler);
   }
}
