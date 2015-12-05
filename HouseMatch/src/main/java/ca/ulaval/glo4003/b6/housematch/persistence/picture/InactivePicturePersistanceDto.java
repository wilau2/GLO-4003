package ca.ulaval.glo4003.b6.housematch.persistence.picture;

import java.util.HashMap;

import ca.ulaval.glo4003.b6.housematch.domain.picture.Picture;
import ca.ulaval.glo4003.b6.housematch.persistence.PersistenceDto;

public class InactivePicturePersistanceDto implements PersistenceDto {

   private String elementName = "inactivePicture";

   private HashMap<String, String> attributes;

   public InactivePicturePersistanceDto(Picture picture) {

      HashMap<String, String> attributes = new HashMap<String, String>();
      attributes.put("uid", picture.getUid());
      attributes.put("name", picture.getName());
      attributes.put("address", picture.getAddress());
      attributes.put("active", picture.isActive().toString());

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
