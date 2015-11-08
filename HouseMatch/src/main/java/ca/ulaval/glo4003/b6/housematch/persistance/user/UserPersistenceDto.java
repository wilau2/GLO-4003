package ca.ulaval.glo4003.b6.housematch.persistance.user;

import java.util.HashMap;

import ca.ulaval.glo4003.b6.housematch.domain.user.User;
import ca.ulaval.glo4003.b6.housematch.persistance.PersistenceDto;

public class UserPersistenceDto implements PersistenceDto {

   private String elementName = "user";

   private HashMap<String, String> attributes;

   public UserPersistenceDto(User user) {

      HashMap<String, String> attributes = new HashMap<String, String>();
      attributes.put("role", user.getRole().getRoles());
      attributes.put("username", user.getUsername());
      attributes.put("firstName", user.getContactInformation().getFirstName());
      attributes.put("lastName", user.getContactInformation().getLastName());
      attributes.put("phoneNumber", user.getContactInformation().getPhoneNumber());
      attributes.put("email", user.getContactInformation().getEmail());
      attributes.put("password", user.getPassword());
      attributes.put("isActive", user.isActive().toString());
      attributes.put("dateLastActivity", user.getDateOfLastActivity().toString());
      this.attributes = attributes;
   }

   @Override
   public HashMap<String, String> getAttributes() {
      return attributes;
   }

   @Override
   public String getElementName() {
      return elementName;
   }

}
