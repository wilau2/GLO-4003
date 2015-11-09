package ca.ulaval.glo4003.b6.housematch.services.admin;

import javax.inject.Inject;

import ca.ulaval.glo4003.b6.housematch.domain.estate.EstateRepository;
import ca.ulaval.glo4003.b6.housematch.domain.user.UserRepository;
import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;

public class AdminStatisticService {

   private UserRepository userRepository;

   private EstateRepository estateRepository;

   @Inject
   public AdminStatisticService(UserRepository userRepository, EstateRepository estateRepository) {
      this.userRepository = userRepository;
      this.estateRepository = estateRepository;
   }

   public int getNumberOfActiveBuyer() throws CouldNotAccessDataException {
      return userRepository.getNumberOfActiveBuyer();
   }

   public int getNumberOfActiveSeller() {
      return estateRepository.getNumberOfUniqueSeller();
   }

}
