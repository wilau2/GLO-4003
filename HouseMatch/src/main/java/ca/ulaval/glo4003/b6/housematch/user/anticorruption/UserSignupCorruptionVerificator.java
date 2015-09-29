package ca.ulaval.glo4003.b6.housematch.user.anticorruption;

import org.springframework.beans.factory.annotation.Autowired;

import ca.ulaval.glo4003.b6.housematch.user.anticorruption.exceptions.InvalidUserSignupFieldException;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserSignupDto;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.CouldNotAccessUserDataException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UsernameAlreadyExistsException;
import ca.ulaval.glo4003.b6.housematch.user.services.UserSignupService;

public class UserSignupCorruptionVerificator {

   private UserSignupService userSignupService;

   @Autowired
   public UserSignupCorruptionVerificator(UserSignupService userSignupService) {
      this.userSignupService = userSignupService;
   }

   public void signup(UserSignupDto userDto)
         throws InvalidUserSignupFieldException, UsernameAlreadyExistsException, CouldNotAccessUserDataException {
      validateUserSignupCorruption(userDto);
      userSignupService.signup(userDto);
   }

   private void validateUserSignupCorruption(UserSignupDto userDto) throws InvalidUserSignupFieldException {
      String firstName = userDto.getFirstName();
      if (firstName == null || firstName.isEmpty()) {
         throw new InvalidUserSignupFieldException("First name is mandatory");
      }
      String lastName = userDto.getLastName();
      if (lastName == null || lastName.isEmpty()) {
         throw new InvalidUserSignupFieldException("Last name is mandatory");
      }
      String email = userDto.getEmail();
      if (email == null || email.isEmpty()) {
         throw new InvalidUserSignupFieldException("Email is mandatory");
      }
      String phoneNumber = userDto.getPhoneNumber();
      if (phoneNumber == null || phoneNumber.isEmpty()) {
         throw new InvalidUserSignupFieldException("Phone number is mandatory");
      }
      String username = userDto.getUsername();
      if (username == null || username.isEmpty()) {
         throw new InvalidUserSignupFieldException("Username is mandatory");
      }
      String password = userDto.getPassword();
      if (password == null || password.isEmpty()) {
         throw new InvalidUserSignupFieldException("Password is mandatory");
      }
      String role = userDto.getRole();
      if (role == null || role.isEmpty()) {
         throw new InvalidUserSignupFieldException("Role is mandatory");
      }
   }
}
