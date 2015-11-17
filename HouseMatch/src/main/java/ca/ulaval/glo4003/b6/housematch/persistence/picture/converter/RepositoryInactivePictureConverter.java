package ca.ulaval.glo4003.b6.housematch.persistence.picture.converter;

import java.util.HashMap;

import ca.ulaval.glo4003.b6.housematch.domain.picture.InactivePicture;

public class RepositoryInactivePictureConverter {

   public InactivePicture assembleInactivePictureFromAttributes(HashMap<String, String> attributes) {
      return new InactivePicture(attributes.get("uid"), attributes.get("address"), attributes.get("name"));
   }
}
