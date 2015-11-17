package ca.ulaval.glo4003.b6.housematch.services.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import ca.ulaval.glo4003.b6.housematch.domain.user.User;
import ca.ulaval.glo4003.b6.housematch.domain.user.UserRepository;
import ca.ulaval.glo4003.b6.housematch.domain.user.exceptions.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.dto.UserDto;
import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.InvalidPasswordException;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.UserActivationException;

public class UserLoginService {

   private UserRepository userRepository;

   private UserAuthorizationService userAuthorizationService;

   @Autowired
   public UserLoginService(UserRepository userRepository, UserAuthorizationService userAuthorizationService) {

      this.userRepository = userRepository;
      this.userAuthorizationService = userAuthorizationService;

   }

   public void login(HttpServletRequest request, UserDto userLoginDto)
         throws UserNotFoundException, CouldNotAccessDataException, InvalidPasswordException, UserActivationException {

      User user = userRepository.getUser(userLoginDto.getUsername());
      validatePassword(userLoginDto, user);
      validateActivation(user);

      user.updateLastActivity();
      userRepository.updateUser(user);

      request = userAuthorizationService.setSessionUserAuthorisation(request, user);

   }

   private void validateActivation(User user) throws UserActivationException {
      if (!user.isActive()) {
         throw new UserActivationException("User is not active");
      }

   }

   private void validatePassword(UserDto userLoginDto, User user) throws InvalidPasswordException {

      if (!user.getPassword().equals(userLoginDto.getPassword())) {
         throw new InvalidPasswordException("This password is not good, you fool");
      }
   }

}
