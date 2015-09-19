package ca.ulaval.glo4003.b6.housematch.user.domain.assembler;

import ca.ulaval.glo4003.b6.housematch.user.domain.User;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserSignupDto;

public class UserAssembler {

   public User assembleUser(UserSignupDto userDto) {
      String username = userDto.getUsername();
      String firstName = userDto.getFirstName();
      String lastName = userDto.getLastName();
      String phoneNum = userDto.getPhoneNumber();
      String email = userDto.getEmail();
      String password = userDto.getPassword();

      User user = new User(username, firstName, lastName, phoneNum, email, password);
      return user;
   }

}
