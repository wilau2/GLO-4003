package ca.ulaval.glo4003.b6.housematch.dto.assembler.factory;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.dto.assembler.ContactInformationAssembler;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.factory.ContactInformationAssemblerFactory;

public class ContactInformationAssemblerFactoryTest {

   @InjectMocks
   ContactInformationAssemblerFactory contactInformationAssemblerFactory;

   @Mock
   private ContactInformationAssembler contactInformationAssembler;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);
   }

   @Test
   public void whenCreateUserAssemblerShouldReturnRightInstance() {
      // Given

      // When
      contactInformationAssembler = contactInformationAssemblerFactory.createContactInformationAssembler();

      // Then
      assertTrue(contactInformationAssembler instanceof ContactInformationAssembler);
   }
}
