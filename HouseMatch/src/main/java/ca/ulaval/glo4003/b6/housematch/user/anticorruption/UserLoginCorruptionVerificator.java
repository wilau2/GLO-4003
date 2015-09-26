package ca.ulaval.glo4003.b6.housematch.user.anticorruption;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import ca.ulaval.glo4003.b6.housematch.admin.repository.exception.CouldNotAccesAdminDataException;
import ca.ulaval.glo4003.b6.housematch.user.anticorruption.exceptions.InvalidUserLoginFieldException;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserLoginDto;
import ca.ulaval.glo4003.b6.housematch.user.services.UserLoginService;

public class UserLoginCorruptionVerificator {

   private UserLoginService userLoginService;

   public void setUserLoginService(UserLoginService userLoginService) {
      this.userLoginService = userLoginService;
   }

   @Autowired
   public UserLoginCorruptionVerificator(UserLoginService userLoginService) {
      this.userLoginService = userLoginService;
   }

   public void login(HttpServletRequest request, UserLoginDto userDto)
         throws InvalidUserLoginFieldException, CouldNotAccesAdminDataException {
      validateUserLoginCorruption(userDto);
      // TODO try catch error
      userLoginService.login(request, userDto);
   }

   private void validateUserLoginCorruption(UserLoginDto userDto) throws InvalidUserLoginFieldException {
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
