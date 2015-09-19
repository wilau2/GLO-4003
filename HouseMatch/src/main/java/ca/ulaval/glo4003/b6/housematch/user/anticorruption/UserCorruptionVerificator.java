package ca.ulaval.glo4003.b6.housematch.user.anticorruption;

import javax.servlet.http.HttpServletRequest;

import ca.ulaval.glo4003.b6.housematch.user.anticorruption.exceptions.InvalidUserLoginFieldException;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserDto;
import ca.ulaval.glo4003.b6.housematch.user.service.UserLoginService;

public class UserCorruptionVerificator {

   private UserLoginService userLoginService;

   public UserCorruptionVerificator(UserLoginService userLoginService) {
      this.userLoginService = userLoginService;
   }

   public void login(HttpServletRequest request, UserDto userDto) throws InvalidUserLoginFieldException {
      validateUserLoginCorruption(userDto);
      // TODO try catch error
      userLoginService.login(request, userDto);
   }

   private void validateUserLoginCorruption(UserDto userDto) throws InvalidUserLoginFieldException {
      String username = userDto.getUsername();
      if (username == null || username.isEmpty()) {
         throw new InvalidUserLoginFieldException();
      }
      String password = userDto.getPassword();
      if (password == null || password.isEmpty()) {
         throw new InvalidUserLoginFieldException();
      }
   }

}
