package ca.ulaval.glo4003.b6.housematch.dto.assembler;

import ca.ulaval.glo4003.b6.housematch.domain.user.ContactInformation;
import ca.ulaval.glo4003.b6.housematch.domain.user.Role;
import ca.ulaval.glo4003.b6.housematch.domain.user.User;
import ca.ulaval.glo4003.b6.housematch.dto.ContactInformationDto;
import ca.ulaval.glo4003.b6.housematch.dto.UserDto;

public class UserAssembler {

   private ContactInformationAssembler contactInformationAssembler;

   public UserAssembler(ContactInformationAssembler contactInformationAssembler) {
      this.contactInformationAssembler = contactInformationAssembler;
   }

   public User assembleUser(UserDto userSignupDto) {
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

   public UserDto assembleUserDto(User user) {
      UserDto userDto = new UserDto();
      userDto.setUsername(user.getUsername());
      ContactInformationDto contactInformationDto = new ContactInformationDto(
            user.getContactInformation().getFirstName(), user.getContactInformation().getLastName(),
            user.getContactInformation().getPhoneNumber(), user.getContactInformation().getEmail());
      userDto.setContactInformationDto(contactInformationDto);

      return userDto;
   }

}
