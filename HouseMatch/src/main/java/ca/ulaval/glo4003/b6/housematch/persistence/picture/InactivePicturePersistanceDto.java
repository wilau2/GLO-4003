package ca.ulaval.glo4003.b6.housematch.persistence.picture;

import java.util.HashMap;

import ca.ulaval.glo4003.b6.housematch.domain.picture.Picture;
import ca.ulaval.glo4003.b6.housematch.persistence.PersistenceDto;

public class InactivePicturePersistanceDto implements PersistenceDto {

   private String elementName = "inactivePicture";

   private HashMap<String, String> attributes;

   public InactivePicturePersistanceDto(Picture inactivePicture) {

      HashMap<String, String> attributes = new HashMap<String, String>();
      attributes.put("uid", inactivePicture.getUid());
      attributes.put("name", inactivePicture.getName());
      attributes.put("address", inactivePicture.getAddress());
      attributes.put("active", inactivePicture.getActive().toString());

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