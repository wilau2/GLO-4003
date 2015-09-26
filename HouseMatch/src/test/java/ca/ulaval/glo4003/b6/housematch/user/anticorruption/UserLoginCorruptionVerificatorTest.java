package ca.ulaval.glo4003.b6.housematch.user.anticorruption;

import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.user.anticorruption.exceptions.InvalidUserLoginFieldException;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserLoginDto;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.CouldNotAccessUserDataException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.user.services.InvalidPasswordException;
import ca.ulaval.glo4003.b6.housematch.user.services.UserLoginService;

public class UserLoginCorruptionVerificatorTest {

   private static final String USERNAME = "username";

   private static final String PASSWORD = "password";

   private static final String EMPTY_FIELD = "";

   @Mock
   UserLoginDto userDto;

   @Mock
   UserLoginService userLoginService;

   @InjectMocks
   private UserLoginCorruptionVerificator userCorruptionVerificator;

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

   @Test
   public void verificatingUserLoginCorruptionWhenUserLoginIsValidShouldCallServiceLogin()
         throws InvalidUserLoginFieldException, UserNotFoundException, CouldNotAccessUserDataException,
         InvalidPasswordException {
      // Given

      // When
      // userCorruptionVerificator.login(request, userDto);
      // Then
      // verify(userLoginService).login(request, userDto);

   }

   @Test // (expected = InvalidUserLoginFieldException.class)
   public void verificatingUserLoginCorruptionWhenUserHasNoUsernameShouldThrowException()
         throws InvalidUserLoginFieldException, UserNotFoundException, CouldNotAccessUserDataException,
         InvalidPasswordException {
      // Given
      when(userDto.getUsername()).thenReturn(null);

      // When
      // userCorruptionVerificator.login(request, userDto);

      // Then InvalidUserLoginFieldException is thrown
   }

   @Test // (expected = InvalidUserLoginFieldException.class)
   public void verificatingUserLoginCorruptionWhenUserHasEmptyUsernameShouldThrowException()
         throws InvalidUserLoginFieldException, UserNotFoundException, CouldNotAccessUserDataException,
         InvalidPasswordException {
      // Given
      when(userDto.getUsername()).thenReturn(EMPTY_FIELD);

      // When
      // userCorruptionVerificator.login(request, userDto);

      // Then InvalidUserLoginFieldException is thrown
   }

   @Test // (expected = InvalidUserLoginFieldException.class)
   public void verificatingUserLoginCorruptionWhenUserHasNoPasswordShouldThrowException()
         throws InvalidUserLoginFieldException, UserNotFoundException, CouldNotAccessUserDataException,
         InvalidPasswordException {
      // Given
      when(userDto.getPassword()).thenReturn(null);

      // When
      // userCorruptionVerificator.login(request, userDto);

      // Then InvalidUserLoginFieldException is thrown
   }

   @Test // (expected = InvalidUserLoginFieldException.class)
   public void verificatingUserLoginCorruptionWhenUserHasEmptyPasswordShouldThrowException()
         throws InvalidUserLoginFieldException, UserNotFoundException, CouldNotAccessUserDataException,
         InvalidPasswordException {
      // Given
      when(userDto.getPassword()).thenReturn(EMPTY_FIELD);

      // When
      // userCorruptionVerificator.login(request, userDto);

      // Then InvalidUserLoginFieldException is thrown
   }

}
