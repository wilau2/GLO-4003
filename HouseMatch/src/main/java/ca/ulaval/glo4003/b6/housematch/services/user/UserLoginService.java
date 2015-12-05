package ca.ulaval.glo4003.b6.housematch.services.user;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import ca.ulaval.glo4003.b6.housematch.anticorruption.user.UserLoginCorruptionVerificator;
import ca.ulaval.glo4003.b6.housematch.anticorruption.user.exceptions.InvalidUserLoginFieldException;
import ca.ulaval.glo4003.b6.housematch.domain.user.User;
import ca.ulaval.glo4003.b6.housematch.domain.user.UserRepository;
import ca.ulaval.glo4003.b6.housematch.domain.user.exceptions.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.dto.UserDto;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.InvalidPasswordException;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.UserActivationException;

public class UserLoginService {

   private UserRepository userRepository;

   private UserSessionAuthorizationService userSessionAuthorizationService;

   private UserLoginCorruptionVerificator userLoginCorruptionVerificator;

   @Inject
   public UserLoginService(UserLoginCorruptionVerificator userLoginCorruptionVerificator, UserRepository userRepository,
         UserSessionAuthorizationService userSessionAuthorizationService) {
      this.userLoginCorruptionVerificator = userLoginCorruptionVerificator;
      this.userRepository = userRepository;
      this.userSessionAuthorizationService = userSessionAuthorizationService;

   }

   public void login(HttpServletRequest request, UserDto userLoginDto)
         throws UserNotFoundException, CouldNotAccessDataException, InvalidPasswordException, UserActivationException,
         InvalidUserLoginFieldException {
      userLoginCorruptionVerificator.validateUserLoginCorruption(userLoginDto);

      User user = userRepository.getUser(userLoginDto.getUsername());
      validatePassword(userLoginDto, user);
      validateActivation(user);

      user.updateLastActivity();
      userRepository.update(user);

      request = userSessionAuthorizationService.setSessionUserAuthorisation(request, user);

   }

   private void validateActivation(User user) throws UserActivationException {
      if (!user.isActive()) {
         throw new UserActivationException("User is not active");
      }
   }

   private void validatePassword(UserDto userLoginDto, User user) throws InvalidPasswordException {
      if (!user.validateLoginCredential(userLoginDto.getPassword())) {
         throw new InvalidPasswordException("This password is not good, you fool");
      }
   }

}
