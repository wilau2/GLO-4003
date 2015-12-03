package ca.ulaval.glo4003.b6.housematch.services.user;

import javax.inject.Inject;

import ca.ulaval.glo4003.b6.housematch.domain.user.User;
import ca.ulaval.glo4003.b6.housematch.domain.user.UserRepository;
import ca.ulaval.glo4003.b6.housematch.domain.user.exceptions.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.dto.UserDto;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.UserAssembler;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.factory.UserAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;

public class UserFetcher {

   private UserRepository userRepository;

   private UserAssemblerFactory userAssemblerFactory;

   @Inject
   public UserFetcher(UserRepository userRepository, UserAssemblerFactory userAssemblerFactory) {
      this.userRepository = userRepository;
      this.userAssemblerFactory = userAssemblerFactory;
   }

   public UserDto getUserByUsername(String username) throws UserNotFoundException, CouldNotAccessDataException {

      User user = userRepository.getUser(username);
      UserAssembler userAssembler = userAssemblerFactory.createUserAssembler();
      UserDto userDto = userAssembler.assembleUserDto(user);

      return userDto;

   }

}
