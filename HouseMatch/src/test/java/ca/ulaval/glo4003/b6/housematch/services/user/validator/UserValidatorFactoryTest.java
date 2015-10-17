package ca.ulaval.glo4003.b6.housematch.services.user.validator;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
      assertTrue(userValidator instanceof UserValidator);
   }

}
