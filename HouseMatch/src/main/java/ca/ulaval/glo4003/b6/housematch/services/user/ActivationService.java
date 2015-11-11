package ca.ulaval.glo4003.b6.housematch.services.user;

import javax.inject.Inject;

import ca.ulaval.glo4003.b6.housematch.domain.user.User;
import ca.ulaval.glo4003.b6.housematch.domain.user.UserRepository;
import ca.ulaval.glo4003.b6.housematch.domain.user.exceptions.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;

public class ActivationService {

   private UserRepository userRepository;

   @Inject
   public ActivationService(UserRepository userRepository) {
      this.userRepository = userRepository;
   }

   public void activateAccount(String username) throws UserNotFoundException, CouldNotAccessDataException {
      User user = userRepository.getUser(username);
      user.setActive(true);
      userRepository.updateUser(user);
   }

}
