package ca.ulaval.glo4003.b6.housematch.user.dto;

import java.util.HashMap;

import ca.ulaval.glo4003.b6.housematch.user.model.User;

public class RepositoryToPersistenceUserDto implements RepositoryToPersistenceDto {

   private String elementName = "user";

   private HashMap<String, String> attributes;

   public RepositoryToPersistenceUserDto(User user) {

      HashMap<String, String> attributes = new HashMap<String, String>();

      attributes.put("username", user.getUsername());
      attributes.put("firstName", user.getFirstName());
      attributes.put("lastName", user.getLastName());
      attributes.put("phoneNumber", user.getPhoneNumber());
      attributes.put("email", user.getEmail());
      attributes.put("password", user.getPassword());

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
