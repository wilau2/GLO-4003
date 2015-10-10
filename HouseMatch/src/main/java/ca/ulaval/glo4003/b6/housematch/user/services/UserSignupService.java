package ca.ulaval.glo4003.b6.housematch.user.services;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;

import ca.ulaval.glo4003.b6.housematch.user.domain.User;
import ca.ulaval.glo4003.b6.housematch.user.domain.assembler.UserAssembler;
import ca.ulaval.glo4003.b6.housematch.user.domain.assembler.factory.UserAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserSignupDto;
import ca.ulaval.glo4003.b6.housematch.user.dto.validators.UserValidator;
import ca.ulaval.glo4003.b6.housematch.user.dto.validators.factory.UserValidatorFactory;
import ca.ulaval.glo4003.b6.housematch.user.repository.UserRepository;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.CouldNotAccessUserDataException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UsernameAlreadyExistsException;
import ca.ulaval.glo4003.b6.housematch.user.services.exceptions.UserNotifyingException;
import ca.ulaval.glo4003.b6.housematch.user.services.observer.MailSenderObserver;
import ca.ulaval.glo4003.b6.housematch.user.services.observer.UserObserver;

public class UserSignupService {

   private UserValidatorFactory userValidatorFactory;

   private UserAssemblerFactory userAssemblerFactory;

   private UserRepository userRepository;
 
   
   private List<UserObserver> observers; 

   @Autowired
   public UserSignupService(UserValidatorFactory userValidatorFactory, UserAssemblerFactory userAssemblerFactory,
         UserRepository userRepository, List<UserObserver> observers) {

      this.userValidatorFactory = userValidatorFactory;
      this.userAssemblerFactory = userAssemblerFactory;
      this.userRepository = userRepository;
      this.observers = observers;
   }

   public void signup(UserSignupDto userSignupDto)
         throws UsernameAlreadyExistsException, CouldNotAccessUserDataException, UserNotifyingException{

      UserValidator userValidator = userValidatorFactory.getValidator();
      userValidator.validate(userSignupDto);

      UserAssembler userAssembler = userAssemblerFactory.createUserAssembler();
      User newUser = userAssembler.assembleUser(userSignupDto);

      userRepository.addUser(newUser);
      notifyAllObservers(newUser);

   }
   
   public void notifyAllObservers(User user) throws UserNotifyingException{
      for (UserObserver observer : observers) {
         observer.update(user);
      }
   }    
}
