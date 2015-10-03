package ca.ulaval.glo4003.b6.housematch.estates.dto;

import java.util.HashMap;

import ca.ulaval.glo4003.b6.housematch.persistance.RepositoryToPersistenceDto;;

public class EstatePersistenceDto implements RepositoryToPersistenceDto {

   private static final String ELEMENT_NAME = "estate";

   private HashMap<String, String> attributes;

   public EstatePersistenceDto(HashMap<String, String> attributes) {
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
