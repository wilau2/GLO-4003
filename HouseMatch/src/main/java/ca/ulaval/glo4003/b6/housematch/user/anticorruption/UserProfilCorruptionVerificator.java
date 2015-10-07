package ca.ulaval.glo4003.b6.housematch.user.anticorruption;

import org.springframework.beans.factory.annotation.Autowired;

import ca.ulaval.glo4003.b6.housematch.user.anticorruption.exceptions.InvalidContactInformationFieldException;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserDetailedDto;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.CouldNotAccessUserDataException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.user.services.UserProfilService;

public class UserProfilCorruptionVerificator {

   private UserProfilService userProfilService;

   private ContactInformationCorruptionVerificator contactInformationCorruptionVerificator;

   @Autowired
   public UserProfilCorruptionVerificator(UserProfilService userProfilService,
         ContactInformationCorruptionVerificator contactInformationCorruptionVerificator) {
      this.userProfilService = userProfilService;
      this.contactInformationCorruptionVerificator = contactInformationCorruptionVerificator;
   }

   public void update(UserDetailedDto userDetailedDto)
         throws CouldNotAccessUserDataException, UserNotFoundException, InvalidContactInformationFieldException {
      contactInformationCorruptionVerificator
            .validateContactInformationCorruption(userDetailedDto.getContactInformationDto());

      userProfilService.update(userDetailedDto);

   }

}
