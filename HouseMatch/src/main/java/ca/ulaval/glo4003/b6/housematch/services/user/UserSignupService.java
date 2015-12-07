package ca.ulaval.glo4003.b6.housematch.services.user;

import java.util.List;

import javax.inject.Inject;

import ca.ulaval.glo4003.b6.housematch.anticorruption.user.UserSignupCorruptionVerificator;
import ca.ulaval.glo4003.b6.housematch.anticorruption.user.exceptions.InvalidContactInformationFieldException;
import ca.ulaval.glo4003.b6.housematch.anticorruption.user.exceptions.InvalidUserSignupFieldException;
import ca.ulaval.glo4003.b6.housematch.domain.user.User;
import ca.ulaval.glo4003.b6.housematch.domain.user.UserRepository;
import ca.ulaval.glo4003.b6.housematch.domain.user.exceptions.UsernameAlreadyExistsException;
import ca.ulaval.glo4003.b6.housematch.dto.UserDto;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.UserAssembler;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.factory.UserAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.UserNotifyingException;

public class UserSignupService {

   private UserAssemblerFactory userAssemblerFactory;

   private UserRepository userRepository;

   private List<UserObserver> observers;

   private UserSignupCorruptionVerificator userSignupCorruptionVerificator;

   @Inject
   public UserSignupService(UserSignupCorruptionVerificator userSignupCorruptionVerificator,
         UserAssemblerFactory userAssemblerFactory, UserRepository userRepository, List<UserObserver> observers) {
      this.userSignupCorruptionVerificator = userSignupCorruptionVerificator;
      this.userAssemblerFactory = userAssemblerFactory;
      this.userRepository = userRepository;
      this.observers = observers;
   }

   public void signup(UserDto userSignupDto) throws UsernameAlreadyExistsException, CouldNotAccessDataException,
         UserNotifyingException, InvalidUserSignupFieldException, InvalidContactInformationFieldException {
      userSignupCorruptionVerificator.validateSignup(userSignupDto);

      UserAssembler userAssembler = userAssemblerFactory.createUserAssembler();
      User newUser = userAssembler.assembleUser(userSignupDto);

      userRepository.add(newUser);
      notifyAllObservers(newUser);
   }

   private void notifyAllObservers(User user) throws UserNotifyingException {
      for (UserObserver observer : observers) {
         observer.notifyUserChanged(user);
      }
   }
}
