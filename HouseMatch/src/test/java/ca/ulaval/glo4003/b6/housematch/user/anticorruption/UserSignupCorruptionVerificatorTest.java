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
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.CouldNotAccessUserDataException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UsernameAlreadyExistsException;
import ca.ulaval.glo4003.b6.housematch.user.services.UserSignupService;

public class UserSignupCorruptionVerificatorTest {

   private static final String FIRST_NAME = "firstName";

   private static final String LAST_NAME = "lastName";

   private static final String PHONE_NUMBER = "phoneNumber";

   private static final String EMAIL = "email";

   private static final String USERNAME = "username";

   private static final String PASSWORD = "password";

   private static final String EMPTY_FIELD = "";

   private static final String ROLE = "role";

   @Mock
   private UserSignupDto userDto;

   @Mock
   private UserSignupService userSignupService;

   @InjectMocks
   private UserSignupCorruptionVerificator userCorruptionVerificator;

   @Mock
   private HttpServletRequest request;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);
      configureValidUserSignupModel();
   }

   private void configureValidUserSignupModel() {
      when(userDto.getUsername()).thenReturn(USERNAME);
      when(userDto.getPassword()).thenReturn(PASSWORD);
      when(userDto.getFirstName()).thenReturn(FIRST_NAME);
      when(userDto.getLastName()).thenReturn(LAST_NAME);
      when(userDto.getEmail()).thenReturn(EMAIL);
      when(userDto.getPhoneNumber()).thenReturn(PHONE_NUMBER);
      when(userDto.getRole()).thenReturn(ROLE);
   }

   @Test
   public void verificatingUserSignupCorruptionWhenUserSignupIsValidShouldCallServiceSignup()
         throws InvalidUserSignupFieldException, UsernameAlreadyExistsException, CouldNotAccessUserDataException {
      // Given

      // When
      userCorruptionVerificator.signup(userDto);

      // Then
      verify(userSignupService).signup(userDto);

   }

   @Test(expected = InvalidUserSignupFieldException.class)
   public void verificatingUserSignupCorruptionWhenUserHasNoUsernameShouldThrowException()
         throws InvalidUserSignupFieldException, UsernameAlreadyExistsException, CouldNotAccessUserDataException {
      // Given
      when(userDto.getUsername()).thenReturn(null);

      // When
      userCorruptionVerificator.signup(userDto);

      // Then an InvalidUserSignupFieldException is thrown
   }

   @Test(expected = InvalidUserSignupFieldException.class)
   public void verificatingUserSignupCorruptionWhenUserHasEmptyUsernameShouldThrowException()
         throws InvalidUserSignupFieldException, UsernameAlreadyExistsException, CouldNotAccessUserDataException {
      // Given
      when(userDto.getUsername()).thenReturn(EMPTY_FIELD);

      // When
      userCorruptionVerificator.signup(userDto);

      // Then an InvalidUserSignupFieldException is thrown
   }

   @Test(expected = InvalidUserSignupFieldException.class)
   public void verificatingUserSignupCorruptionWhenUserHasNoPasswordShouldThrowException()
         throws InvalidUserSignupFieldException, UsernameAlreadyExistsException, CouldNotAccessUserDataException {
      // Given
      when(userDto.getPassword()).thenReturn(null);

      // When
      userCorruptionVerificator.signup(userDto);

      // Then an InvalidUserSignupFieldException is thrown
   }

   @Test(expected = InvalidUserSignupFieldException.class)
   public void verificatingUserSignupCorruptionWhenUserHasEmptyPasswordShouldThrowException()
         throws InvalidUserSignupFieldException, UsernameAlreadyExistsException, CouldNotAccessUserDataException {
      // Given
      when(userDto.getPassword()).thenReturn(EMPTY_FIELD);

      // When
      userCorruptionVerificator.signup(userDto);

      // Then an InvalidUserSignupFieldException is thrown
   }

   @Test(expected = InvalidUserSignupFieldException.class)
   public void verificatingUserSignupCorruptionWhenUserHasNoFirstNameShouldThrowException()
         throws InvalidUserSignupFieldException, UsernameAlreadyExistsException, CouldNotAccessUserDataException {
      // Given
      when(userDto.getFirstName()).thenReturn(null);

      // When
      userCorruptionVerificator.signup(userDto);

      // Then an InvalidUserSignupFieldException is thrown
   }

   @Test(expected = InvalidUserSignupFieldException.class)
   public void verificatingUserSignupCorruptionWhenUserHasEmptyFirstNameShouldThrowException()
         throws InvalidUserSignupFieldException, UsernameAlreadyExistsException, CouldNotAccessUserDataException {
      // Given
      when(userDto.getFirstName()).thenReturn(EMPTY_FIELD);

      // When
      userCorruptionVerificator.signup(userDto);

      // Then an InvalidUserSignupFieldException is thrown
   }

   @Test(expected = InvalidUserSignupFieldException.class)
   public void verificatingUserSignupCorruptionWhenUserHasNoLastNameShouldThrowException()
         throws InvalidUserSignupFieldException, UsernameAlreadyExistsException, CouldNotAccessUserDataException {
      // Given
      when(userDto.getLastName()).thenReturn(null);

      // When
      userCorruptionVerificator.signup(userDto);

      // Then an InvalidUserSignupFieldException is thrown
   }

   @Test(expected = InvalidUserSignupFieldException.class)
   public void verificatingUserSignupCorruptionWhenUserHasEmptyLastNameShouldThrowException()
         throws InvalidUserSignupFieldException, UsernameAlreadyExistsException, CouldNotAccessUserDataException {
      // Given
      when(userDto.getLastName()).thenReturn(EMPTY_FIELD);

      // When
      userCorruptionVerificator.signup(userDto);

      // Then an InvalidUserSignupFieldException is thrown
   }

   @Test(expected = InvalidUserSignupFieldException.class)
   public void verificatingUserSignupCorruptionWhenUserHasNoPhoneNumberShouldThrowException()
         throws InvalidUserSignupFieldException, UsernameAlreadyExistsException, CouldNotAccessUserDataException {
      // Given
      when(userDto.getPhoneNumber()).thenReturn(null);

      // When
      userCorruptionVerificator.signup(userDto);

      // Then an InvalidUserSignupFieldException is thrown
   }

   @Test(expected = InvalidUserSignupFieldException.class)
   public void verificatingUserSignupCorruptionWhenUserHasEmptyPhoneNumberShouldThrowException()
         throws InvalidUserSignupFieldException, UsernameAlreadyExistsException, CouldNotAccessUserDataException {
      // Given
      when(userDto.getPhoneNumber()).thenReturn(EMPTY_FIELD);

      // When
      userCorruptionVerificator.signup(userDto);

      // Then an InvalidUserSignupFieldException is thrown
   }

   @Test(expected = InvalidUserSignupFieldException.class)
   public void verificatingUserSignupCorruptionWhenUserHasNoEmailShouldThrowException()
         throws InvalidUserSignupFieldException, UsernameAlreadyExistsException, CouldNotAccessUserDataException {
      // Given
      when(userDto.getEmail()).thenReturn(null);

      // When
      userCorruptionVerificator.signup(userDto);

      // Then an InvalidUserSignupFieldException is thrown
   }

   @Test(expected = InvalidUserSignupFieldException.class)
   public void verificatingUserSignupCorruptionWhenUserHasEmptyEmailShouldThrowException()
         throws InvalidUserSignupFieldException, UsernameAlreadyExistsException, CouldNotAccessUserDataException {
      // Given
      when(userDto.getEmail()).thenReturn(EMPTY_FIELD);

      // When
      userCorruptionVerificator.signup(userDto);

      // Then an InvalidUserSignupFieldException is thrown
   }

   @Test(expected = InvalidUserSignupFieldException.class)
   public void verificatingUserSignupCorruptionWhenUserHasNoRoleShouldThrowException()
         throws InvalidUserSignupFieldException, UsernameAlreadyExistsException, CouldNotAccessUserDataException {
      // Given
      when(userDto.getRole()).thenReturn(null);

      // When
      userCorruptionVerificator.signup(userDto);

      // Then an InvalidUserSignupFieldException is thrown
   }

   @Test(expected = InvalidUserSignupFieldException.class)
   public void verificatingUserSignupCorruptionWhenUserHasEmptyRoleShouldThrowException()
         throws InvalidUserSignupFieldException, UsernameAlreadyExistsException, CouldNotAccessUserDataException {
      // Given
      when(userDto.getRole()).thenReturn(EMPTY_FIELD);

      // When
      userCorruptionVerificator.signup(userDto);

      // Then an InvalidUserSignupFieldException is thrown
   }
}
