package ca.ulaval.glo4003.b6.housematch.user.anticorruption;

import ca.ulaval.glo4003.b6.housematch.user.anticorruption.exceptions.InvalidContactInformationFieldException;
import ca.ulaval.glo4003.b6.housematch.user.dto.ContactInformationDto;

public class ContactInformationCorruptionVerificator {

   public void validateContactInformationCorruption(ContactInformationDto contactInformationDto)
         throws InvalidContactInformationFieldException {
      String firstName = contactInformationDto.getFirstName();
      if (firstName == null || firstName.isEmpty()) {
         throw new InvalidContactInformationFieldException("First name is mandatory");
      }
      String lastName = contactInformationDto.getLastName();
      if (lastName == null || lastName.isEmpty()) {
         throw new InvalidContactInformationFieldException("Last name is mandatory");
      }
      String email = contactInformationDto.getEmail();
      if (email == null || email.isEmpty()) {
         throw new InvalidContactInformationFieldException("Email is mandatory");
      }
      String phoneNumber = contactInformationDto.getPhoneNumber();
      if (phoneNumber == null || phoneNumber.isEmpty()) {
         throw new InvalidContactInformationFieldException("Phone number is mandatory");
      }
   }
}
