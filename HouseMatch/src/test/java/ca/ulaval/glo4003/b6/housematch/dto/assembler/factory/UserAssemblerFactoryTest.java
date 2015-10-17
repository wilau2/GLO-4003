package ca.ulaval.glo4003.b6.housematch.dto.assembler.factory;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.dto.assembler.UserAssembler;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.factory.UserAssemblerFactory;

public class UserAssemblerFactoryTest {

   @InjectMocks
   UserAssemblerFactory userAssemblerFactory;

   @Mock
   private UserAssembler userAssembler;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);
   }

   @Test
   public void whenCreateUserAssemblerShouldReturnRightInstance() {
      // Given

      // When
      userAssembler = userAssemblerFactory.createUserAssembler();

      // Then
      assertTrue(userAssembler instanceof UserAssembler);
   }
}
