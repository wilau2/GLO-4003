package ca.ulaval.glo4003.b6.housematch.persistance;

import ca.ulaval.glo4003.b6.housematch.domain.user.User;
import ca.ulaval.glo4003.b6.housematch.persistance.user.UserPersistenceDto;

public class PersistenceDtoFactory {

   public PersistenceDto getRepositoryDto(User user) {

      return new UserPersistenceDto(user);
   }

}
