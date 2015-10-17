package ca.ulaval.glo4003.b6.housematch.dto.assembler.factory;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.b6.housematch.dto.assembler.DescriptionAssembler;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.factory.DescriptionAssemblerFactory;


public class DescriptionAssemblerFactoryTest {
   
   private DescriptionAssemblerFactory descriptionAssemblerFactory;

   @Before
   public void setup() {
      descriptionAssemblerFactory = new DescriptionAssemblerFactory();
   }

   @Test
   public void whenCreatingAnDescriptionAssemblerShouldReturnInstanceOfDescriptionAssembler() {
      // Given

      // When
      Object returnedObject = descriptionAssemblerFactory.createDescriptionAssembler();

      // Then
      assertTrue(returnedObject instanceof DescriptionAssembler);
   }
   

   @Test
   public void whenCreatingMultipleEstateAssemblerShouldNotReturnSameObject() {
      // Given

      // When
      DescriptionAssembler firstReturnedAssembler = descriptionAssemblerFactory.createDescriptionAssembler();
      DescriptionAssembler secondReturnedAssembler = descriptionAssemblerFactory.createDescriptionAssembler();

      // Then
      assertNotEquals(secondReturnedAssembler, firstReturnedAssembler);
   }

}
