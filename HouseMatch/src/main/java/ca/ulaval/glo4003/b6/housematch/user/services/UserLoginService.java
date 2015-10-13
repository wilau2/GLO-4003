package ca.ulaval.glo4003.b6.housematch.user.services;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import ca.ulaval.glo4003.b6.housematch.user.domain.User;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserLoginDto;
import ca.ulaval.glo4003.b6.housematch.user.repository.UserRepository;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.CouldNotAccessUserDataException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.user.services.exceptions.InvalidPasswordException;
import ca.ulaval.glo4003.b6.housematch.user.services.exceptions.UserActivationException;

public class UserLoginService {

   private UserRepository userRepository;

   private UserAuthorizationService userAuthorizationService;

   @Autowired
   public UserLoginService(UserRepository userRepository, UserAuthorizationService userAuthorizationService) {

      this.userRepository = userRepository;
      this.userAuthorizationService = userAuthorizationService;

   }

   public void login(HttpServletRequest request, UserLoginDto userLoginDto)
         throws UserNotFoundException, CouldNotAccessUserDataException, InvalidPasswordException, UserActivationException {

      User user = userRepository.getUser(userLoginDto.getUsername());

      validatePassword(userLoginDto, user);
      validateActivation(user);

      request = userAuthorizationService.setSessionUserAuthorisation(request, user);

   }

   private void validateActivation(User user) throws UserActivationException {
      if(!user.isActive()){
         throw new UserActivationException("User is not active");
      }
      
   }

   private void validatePassword(UserLoginDto userLoginDto, User user) throws InvalidPasswordException {
      if (!user.getPassword().equals(userLoginDto.getPassword())) {
         throw new InvalidPasswordException("This password is not good, you fool");
      }
   }

}
