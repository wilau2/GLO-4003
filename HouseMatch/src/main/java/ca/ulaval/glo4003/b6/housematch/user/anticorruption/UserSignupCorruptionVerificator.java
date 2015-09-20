package ca.ulaval.glo4003.b6.housematch.user.anticorruption;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import ca.ulaval.glo4003.b6.housematch.user.anticorruption.exceptions.InvalidUserSignupFieldException;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserSignupDto;
import ca.ulaval.glo4003.b6.housematch.user.services.UserSignupService;

public class UserSignupCorruptionVerificator {

   private UserSignupService userSignupService;

   @Autowired
   public UserSignupCorruptionVerificator(UserSignupService userSignupService) {
      this.userSignupService = userSignupService;
   }

   public void signup(HttpServletRequest request, UserSignupDto userDto) throws InvalidUserSignupFieldException {
      validateUserSignupCorruption(userDto);
      // TODO try catch error
      userSignupService.signup(request, userDto);
   }

   private void validateUserSignupCorruption(UserSignupDto userDto) throws InvalidUserSignupFieldException {
      String firstName = userDto.getFirstName();
      if (firstName == null || firstName.isEmpty()) {
         throw new InvalidUserSignupFieldException();
      }
      String lastName = userDto.getLastName();
      if (lastName == null || lastName.isEmpty()) {
         throw new InvalidUserSignupFieldException();
      }
      String email = userDto.getEmail();
      if (email == null || email.isEmpty()) {
         throw new InvalidUserSignupFieldException();
      }
      String phoneNumber = userDto.getPhoneNumber();
      if (phoneNumber == null || phoneNumber.isEmpty()) {
         throw new InvalidUserSignupFieldException();
      }
      String username = userDto.getUsername();
      if (username == null || username.isEmpty()) {
         throw new InvalidUserSignupFieldException();
      }
      String password = userDto.getPassword();
      if (password == null || password.isEmpty()) {
         throw new InvalidUserSignupFieldException();
      }
   }
}
