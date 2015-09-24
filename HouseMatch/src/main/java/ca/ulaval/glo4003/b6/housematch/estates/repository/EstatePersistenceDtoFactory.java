package ca.ulaval.glo4003.b6.housematch.estates.repository;

import java.util.HashMap;

public class EstatePersistenceDtoFactory {

   public EstatePersistenceDto newInstance(HashMap<String, String> attributes) {
      return new EstatePersistenceDto(attributes);
   }

}
