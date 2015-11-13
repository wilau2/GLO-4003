package ca.ulaval.glo4003.b6.housematch.persistence.user.converter;

import java.util.HashMap;

import ca.ulaval.glo4003.b6.housematch.domain.user.ContactInformation;
import ca.ulaval.glo4003.b6.housematch.domain.user.Role;
import ca.ulaval.glo4003.b6.housematch.domain.user.User;

public class RepositoryUserConverter {

   public User assembleUserFromAttributes(HashMap<String, String> attributes) {
      ContactInformation contactInformation = new ContactInformation(attributes.get("firstName"),
            attributes.get("lastName"), attributes.get("phoneNumber"), attributes.get("email"));

      User user = new User(attributes.get("username"), attributes.get("password"), contactInformation,
            new Role(attributes.get("role")));
      return user;
   }
}
