package ca.ulaval.glo4003.b6.housematch.user.domain.assembler.factory;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.user.domain.assembler.ContactInformationAssembler;

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
      assert(contactInformationAssembler instanceof ContactInformationAssembler);
   }
}
