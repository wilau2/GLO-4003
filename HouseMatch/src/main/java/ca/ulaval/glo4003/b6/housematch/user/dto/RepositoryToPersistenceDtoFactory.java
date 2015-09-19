package ca.ulaval.glo4003.b6.housematch.user.dto;

import ca.ulaval.glo4003.b6.housematch.user.model.User;

public class RepositoryToPersistenceDtoFactory {

   public RepositoryToPersistenceDto getRepositoryDto(User user) {

      return new RepositoryToPersistenceUserDto(user);
   }

}
