package ca.ulaval.glo4003.b6.housematch.persistence.estate;

import java.util.HashMap;

public class EstatePersistenceDtoFactory {

   public EstatePersistenceDto newInstanceEstate(HashMap<String, String> attributes) {
      return new EstatePersistenceDto(attributes);
   }

   public DescriptionPersistenceDto newInstanceDescription(HashMap<String, String> attributes) {
      return new DescriptionPersistenceDto(attributes);
   }

}
