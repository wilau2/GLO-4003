package ca.ulaval.glo4003.b6.housematch.user.domain.assembler;

import ca.ulaval.glo4003.b6.housematch.user.domain.ContactInformation;
import ca.ulaval.glo4003.b6.housematch.user.domain.Role;
import ca.ulaval.glo4003.b6.housematch.user.domain.User;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserDetailedDto;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserSignupDto;

public class UserAssembler {

   public User assembleUser(UserSignupDto userSignupDto) {
      String username = userSignupDto.getUsername();
      String firstName = userSignupDto.getFirstName();
      String lastName = userSignupDto.getLastName();
      String phoneNumber = userSignupDto.getPhoneNumber();
      String email = userSignupDto.getEmail();
      String password = userSignupDto.getPassword();
      String role = userSignupDto.getRole();

      ContactInformation contactInformation = new ContactInformation(firstName, lastName, phoneNumber, email);
      Role userRole = new Role(role);
      User user = new User(username, password, contactInformation, userRole);
      return user;
   }

   public UserDetailedDto convertUserToDetailedDto(User user) {
      UserDetailedDto userDetailedDto = new UserDetailedDto(user.getUsername());
      userDetailedDto.setFirstName(user.getContactInformation().getFirstName());
      userDetailedDto.setLastName(user.getContactInformation().getLastName());
      userDetailedDto.setEmail(user.getContactInformation().getEmail());
      userDetailedDto.setPhoneNumber(user.getPassword());

      return userDetailedDto;
   }

}
