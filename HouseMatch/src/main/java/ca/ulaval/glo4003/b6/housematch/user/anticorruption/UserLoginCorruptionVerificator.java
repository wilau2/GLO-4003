package ca.ulaval.glo4003.b6.housematch.user.anticorruption;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import ca.ulaval.glo4003.b6.housematch.user.anticorruption.exceptions.InvalidUserLoginFieldException;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserLoginDto;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.CouldNotAccessUserDataException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.user.services.UserLoginService;
import ca.ulaval.glo4003.b6.housematch.user.services.exceptions.InvalidPasswordException;

public class UserLoginCorruptionVerificator {

   private UserLoginService userLoginService;

   @Autowired
   public UserLoginCorruptionVerificator(UserLoginService userLoginService) {
      this.userLoginService = userLoginService;
   }

   public void login(HttpServletRequest request, UserLoginDto userDto) throws InvalidUserLoginFieldException,
         UserNotFoundException, CouldNotAccessUserDataException, InvalidPasswordException {
      validateUserLoginCorruption(userDto);
      userLoginService.login(request, userDto);
   }

   private void validateUserLoginCorruption(UserLoginDto userDto) throws InvalidUserLoginFieldException {
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
