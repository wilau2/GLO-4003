package ca.ulaval.glo4003.b6.housematch.persistence.picture.converter;

import java.util.HashMap;

import ca.ulaval.glo4003.b6.housematch.domain.picture.Picture;

public class RepositoryInactivePictureConverter {

   public Picture assembleInactivePictureFromAttributes(HashMap<String, String> attributes) {
      return new Picture(attributes.get("uid"), attributes.get("address"), attributes.get("name"),
            attributes.get("active"));

   }
}
