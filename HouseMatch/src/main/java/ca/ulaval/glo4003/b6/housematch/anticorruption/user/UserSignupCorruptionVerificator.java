package ca.ulaval.glo4003.b6.housematch.anticorruption.user;

import org.springframework.beans.factory.annotation.Autowired;

import ca.ulaval.glo4003.b6.housematch.anticorruption.user.exceptions.InvalidContactInformationFieldException;
import ca.ulaval.glo4003.b6.housematch.anticorruption.user.exceptions.InvalidUserSignupFieldException;
import ca.ulaval.glo4003.b6.housematch.domain.user.exceptions.UsernameAlreadyExistsException;
import ca.ulaval.glo4003.b6.housematch.dto.UserDto;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.user.UserSignupService;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.UserNotifyingException;

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
         CouldNotAccessDataException, InvalidContactInformationFieldException, UserNotifyingException {

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
