package ca.ulaval.glo4003.b6.housematch.anticorruption.user;

import ca.ulaval.glo4003.b6.housematch.anticorruption.user.exceptions.InvalidUserLoginFieldException;
import ca.ulaval.glo4003.b6.housematch.dto.UserDto;

public class UserLoginCorruptionVerificator {

   public void validateUserLoginCorruption(UserDto userDto) throws InvalidUserLoginFieldException {
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
