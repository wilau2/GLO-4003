package ca.ulaval.glo4003.b6.housematch.persistance;

import ca.ulaval.glo4003.b6.housematch.domain.user.User;
import ca.ulaval.glo4003.b6.housematch.persistance.user.RepositoryToPersistenceUserDto;

public class RepositoryToPersistenceDtoFactory {

   public RepositoryToPersistenceDto getRepositoryDto(User user) {

      return new RepositoryToPersistenceUserDto(user);
   }

}
