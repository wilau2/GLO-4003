package ca.ulaval.glo4003.b6.housematch.persistence.estate;

import java.util.HashMap;

import ca.ulaval.glo4003.b6.housematch.persistence.PersistenceDto;

class EstatePersistenceDto implements PersistenceDto {

   private static final String ELEMENT_NAME = "estate";

   private HashMap<String, String> attributes;

   EstatePersistenceDto(HashMap<String, String> attributes) {
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
