package ca.ulaval.glo4003.b6.housematch.user.domain.assembler;

import ca.ulaval.glo4003.b6.housematch.user.domain.ContactInformation;
import ca.ulaval.glo4003.b6.housematch.user.domain.Role;
import ca.ulaval.glo4003.b6.housematch.user.domain.User;
import ca.ulaval.glo4003.b6.housematch.user.dto.ContactInformationDto;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserSignupDto;

public class UserAssembler {

   private ContactInformationAssembler contactInformationAssembler;

   public UserAssembler(ContactInformationAssembler contactInformationAssembler) {
      this.contactInformationAssembler = contactInformationAssembler;
   }

   public User assembleUser(UserSignupDto userSignupDto) {
      String username = userSignupDto.getUsername();
      ContactInformationDto contactInformationDto = userSignupDto.getContactInformationDto();
      String password = userSignupDto.getPassword();
      String role = userSignupDto.getRole();

      ContactInformation contactInformation = contactInformationAssembler
            .assembleContactInformation(contactInformationDto);
      Role userRole = new Role(role);
      User user = new User(username, password, contactInformation, userRole);

      return user;
   }

}
