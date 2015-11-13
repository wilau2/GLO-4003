package ca.ulaval.glo4003.b6.housematch.anticorruption.user;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.anticorruption.user.exceptions.InvalidContactInformationFieldException;
import ca.ulaval.glo4003.b6.housematch.anticorruption.user.exceptions.InvalidUserSignupFieldException;
import ca.ulaval.glo4003.b6.housematch.domain.user.exceptions.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.dto.ContactInformationDto;
import ca.ulaval.glo4003.b6.housematch.dto.UserDto;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.user.UserProfilService;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.UserNotifyingException;

public class UserProfilCorruptionVerificatorTest {

   @Mock
   private UserProfilService userProfilService;

   @Mock
   private ContactInformationCorruptionVerificator contactInformationCorruptionVerificator;

   @InjectMocks
   private UserProfilCorruptionVerificator userProfilCorruptionVerificator;

   @Mock
   private UserDto userDetailedDto;

   @Mock
   private ContactInformationDto contactInformationDto;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);
      when(userDetailedDto.getContactInformationDto()).thenReturn(contactInformationDto);
   }

   @Test
   public void givenValidUserDetailedDtoWhenUpdateContactInformationShouldDelagateUpdating()
         throws CouldNotAccessDataException, UserNotFoundException, InvalidUserSignupFieldException,
         InvalidContactInformationFieldException, UserNotifyingException {
      // Given

      // When
      userProfilCorruptionVerificator.update(userDetailedDto);

      // Then
      verify(userProfilService).update(userDetailedDto);
   }

   @Test
   public void givenValidUserDetailedDtoWhenUpdateContactInformationShouldDelageCorruptionValidation()
         throws CouldNotAccessDataException, UserNotFoundException, InvalidUserSignupFieldException,
         InvalidContactInformationFieldException, UserNotifyingException {
      // Given

      // When
      userProfilCorruptionVerificator.update(userDetailedDto);

      // Then
      verify(contactInformationCorruptionVerificator).validateContactInformationCorruption(contactInformationDto);
   }
}
