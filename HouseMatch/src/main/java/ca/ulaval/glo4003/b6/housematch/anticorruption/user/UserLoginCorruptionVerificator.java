package ca.ulaval.glo4003.b6.housematch.anticorruption.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import ca.ulaval.glo4003.b6.housematch.anticorruption.user.exceptions.InvalidUserLoginFieldException;
import ca.ulaval.glo4003.b6.housematch.domain.user.exceptions.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.dto.UserDto;
import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.user.UserLoginService;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.InvalidPasswordException;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.UserActivationException;

public class UserLoginCorruptionVerificator {

   private UserLoginService userLoginService;

   @Autowired
   public UserLoginCorruptionVerificator(UserLoginService userLoginService) {
      this.userLoginService = userLoginService;
   }

   public void login(HttpServletRequest request, UserDto userDto) throws InvalidUserLoginFieldException,
         UserNotFoundException, CouldNotAccessDataException, InvalidPasswordException, UserActivationException {

      validateUserLoginCorruption(userDto);
      userLoginService.login(request, userDto);
   }

   private void validateUserLoginCorruption(UserDto userDto) throws InvalidUserLoginFieldException {
      String username = userDto.getUsername();
      if (username == null || username.isEmpty()) {
         throw new InvalidUserLoginFieldException("Username is mandatory");
      }
      String password = userDto.getPassword();
      if (password == null || password.isEmpty()) {
         throw new InvalidUserLoginFieldException("Password is mandatory");
      }
   }

}
