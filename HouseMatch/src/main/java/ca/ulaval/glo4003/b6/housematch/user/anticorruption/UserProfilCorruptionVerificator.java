package ca.ulaval.glo4003.b6.housematch.user.anticorruption;

import org.springframework.beans.factory.annotation.Autowired;

import ca.ulaval.glo4003.b6.housematch.user.dto.UserDetailedDto;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.CouldNotAccessUserDataException;
import ca.ulaval.glo4003.b6.housematch.user.services.UserProfilService;

public class UserProfilCorruptionVerificator {

   private UserProfilService userProfilService;

   @Autowired
   public UserProfilCorruptionVerificator(UserProfilService userProfilService) {
      this.userProfilService = userProfilService;
   }

   public void update(UserDetailedDto userDto) throws CouldNotAccessUserDataException {
      validateUserProfilCorruption(userDto);
      userProfilService.update(userDto);
   }

   private void validateUserProfilCorruption(UserDetailedDto userDto) {
      // TODO Auto-generated method stub

   }

}
