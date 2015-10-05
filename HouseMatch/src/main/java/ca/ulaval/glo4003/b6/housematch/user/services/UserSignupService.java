package ca.ulaval.glo4003.b6.housematch.user.services;

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
import ca.ulaval.glo4003.b6.housematch.user.services.exceptions.BadEmailException;

public class UserSignupService {

   private UserValidatorFactory userValidatorFactory;

   private UserAssemblerFactory userAssemblerFactory;

   private UserRepository userRepository;
   
   private MailService mailService;

   @Autowired
   public UserSignupService(UserValidatorFactory userValidatorFactory, UserAssemblerFactory userAssemblerFactory,
         UserRepository userRepository) {

      this.mailService = new MailService(); // TODO MS
      this.userValidatorFactory = userValidatorFactory;
      this.userAssemblerFactory = userAssemblerFactory;
      this.userRepository = userRepository;

   }

   public void signup(UserSignupDto userSignupDto)
         throws UsernameAlreadyExistsException, CouldNotAccessUserDataException, BadEmailException {

      UserValidator userValidator = userValidatorFactory.getValidator();
      userValidator.validate(userSignupDto);

      UserAssembler userAssembler = userAssemblerFactory.createUserAssembler();
      User newUser = userAssembler.assembleUser(userSignupDto);

      userRepository.addUser(newUser);
      mailService.sendMail(newUser.getContactInformation()); // TODO MS

   }
}
