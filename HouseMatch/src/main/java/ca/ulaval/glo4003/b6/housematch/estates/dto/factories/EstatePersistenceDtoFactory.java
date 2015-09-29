package ca.ulaval.glo4003.b6.housematch.estates.dto.factories;

import java.util.HashMap;

import ca.ulaval.glo4003.b6.housematch.estates.dto.EstatePersistenceDto;

public class EstatePersistenceDtoFactory {

   public EstatePersistenceDto newInstance(HashMap<String, String> attributes) {
      return new EstatePersistenceDto(attributes);
   }

}
