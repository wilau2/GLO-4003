package ca.ulaval.glo4003.b6.housematch.estates.dto.factories;

import java.util.HashMap;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Description;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstatePersistenceDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.DescriptionPersistenceDto;;

public class EstatePersistenceDtoFactory {

   public EstatePersistenceDto newInstanceEstate(HashMap<String, String> attributes) {
      return new EstatePersistenceDto(attributes);
   }

   public DescriptionPersistenceDto newInstanceDescription(HashMap<String, String> attributes) {
      return new DescriptionPersistenceDto(attributes);
   }

}
