package ca.ulaval.glo4003.b6.housematch.anticorruption.user;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.base.Strings;

import ca.ulaval.glo4003.b6.housematch.anticorruption.user.exceptions.InvalidContactInformationFieldException;
import ca.ulaval.glo4003.b6.housematch.anticorruption.user.exceptions.InvalidUserSignupFieldException;
import ca.ulaval.glo4003.b6.housematch.domain.user.exceptions.UsernameAlreadyExistsException;
import ca.ulaval.glo4003.b6.housematch.dto.UserDto;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.UserNotifyingException;

public class UserSignupCorruptionVerificator {

   private ContactInformationCorruptionVerificator contactInformationCorruptionVerificator;

   @Autowired
   public UserSignupCorruptionVerificator(
         ContactInformationCorruptionVerificator contactInformationCorruptionVerificator) {

      this.contactInformationCorruptionVerificator = contactInformationCorruptionVerificator;
   }

   public void validateSignup(UserDto userDto) throws InvalidUserSignupFieldException, UsernameAlreadyExistsException,
         CouldNotAccessDataException, InvalidContactInformationFieldException, UserNotifyingException {

      validateUserSignupCorruption(userDto);
      contactInformationCorruptionVerificator.validateContactInformationCorruption(userDto.getContactInformationDto());
   }

   private void validateUserSignupCorruption(UserDto userDto) throws InvalidUserSignupFieldException {

      String username = userDto.getUsername();
      if (Strings.isNullOrEmpty(username)) {
         throw new InvalidUserSignupFieldException("Username is mandatory");
      }
      String password = userDto.getPassword();
      if (Strings.isNullOrEmpty(password)) {
         throw new InvalidUserSignupFieldException("Password is mandatory");
      }
      String role = userDto.getRole();
      if (Strings.isNullOrEmpty(role)) {
         throw new InvalidUserSignupFieldException("Role is mandatory");
      }
   }
}
