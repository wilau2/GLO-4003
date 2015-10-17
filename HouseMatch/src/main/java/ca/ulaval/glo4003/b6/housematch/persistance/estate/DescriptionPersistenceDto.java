package ca.ulaval.glo4003.b6.housematch.persistance.estate;

import java.util.HashMap;

import ca.ulaval.glo4003.b6.housematch.persistance.RepositoryToPersistenceDto;

public class DescriptionPersistenceDto implements RepositoryToPersistenceDto {

   private static final String ELEMENT_NAME = "description";
   
   private HashMap<String, String> attributes;

   public DescriptionPersistenceDto(HashMap<String, String> attributes) {
      this.attributes = attributes;
   }

   @Override
   public HashMap<String, String> getAttributes() {
      return attributes;
   }

   @Override
   public String getElementName() {
      return ELEMENT_NAME;
   }

}
