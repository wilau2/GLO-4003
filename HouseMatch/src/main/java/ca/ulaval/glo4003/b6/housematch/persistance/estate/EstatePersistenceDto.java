package ca.ulaval.glo4003.b6.housematch.persistance.estate;

import java.util.HashMap;

import ca.ulaval.glo4003.b6.housematch.persistance.PersistenceDto;

public class EstatePersistenceDto implements PersistenceDto {

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
