package ca.ulaval.glo4003.b6.housematch.persistance;

import ca.ulaval.glo4003.b6.housematch.user.domain.User;
import ca.ulaval.glo4003.b6.housematch.user.repository.RepositoryToPersistenceUserDto;

public class RepositoryToPersistenceDtoFactory {

   public RepositoryToPersistenceDto getRepositoryDto(User user) {

      return new RepositoryToPersistenceUserDto(user);
   }

}
