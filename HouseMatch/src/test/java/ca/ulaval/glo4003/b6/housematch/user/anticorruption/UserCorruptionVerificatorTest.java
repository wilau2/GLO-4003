package ca.ulaval.glo4003.b6.housematch.user.anticorruption;

import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.user.anticorruption.exceptions.InvalidUserLoginFieldException;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserDto;
import ca.ulaval.glo4003.b6.housematch.user.service.UserLoginService;

public class UserCorruptionVerificatorTest {

   private static final String USERNAME = "username";

   private static final String PASSWORD = "password";

   private static final String EMPTY_FIELD = null;

   @Mock
   UserDto userDto;

   @Mock
   UserLoginService userLoginService;

   @InjectMocks
   private UserCorruptionVerificator userCorruptionVerificator;

   @Mock
   HttpServletRequest request;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);
      configureValidUserLoginModel();
   }

   private void configureValidUserLoginModel() {
      when(userDto.getUsername()).thenReturn(USERNAME);
      when(userDto.getPassword()).thenReturn(PASSWORD);
   }

   @Test(expected = InvalidUserLoginFieldException.class)
   public void verificatingUserLoginCorruptionWhenUserHasNoUsernameShouldThrowException()
         throws InvalidUserLoginFieldException {
      // Given
      when(userDto.getUsername()).thenReturn(null);

      // When
      userCorruptionVerificator.login(request, userDto);

      // Then InvalidUserLoginFieldException is thrown
   }

}
