package ca.ulaval.glo4003.b6.housematch.user.dto.validators.factory;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.user.dto.validators.UserValidator;

public class UserValidatorFactoryTest {

   @InjectMocks
   UserValidatorFactory userValidatorFactory;

   @Mock
   private UserValidator userValidator;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);
   }

   @Test
   public void whenCreateUserValidatorShouldReturnRightInstance() {
      // Given

      // When
      userValidator = userValidatorFactory.getValidator();

      // Then
      assert(userValidator instanceof UserValidator);
   }

}
