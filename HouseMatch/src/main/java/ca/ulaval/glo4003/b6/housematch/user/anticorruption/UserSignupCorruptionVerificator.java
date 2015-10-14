package ca.ulaval.glo4003.b6.housematch.user.anticorruption;

import org.springframework.beans.factory.annotation.Autowired;

import ca.ulaval.glo4003.b6.housematch.user.anticorruption.exceptions.InvalidContactInformationFieldException;
import ca.ulaval.glo4003.b6.housematch.user.anticorruption.exceptions.InvalidUserSignupFieldException;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserDto;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.CouldNotAccessUserDataException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UsernameAlreadyExistsException;
import ca.ulaval.glo4003.b6.housematch.user.services.UserSignupService;

public class UserSignupCorruptionVerificator {

   private UserSignupService userSignupService;

   private ContactInformationCorruptionVerificator contactInformationCorruptionVerificator;

   @Autowired
   public UserSignupCorruptionVerificator(UserSignupService userSignupService,
         ContactInformationCorruptionVerificator contactInformationCorruptionVerificator) {
      this.userSignupService = userSignupService;
      this.contactInformationCorruptionVerificator = contactInformationCorruptionVerificator;
   }

   public void signup(UserDto userDto) throws InvalidUserSignupFieldException, UsernameAlreadyExistsException,
         CouldNotAccessUserDataException, InvalidContactInformationFieldException {
      validateUserSignupCorruption(userDto);
      contactInformationCorruptionVerificator.validateContactInformationCorruption(userDto.getContactInformationDto());
      userSignupService.signup(userDto);
   }

   private void validateUserSignupCorruption(UserDto userDto) throws InvalidUserSignupFieldException {

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
