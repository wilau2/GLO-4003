package ca.ulaval.glo4003.b6.housematch.estates.repository;

import java.util.HashMap;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Estate;
import ca.ulaval.glo4003.b6.housematch.persistance.RepositoryToPersistenceDto;

public class RepositoryToPersistenceEstateDto implements RepositoryToPersistenceDto {

   private String elementName = "estate";

   private HashMap<String, String> attributes;

   public RepositoryToPersistenceEstateDto(Estate estate) {

      HashMap<String, String> attributes = new HashMap<String, String>();

      attributes.put("type", estate.getType());
      attributes.put("address", estate.getAddress());
      attributes.put("price", estate.getPrice().toString());

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
