package ca.ulaval.glo4003.b6.housematch.services.admin;

import java.util.List;

import javax.inject.Inject;

import org.dom4j.DocumentException;

import ca.ulaval.glo4003.b6.housematch.domain.estate.Estate;
import ca.ulaval.glo4003.b6.housematch.domain.estate.EstateRepository;
import ca.ulaval.glo4003.b6.housematch.domain.estate.EstatesProcessor;
import ca.ulaval.glo4003.b6.housematch.domain.user.User;
import ca.ulaval.glo4003.b6.housematch.domain.user.UserProcessor;
import ca.ulaval.glo4003.b6.housematch.domain.user.UserRepository;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.estate.EstateRepositoryFactory;

public class AdminStatisticService {

   private UserRepository userRepository;

   private UserProcessor userProcessor;

   private EstateRepositoryFactory estateRepositoryFactory;

   private EstatesProcessor estateProcessor;

   @Inject
   public AdminStatisticService(UserRepository userRepository, UserProcessor userProcessor,
         EstateRepositoryFactory estateRepositoryFactory, EstatesProcessor estateProcessor) {
      this.userRepository = userRepository;
      this.userProcessor = userProcessor;
      this.estateRepositoryFactory = estateRepositoryFactory;
      this.estateProcessor = estateProcessor;

   }

   public int getNumberOfActiveBuyer() throws CouldNotAccessDataException, DocumentException {
      List<User> users = userRepository.getAllUser();
      return userProcessor.getNumberOfActiveBuyer(users);
   }

   public int getNumberOfActiveSeller() throws CouldNotAccessDataException {
      EstateRepository estateRepository = estateRepositoryFactory.newInstance(new EstateAssemblerFactory());
      List<Estate> estates = estateRepository.getAllEstates();
      List<String> uniqueSellersName = estateProcessor.retrieveUniqueSellersName(estates);
      return uniqueSellersName.size();
   }

}
