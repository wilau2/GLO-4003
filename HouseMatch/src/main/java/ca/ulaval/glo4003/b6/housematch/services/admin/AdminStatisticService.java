package ca.ulaval.glo4003.b6.housematch.services.admin;

import javax.inject.Inject;

import ca.ulaval.glo4003.b6.housematch.domain.estate.EstateRepository;
import ca.ulaval.glo4003.b6.housematch.domain.user.UserRepository;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.estate.EstateRepositoryFactory;

public class AdminStatisticService {

   private UserRepository userRepository;

   private EstateRepositoryFactory estateRepositoryFactory;

   @Inject
   public AdminStatisticService(UserRepository userRepository, EstateRepositoryFactory estateRepositoryFactory) {
      this.userRepository = userRepository;
      this.estateRepositoryFactory = estateRepositoryFactory;
   }

   public int getNumberOfActiveBuyer() throws CouldNotAccessDataException {
      return userRepository.getNumberOfActiveBuyer();
   }

   public int getNumberOfActiveSeller() throws CouldNotAccessDataException {
      EstateRepository estateRepository = estateRepositoryFactory.newInstance(new EstateAssemblerFactory());
      return estateRepository.getNumberOfUniqueSeller();
   }

}
