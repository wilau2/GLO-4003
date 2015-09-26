package ca.ulaval.glo4003.b6.housematch.user.domain.assembler.factory;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.user.domain.assembler.UserAssembler;

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
      assert(userAssembler instanceof UserAssembler);
   }
}
