package ca.ulaval.glo4003.b6.housematch.estates.domain.assembler;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Land;
import ca.ulaval.glo4003.b6.housematch.estates.dto.LandDto;

public class LandAssembler {

   public Land assembleLand(LandDto landDto){
         String aqueduct = landDto.getAqueduct();
         String dimensionsLot = landDto.getDimensionsLot();
         
         Land land = new Land(aqueduct,dimensionsLot);

         return land;
   }
   
   public LandDto assembleLandDto(Land land){
      if (land == null){
         return new LandDto();
      }
      String aqueduct = land.getAqueduct();
      String dimensionsLot = land.getDimensionsLot();
      
      LandDto landDto = new LandDto(aqueduct,dimensionsLot);

      return landDto;
   }
}
