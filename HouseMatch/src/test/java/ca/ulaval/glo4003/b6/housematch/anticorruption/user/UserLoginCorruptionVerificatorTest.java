package ca.ulaval.glo4003.b6.housematch.anticorruption.user;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.anticorruption.user.exceptions.InvalidUserLoginFieldException;
import ca.ulaval.glo4003.b6.housematch.domain.user.exceptions.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.dto.UserDto;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.user.UserLoginService;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.InvalidPasswordException;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.UserActivationException;

public class UserLoginCorruptionVerificatorTest {

   private static final String USERNAME = "username";

   private static final String PASSWORD = "password";

   private static final String EMPTY_FIELD = "";

   @Mock
   UserDto userDto;

   @Mock
   UserLoginService userLoginService;

   @Mock
   private HttpServletRequest request;

   @InjectMocks
   private UserLoginCorruptionVerificator userCorruptionVerificator;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);
      configureValidUserLoginModel();
   }

   private void configureValidUserLoginModel() {
      when(userDto.getUsername()).thenReturn(USERNAME);
      when(userDto.getPassword()).thenReturn(PASSWORD);
   }

   @Test
   public void verificatingUserLoginCorruptionWhenUserLoginIsValidShouldCallServiceLogin()
         throws InvalidUserLoginFieldException, UserNotFoundException, CouldNotAccessDataException,
         InvalidPasswordException, UserActivationException {

      // Given

      // When
      userCorruptionVerificator.login(request, userDto);
      // Then
      verify(userLoginService).login(request, userDto);

   }

   @Test(expected = InvalidUserLoginFieldException.class)
   public void verificatingUserLoginCorruptionWhenUserHasNoUsernameShouldThrowException()
         throws InvalidUserLoginFieldException, UserNotFoundException, CouldNotAccessDataException,
         InvalidPasswordException, UserActivationException {

      // Given
      when(userDto.getUsername()).thenReturn(null);

      // When
      userCorruptionVerificator.login(request, userDto);

      // Then InvalidUserLoginFieldException is thrown
   }

   @Test(expected = InvalidUserLoginFieldException.class)
   public void verificatingUserLoginCorruptionWhenUserHasEmptyUsernameShouldThrowException()
         throws InvalidUserLoginFieldException, UserNotFoundException, CouldNotAccessDataException,
         InvalidPasswordException, UserActivationException {

      // Given
      when(userDto.getUsername()).thenReturn(EMPTY_FIELD);

      // When
      userCorruptionVerificator.login(request, userDto);

      // Then InvalidUserLoginFieldException is thrown
   }

   @Test(expected = InvalidUserLoginFieldException.class)
   public void verificatingUserLoginCorruptionWhenUserHasNoPasswordShouldThrowException()
         throws InvalidUserLoginFieldException, UserNotFoundException, CouldNotAccessDataException,
         InvalidPasswordException, UserActivationException {

      // Given
      when(userDto.getPassword()).thenReturn(null);

      // When
      userCorruptionVerificator.login(request, userDto);

      // Then InvalidUserLoginFieldException is thrown
   }

   @Test(expected = InvalidUserLoginFieldException.class)
   public void verificatingUserLoginCorruptionWhenUserHasEmptyPasswordShouldThrowException()
         throws InvalidUserLoginFieldException, UserNotFoundException, CouldNotAccessDataException,
         InvalidPasswordException, UserActivationException {

      // Given
      when(userDto.getPassword()).thenReturn(EMPTY_FIELD);

      // When
      userCorruptionVerificator.login(request, userDto);

      // Then InvalidUserLoginFieldException is thrown
   }

}
