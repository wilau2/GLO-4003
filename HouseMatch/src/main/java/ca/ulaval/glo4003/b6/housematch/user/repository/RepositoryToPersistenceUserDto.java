package ca.ulaval.glo4003.b6.housematch.user.repository;

import java.util.HashMap;

import ca.ulaval.glo4003.b6.housematch.persistance.RepositoryToPersistenceDto;
import ca.ulaval.glo4003.b6.housematch.user.domain.User;

public class RepositoryToPersistenceUserDto implements RepositoryToPersistenceDto {

   private String elementName = "user";

   private HashMap<String, String> attributes;

   public RepositoryToPersistenceUserDto(User user) {

      HashMap<String, String> attributes = new HashMap<String, String>();

      attributes.put("username", user.getUsername());
      attributes.put("firstName", user.getContactInformation().getFirstName());
      attributes.put("lastName", user.getContactInformation().getLastName());
      attributes.put("phoneNumber", user.getContactInformation().getPhoneNumber());
      attributes.put("email", user.getContactInformation().getEmail());
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
