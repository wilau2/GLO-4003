package ca.ulaval.glo4003.b6.housematch.dto.assembler;

import ca.ulaval.glo4003.b6.housematch.domain.user.ContactInformation;
import ca.ulaval.glo4003.b6.housematch.dto.ContactInformationDto;

public class ContactInformationAssembler {

   public ContactInformation assembleContactInformation(ContactInformationDto contactInformationDto) {
      String firstName = contactInformationDto.getFirstName();
      String lastName = contactInformationDto.getLastName();
      String phoneNumber = contactInformationDto.getPhoneNumber();
      String email = contactInformationDto.getEmail();

      ContactInformation contactInformation = new ContactInformation(firstName, lastName, phoneNumber, email);

      return contactInformation;
   }
}
