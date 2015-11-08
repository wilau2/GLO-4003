package ca.ulaval.glo4003.b6.housematch.services.admin;

import javax.inject.Inject;

import ca.ulaval.glo4003.b6.housematch.domain.user.UserRepository;
import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;

public class AdminStatisticService {

   private UserRepository userRepository;

   @Inject
   public AdminStatisticService(UserRepository userRepository) {
      this.userRepository = userRepository;
   }

   public int getNumberOfActiveBuyer() throws CouldNotAccessDataException {
      return userRepository.getNumberOfActiveBuyer();
   }

}
