package ca.ulaval.glo4003.b6.housematch.anticorruption.user;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.anticorruption.user.exceptions.InvalidContactInformationFieldException;
import ca.ulaval.glo4003.b6.housematch.anticorruption.user.exceptions.InvalidUserSignupFieldException;
import ca.ulaval.glo4003.b6.housematch.domain.user.exceptions.UsernameAlreadyExistsException;
import ca.ulaval.glo4003.b6.housematch.dto.ContactInformationDto;
import ca.ulaval.glo4003.b6.housematch.dto.UserDto;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.user.UserSignupService;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.UserNotifyingException;

public class UserSignupCorruptionVerificatorTest {

   private static final String USERNAME = "username";

   private static final String PASSWORD = "password";

   private static final String EMPTY_FIELD = "";

   private static final String ROLE = "role";

   @Mock
   private UserDto userDto;

   @Mock
   private UserSignupService userSignupService;

   @InjectMocks
   private UserSignupCorruptionVerificator userCorruptionVerificator;

   @Mock
   private HttpServletRequest request;

   @Mock
   private ContactInformationDto contactInformationDto;

   @Mock
   private ContactInformationCorruptionVerificator contactInformationCorruptionVerificator;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);
      configureValidUserSignupModel();
   }

   private void configureValidUserSignupModel() {
      when(userDto.getUsername()).thenReturn(USERNAME);
      when(userDto.getPassword()).thenReturn(PASSWORD);
      when(userDto.getContactInformationDto()).thenReturn(contactInformationDto);
      when(userDto.getRole()).thenReturn(ROLE);
   }

   @Test
   public void verificatingUserSignupCorruptionWhenUserSignupIsValidShouldCallServiceSignup()
         throws InvalidUserSignupFieldException, UsernameAlreadyExistsException, CouldNotAccessDataException,
         InvalidContactInformationFieldException, UserNotifyingException {
      // Given
      // When
      userCorruptionVerificator.signup(userDto);

      // Then
      verify(userSignupService).signup(userDto);

   }

   @Test(expected = InvalidUserSignupFieldException.class)
   public void verificatingUserSignupCorruptionWhenUserHasNoUsernameShouldThrowException()
         throws InvalidUserSignupFieldException, UsernameAlreadyExistsException, CouldNotAccessDataException,
         InvalidContactInformationFieldException, UserNotifyingException {
      // Given
      when(userDto.getUsername()).thenReturn(null);

      // When
      userCorruptionVerificator.signup(userDto);

      // Then an InvalidUserSignupFieldException is thrown
   }

   @Test(expected = InvalidUserSignupFieldException.class)
   public void verificatingUserSignupCorruptionWhenUserHasEmptyUsernameShouldThrowException()
         throws InvalidUserSignupFieldException, UsernameAlreadyExistsException, CouldNotAccessDataException,
         InvalidContactInformationFieldException, UserNotifyingException {
      // Given
      when(userDto.getUsername()).thenReturn(EMPTY_FIELD);

      // When
      userCorruptionVerificator.signup(userDto);

      // Then an InvalidUserSignupFieldException is thrown
   }

   @Test(expected = InvalidUserSignupFieldException.class)
   public void verificatingUserSignupCorruptionWhenUserHasNoPasswordShouldThrowException()
         throws InvalidUserSignupFieldException, UsernameAlreadyExistsException, CouldNotAccessDataException,
         InvalidContactInformationFieldException, UserNotifyingException {
      // Given
      when(userDto.getPassword()).thenReturn(null);

      // When
      userCorruptionVerificator.signup(userDto);

      // Then an InvalidUserSignupFieldException is thrown
   }

   @Test(expected = InvalidUserSignupFieldException.class)
   public void verificatingUserSignupCorruptionWhenUserHasEmptyPasswordShouldThrowException()
         throws InvalidUserSignupFieldException, UsernameAlreadyExistsException, CouldNotAccessDataException,
         InvalidContactInformationFieldException, UserNotifyingException {
      // Given
      when(userDto.getPassword()).thenReturn(EMPTY_FIELD);

      // When
      userCorruptionVerificator.signup(userDto);

      // Then an InvalidUserSignupFieldException is thrown
   }

   @Test(expected = InvalidUserSignupFieldException.class)
   public void verificatingUserSignupCorruptionWhenUserHasNoRoleShouldThrowException()
         throws InvalidUserSignupFieldException, UsernameAlreadyExistsException, CouldNotAccessDataException,
         InvalidContactInformationFieldException, UserNotifyingException {
      // Given
      when(userDto.getRole()).thenReturn(null);

      // When
      userCorruptionVerificator.signup(userDto);

      // Then an InvalidUserSignupFieldException is thrown
   }

   @Test(expected = InvalidUserSignupFieldException.class)
   public void verificatingUserSignupCorruptionWhenUserHasEmptyRoleShouldThrowException()
         throws InvalidUserSignupFieldException, UsernameAlreadyExistsException, CouldNotAccessDataException,
         InvalidContactInformationFieldException, UserNotifyingException {
      // Given
      when(userDto.getRole()).thenReturn(EMPTY_FIELD);

      // When
      userCorruptionVerificator.signup(userDto);

      // Then an InvalidUserSignupFieldException is thrown
   }
}
