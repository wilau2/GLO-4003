package ca.ulaval.glo4003.b6.housematch.user.services;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import ca.ulaval.glo4003.b6.housematch.user.domain.User;
import ca.ulaval.glo4003.b6.housematch.user.domain.assembler.UserAssembler;
import ca.ulaval.glo4003.b6.housematch.user.domain.assembler.factory.UserAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserDetailedDto;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserSignupDto;
import ca.ulaval.glo4003.b6.housematch.user.dto.validators.UserValidator;
import ca.ulaval.glo4003.b6.housematch.user.dto.validators.factory.UserValidatorFactory;
import ca.ulaval.glo4003.b6.housematch.user.repository.UserDao;

public class UserSignupService {

   private UserValidatorFactory userValidatorFactory;

   private UserAssemblerFactory userAssemblerFactory;

   private UserDao userRepository;

   @Autowired
   public UserSignupService(UserValidatorFactory userValidatorFactory, UserAssemblerFactory userAssemblerFactory,
         UserDao userRepository) {
      this.userValidatorFactory = userValidatorFactory;
      this.userAssemblerFactory = userAssemblerFactory;
      this.userRepository = userRepository;
   }

   public UserDetailedDto signup(HttpServletRequest request, UserSignupDto userSignupDto) {
      UserValidator userValidator = userValidatorFactory.getValidator();
      userValidator.validate(userSignupDto);

      UserAssembler userAssembler = userAssemblerFactory.createUserAssembler();
      User newUser = userAssembler.assembleUser(userSignupDto);

      userRepository.add(newUser);
      return userAssembler.convertUserToDetailedDto(newUser);
   }
}