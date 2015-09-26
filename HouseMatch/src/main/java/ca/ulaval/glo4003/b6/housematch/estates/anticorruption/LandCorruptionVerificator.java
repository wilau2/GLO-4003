package ca.ulaval.glo4003.b6.housematch.estates.anticorruption;

import ca.ulaval.glo4003.b6.housematch.estates.anticorruption.exceptions.InvalidLandFieldException;
import ca.ulaval.glo4003.b6.housematch.estates.dto.LandDto;

public class LandCorruptionVerificator {

   public void validateLandCorruption(LandDto landDto) throws InvalidLandFieldException {
      validateAqueduct(landDto);
      validateDimensionLot(landDto);
   }
   
   private void validateAqueduct(LandDto landDto) throws InvalidLandFieldException {
      if(landDto.getAqueduct() == null || landDto.getAqueduct() == ""){
         throw new InvalidLandFieldException("The entered aqueduct is not valid");
      }
   }
   
   private void validateDimensionLot(LandDto landDto) throws InvalidLandFieldException {
      if(landDto.getDimensionsLot() == null || landDto.getDimensionsLot() == ""){
         throw new InvalidLandFieldException("The entered dimension lot is not valid");
      }
   }

}
