package ca.ulaval.glo4003.b6.housematch.anticorruption.user;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.anticorruption.user.exceptions.InvalidContactInformationFieldException;
import ca.ulaval.glo4003.b6.housematch.domain.user.exceptions.UsernameAlreadyExistsException;
import ca.ulaval.glo4003.b6.housematch.dto.ContactInformationDto;
import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;

public class ContactInformationCorruptionVerificatorTest {

   private static final String EMPTY_FIELD = "";

   private static final String FIRST_NAME = "firstname";

   private static final String LAST_NAME = "lastname";

   private static final String PHONE_NUMBER = "phonenumber";

   private static final String EMAIL = "email";

   @InjectMocks
   ContactInformationCorruptionVerificator contactInformationCorruptionVerificator;

   @Mock
   private ContactInformationDto contactInformationDto;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);
      configureValidContactInformationDto();
   }

   private void configureValidContactInformationDto() {
      when(contactInformationDto.getFirstName()).thenReturn(FIRST_NAME);
      when(contactInformationDto.getLastName()).thenReturn(LAST_NAME);
      when(contactInformationDto.getPhoneNumber()).thenReturn(PHONE_NUMBER);
      when(contactInformationDto.getEmail()).thenReturn(EMAIL);

   }

   @Test(expected = InvalidContactInformationFieldException.class)
   public void verificatingUserSignupCorruptionWhenUserHasNoFirstNameShouldThrowException()
         throws UsernameAlreadyExistsException, CouldNotAccessDataException, InvalidContactInformationFieldException {
      // Given
      when(contactInformationDto.getFirstName()).thenReturn(null);

      // When
      contactInformationCorruptionVerificator.validateContactInformationCorruption(contactInformationDto);

      // Then an InvalidUserSignupFieldException is thrown
   }

   @Test(expected = InvalidContactInformationFieldException.class)
   public void verificatingUserSignupCorruptionWhenUserHasEmptyFirstNameShouldThrowException()
         throws UsernameAlreadyExistsException, CouldNotAccessDataException, InvalidContactInformationFieldException {
      // Given
      when(contactInformationDto.getFirstName()).thenReturn(EMPTY_FIELD);

      // When
      contactInformationCorruptionVerificator.validateContactInformationCorruption(contactInformationDto);

      // Then an InvalidUserSignupFieldException is thrown
   }

   @Test(expected = InvalidContactInformationFieldException.class)
   public void verificatingUserSignupCorruptionWhenUserHasNoLastNameShouldThrowException()
         throws UsernameAlreadyExistsException, CouldNotAccessDataException, InvalidContactInformationFieldException {
      // Given
      when(contactInformationDto.getLastName()).thenReturn(null);

      // When
      contactInformationCorruptionVerificator.validateContactInformationCorruption(contactInformationDto);

      // Then an InvalidUserSignupFieldException is thrown
   }

   @Test(expected = InvalidContactInformationFieldException.class)
   public void verificatingUserSignupCorruptionWhenUserHasEmptyLastNameShouldThrowException()
         throws UsernameAlreadyExistsException, CouldNotAccessDataException, InvalidContactInformationFieldException {
      // Given
      when(contactInformationDto.getLastName()).thenReturn(EMPTY_FIELD);

      // When
      contactInformationCorruptionVerificator.validateContactInformationCorruption(contactInformationDto);

      // Then an InvalidUserSignupFieldException is thrown
   }

   @Test(expected = InvalidContactInformationFieldException.class)
   public void verificatingUserSignupCorruptionWhenUserHasNoPhoneNumberShouldThrowException()
         throws UsernameAlreadyExistsException, CouldNotAccessDataException, InvalidContactInformationFieldException {
      // Given
      when(contactInformationDto.getPhoneNumber()).thenReturn(null);

      // When
      contactInformationCorruptionVerificator.validateContactInformationCorruption(contactInformationDto);

      // Then an InvalidUserSignupFieldException is thrown
   }

   @Test(expected = InvalidContactInformationFieldException.class)
   public void verificatingUserSignupCorruptionWhenUserHasEmptyPhoneNumberShouldThrowException()
         throws UsernameAlreadyExistsException, CouldNotAccessDataException, InvalidContactInformationFieldException {
      // Given
      when(contactInformationDto.getPhoneNumber()).thenReturn(EMPTY_FIELD);

      // When
      contactInformationCorruptionVerificator.validateContactInformationCorruption(contactInformationDto);

      // Then an InvalidUserSignupFieldException is thrown
   }

   @Test(expected = InvalidContactInformationFieldException.class)
   public void verificatingUserSignupCorruptionWhenUserHasNoEmailShouldThrowException()
         throws UsernameAlreadyExistsException, CouldNotAccessDataException, InvalidContactInformationFieldException {
      // Given
      when(contactInformationDto.getEmail()).thenReturn(null);

      // When
      contactInformationCorruptionVerificator.validateContactInformationCorruption(contactInformationDto);

      // Then an InvalidUserSignupFieldException is thrown
   }

   @Test(expected = InvalidContactInformationFieldException.class)
   public void verificatingUserSignupCorruptionWhenUserHasEmptyEmailShouldThrowException()
         throws UsernameAlreadyExistsException, CouldNotAccessDataException, InvalidContactInformationFieldException {
      // Given
      when(contactInformationDto.getEmail()).thenReturn(EMPTY_FIELD);

      // When
      contactInformationCorruptionVerificator.validateContactInformationCorruption(contactInformationDto);

      // Then an InvalidUserSignupFieldException is thrown
   }
}
