package ca.ulaval.glo4003.b6.housematch.user.anticorruption;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.user.anticorruption.exceptions.InvalidUserSignupFieldException;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserSignupDto;
import ca.ulaval.glo4003.b6.housematch.user.services.UserSignupService;

public class UserSignupCorruptionVerificatorTest {

   private static final String FIRST_NAME = "firstName";

   private static final String LAST_NAME = "lastName";

   private static final String PHONE_NUMBER = "phoneNumber";

   private static final String EMAIL = "email";

   private static final String USERNAME = "username";

   private static final String PASSWORD = "password";

   private static final String EMPTY_FIELD = "";

   @Mock
   UserSignupDto userDto;

   @Mock
   UserSignupService userSignupService;

   @InjectMocks
   private UserSignupCorruptionVerificator userCorruptionVerificator;

   @Mock
   HttpServletRequest request;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);
      configureValidUserSignupModel();
   }

   private void configureValidUserSignupModel() {
      when(userDto.getUsername()).thenReturn(USERNAME);
      when(userDto.getPassword()).thenReturn(PASSWORD);
   }

   @Test
   public void verificatingUserSignupCorruptionWhenUserSignupIsValidShouldCallServiceSignup()
         throws InvalidUserSignupFieldException {
      // Given

      // When
      userCorruptionVerificator.signup(request, userDto);
      // Then
      verify(userSignupService).signup(request, userDto);

   }

   @Test(expected = InvalidUserSignupFieldException.class)
   public void verificatingUserSignupCorruptionWhenUserHasNoUsernameShouldThrowException()
         throws InvalidUserSignupFieldException {
      // Given
      when(userDto.getUsername()).thenReturn(null);

      // When
      userCorruptionVerificator.signup(request, userDto);

      // Then InvalidUserSignupFieldException is thrown
   }

   @Test(expected = InvalidUserSignupFieldException.class)
   public void verificatingUserSignupCorruptionWhenUserHasEmptyUsernameShouldThrowException()
         throws InvalidUserSignupFieldException {
      // Given
      when(userDto.getUsername()).thenReturn(EMPTY_FIELD);

      // When
      userCorruptionVerificator.signup(request, userDto);

      // Then InvalidUserSignupFieldException is thrown
   }

   @Test(expected = InvalidUserSignupFieldException.class)
   public void verificatingUserSignupCorruptionWhenUserHasNoPasswordShouldThrowException()
         throws InvalidUserSignupFieldException {
      // Given
      when(userDto.getPassword()).thenReturn(null);

      // When
      userCorruptionVerificator.signup(request, userDto);

      // Then InvalidUserSignupFieldException is thrown
   }

   @Test(expected = InvalidUserSignupFieldException.class)
   public void verificatingUserSignupCorruptionWhenUserHasEmptyPasswordShouldThrowException()
         throws InvalidUserSignupFieldException {
      // Given
      when(userDto.getPassword()).thenReturn(EMPTY_FIELD);

      // When
      userCorruptionVerificator.signup(request, userDto);

      // Then InvalidUserSignupFieldException is thrown
   }
}
