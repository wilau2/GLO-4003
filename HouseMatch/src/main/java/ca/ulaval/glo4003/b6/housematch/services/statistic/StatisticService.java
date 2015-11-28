package ca.ulaval.glo4003.b6.housematch.services.statistic;

import java.util.List;

import javax.inject.Inject;

import org.dom4j.DocumentException;

import ca.ulaval.glo4003.b6.housematch.domain.estate.Estate;
import ca.ulaval.glo4003.b6.housematch.domain.estate.EstateRepository;
import ca.ulaval.glo4003.b6.housematch.domain.estate.EstatesProcessor;
import ca.ulaval.glo4003.b6.housematch.domain.user.User;
import ca.ulaval.glo4003.b6.housematch.domain.user.UserProcessor;
import ca.ulaval.glo4003.b6.housematch.domain.user.UserRepository;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;

public class StatisticService {

   private UserRepository userRepository;

   private UserProcessor userProcessor;

   private EstateRepository estateRepository;

   private EstatesProcessor estateProcessor;

   @Inject
   public StatisticService(UserRepository userRepository, UserProcessor userProcessor,
         EstateRepository estateRepository, EstatesProcessor estateProcessor) {
      this.userRepository = userRepository;
      this.userProcessor = userProcessor;
      this.estateRepository = estateRepository;
      this.estateProcessor = estateProcessor;

   }

   public int getNumberOfActiveBuyers() throws CouldNotAccessDataException, DocumentException {
      List<User> users = userRepository.getAllUsers();
      return userProcessor.getNumberOfActiveBuyer(users);
   }

   public int getNumberOfActiveSeller() throws CouldNotAccessDataException {

      List<Estate> estates = estateRepository.getAllEstates();
      List<String> uniqueSellersName = estateProcessor.retrieveUniqueSellersName(estates);
      return uniqueSellersName.size();
   }

}
