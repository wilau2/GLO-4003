package ca.ulaval.glo4003.b6.housematch.services.admin;

import javax.inject.Inject;

import ca.ulaval.glo4003.b6.housematch.domain.user.UserRepository;

public class AdminStatisticService {

   private UserRepositoryFactory userRepositoryFactory;

   @Inject
   public AdminStatisticService(UserRepositoryFactory userRepositoryFactory) {
      this.userRepositoryFactory = userRepositoryFactory;
   }

   public int getNumberOfActiveBuyer() {
      UserRepository userRepository = userRepositoryFactory.newInstance();

      return userRepository.getNumberOfActiveBuyer();
   }

}
