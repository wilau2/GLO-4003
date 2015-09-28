package ca.ulaval.glo4003.b6.housematch.user.services;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import ca.ulaval.glo4003.b6.housematch.user.domain.User;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserLoginDto;
import ca.ulaval.glo4003.b6.housematch.user.repository.UserRepository;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.CouldNotAccessUserDataException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.user.services.exceptions.InvalidPasswordException;

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

      User user = userRepository.getUser(userLoginDto.getUsername());

      validatePassword(userLoginDto, user);

      request = userAuthorisationService.setSessionUserAuthorisation(request, user);

   }

   private void validatePassword(UserLoginDto userLoginDto, User user) throws InvalidPasswordException {
      if (!user.getPassword().equals(userLoginDto.getPassword())) {
         throw new InvalidPasswordException("This password good, you fool");
      }
   }

}
