package ca.ulaval.glo4003.b6.housematch.user.anticorruption;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.user.anticorruption.exceptions.InvalidContactInformationFieldException;
import ca.ulaval.glo4003.b6.housematch.user.anticorruption.exceptions.InvalidUserSignupFieldException;
import ca.ulaval.glo4003.b6.housematch.user.dto.ContactInformationDto;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserDetailedDto;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.CouldNotAccessUserDataException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.user.services.UserProfilService;

public class UserProfilCorruptionVerificatorTest {

   @Mock
   private UserProfilService userProfilService;

   @Mock
   private ContactInformationCorruptionVerificator contactInformationCorruptionVerificator;

   @InjectMocks
   private UserProfilCorruptionVerificator userProfilCorruptionVerificator;

   @Mock
   private UserDetailedDto userDetailedDto;

   @Mock
   private ContactInformationDto contactInformationDto;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);
      when(userDetailedDto.getContactInformationDto()).thenReturn(contactInformationDto);
   }

   @Test
   public void givenValidUserDetailedDtoWhenUpdateContactInformationShouldDelagateUpdating()
         throws CouldNotAccessUserDataException, UserNotFoundException, InvalidUserSignupFieldException,
         InvalidContactInformationFieldException {
      // Given

      // When
      userProfilCorruptionVerificator.update(userDetailedDto);

      // Then
      verify(userProfilService).update(userDetailedDto);
   }

   @Test
   public void givenValidUserDetailedDtoWhenUpdateContactInformationShouldDelageCorruptionValidation()
         throws CouldNotAccessUserDataException, UserNotFoundException, InvalidUserSignupFieldException,
         InvalidContactInformationFieldException {
      // Given

      // When
      userProfilCorruptionVerificator.update(userDetailedDto);

      // Then
      verify(contactInformationCorruptionVerificator).validateContactInformationCorruption(contactInformationDto);
   }
}
