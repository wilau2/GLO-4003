package ca.ulaval.glo4003.b6.housematch.user.services;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import ca.ulaval.glo4003.b6.housematch.user.domain.User;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserLoginDto;
import ca.ulaval.glo4003.b6.housematch.user.repository.UserRepository;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.CouldNotAccessUserDataException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserNotFoundException;

public class UserLoginService {

   private UserRepository userRepository;

   private UserAuthorisationService userAuthorisationService;

   @Autowired
   public UserLoginService(UserRepository userRepository, UserAuthorisationService userAuthorisationService) {

      this.userRepository = userRepository;
      this.userAuthorisationService = userAuthorisationService;

   }

   public void login(HttpServletRequest request, UserLoginDto userLoginDto)
         throws UserNotFoundException, CouldNotAccessUserDataException, InvalidPasswordException {

      User user = userRepository.findByUsername(userLoginDto.getUsername());

      if (!user.getPassword().equals(userLoginDto.getPassword())) {
         throw new InvalidPasswordException();
      }

      request = userAuthorisationService.setSessionUserAuthorisation(request, user);

   }

}
