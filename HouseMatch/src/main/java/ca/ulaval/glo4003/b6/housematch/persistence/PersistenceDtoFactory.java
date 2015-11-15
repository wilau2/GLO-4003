package ca.ulaval.glo4003.b6.housematch.persistence;

import ca.ulaval.glo4003.b6.housematch.domain.user.User;
import ca.ulaval.glo4003.b6.housematch.persistence.user.UserPersistenceDto;

public class PersistenceDtoFactory {

   public PersistenceDto getRepositoryDto(User user) {

      return new UserPersistenceDto(user);
   }

}