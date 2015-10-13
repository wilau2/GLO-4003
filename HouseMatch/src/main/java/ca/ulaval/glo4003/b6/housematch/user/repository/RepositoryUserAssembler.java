package ca.ulaval.glo4003.b6.housematch.user.repository;

import java.util.HashMap;

import ca.ulaval.glo4003.b6.housematch.user.domain.ContactInformation;
import ca.ulaval.glo4003.b6.housematch.user.domain.Role;
import ca.ulaval.glo4003.b6.housematch.user.domain.User;

public class RepositoryUserAssembler {
   
   public User assembleUserFromAttributes(HashMap<String, String> attributes){
      ContactInformation contactInformation = new ContactInformation(attributes.get("firstName"),
            attributes.get("lastName"), attributes.get("phoneNumber"), attributes.get("email"));

      User user = new User(attributes.get("username"), attributes.get("password"), contactInformation,
            new Role(attributes.get("role")));
      return user;
   }
}
