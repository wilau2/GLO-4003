package ca.ulaval.glo4003.b6.housematch.user.services;

import org.springframework.beans.factory.annotation.Autowired;

import ca.ulaval.glo4003.b6.housematch.user.domain.User;
import ca.ulaval.glo4003.b6.housematch.user.repository.UserRepository;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.CouldNotAccessUserDataException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserNotFoundException;

public class ActivationService {
   
   private UserRepository userRepository;
   
   @Autowired
   public ActivationService(UserRepository userRepository)
   {
      this.userRepository = userRepository;
   }
   
   public void activateAccount(String username) throws UserNotFoundException, CouldNotAccessUserDataException
   {
      User newUser = userRepository.getUser(username);
      newUser.setActive(true);
      userRepository.updateUser(newUser);
   }

}
