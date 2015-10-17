package ca.ulaval.glo4003.b6.housematch.anticorruption.user;

import org.springframework.beans.factory.annotation.Autowired;

import ca.ulaval.glo4003.b6.housematch.anticorruption.user.exceptions.InvalidContactInformationFieldException;
import ca.ulaval.glo4003.b6.housematch.domain.user.exceptions.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.dto.UserDto;
import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.user.UserProfilService;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.UserNotifyingException;

public class UserProfilCorruptionVerificator {

   private UserProfilService userProfilService;

   private ContactInformationCorruptionVerificator contactInformationCorruptionVerificator;

   @Autowired
   public UserProfilCorruptionVerificator(UserProfilService userProfilService,
         ContactInformationCorruptionVerificator contactInformationCorruptionVerificator) {
      this.userProfilService = userProfilService;
      this.contactInformationCorruptionVerificator = contactInformationCorruptionVerificator;
   }

   public void update(UserDto userDetailedDto) throws CouldNotAccessDataException, UserNotFoundException,
         InvalidContactInformationFieldException, UserNotifyingException {

      contactInformationCorruptionVerificator
            .validateContactInformationCorruption(userDetailedDto.getContactInformationDto());

      userProfilService.update(userDetailedDto);

   }

}
