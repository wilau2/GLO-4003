package ca.ulaval.glo4003.b6.housematch.services.admin;

import java.util.List;

import javax.inject.Inject;

import org.dom4j.DocumentException;

import ca.ulaval.glo4003.b6.housematch.domain.user.User;
import ca.ulaval.glo4003.b6.housematch.domain.user.UserProcessor;
import ca.ulaval.glo4003.b6.housematch.domain.user.UserRepository;
import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;

public class AdminStatisticService {

   private UserRepository userRepository;

   private UserProcessor userProcessor;

   @Inject
   public AdminStatisticService(UserRepository userRepository, UserProcessor userProcessor) {
      this.userRepository = userRepository;
      this.userProcessor = userProcessor;
   }

   public int getNumberOfActiveBuyer() throws CouldNotAccessDataException, DocumentException {
      List<User> users = userRepository.getAllUser();
      return userProcessor.getNumberOfActiveBuyer(users);
   }

}
