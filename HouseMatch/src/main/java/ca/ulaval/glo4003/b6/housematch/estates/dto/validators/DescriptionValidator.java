package ca.ulaval.glo4003.b6.housematch.estates.dto.validators;

import ca.ulaval.glo4003.b6.housematch.estates.dto.DescriptionDto;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.InvalidDescriptionException;

//TODO eventuellement regarder la validité des information dans le domaines
public class DescriptionValidator {

   public void validate(DescriptionDto descriptionDto) throws InvalidDescriptionException {
      if (numberSmallerThanOne(descriptionDto.getNumberOfBathrooms())){
         throw new InvalidDescriptionException("invalid number of bathrooms");
      }
      if(numberSmallerThanOne(descriptionDto.getLivingSpaceAreaSquareMeter())){
         throw new InvalidDescriptionException("invalid area square meter");
      }
      if(numberSmallerThanOne(descriptionDto.getMunicipalValuation())){
         throw new InvalidDescriptionException("invalid municipal valuation");
      }
      if(numberSmallerThanOne(descriptionDto.getNumberOfBedRooms())){
         throw new InvalidDescriptionException("invalid number of bed rooms");
      }
      if(numberSmallerThanOne(descriptionDto.getNumberOfLevel())){
         throw new InvalidDescriptionException("invalid number of levels");
      }
      if(numberSmallerThanOne(descriptionDto.getNumberOfRooms())){
         throw new InvalidDescriptionException("invalid number of rooms");
      }
      if(numberSmallerThanOne(descriptionDto.getYearsOfConstruction())){
         throw new InvalidDescriptionException("invalid year of construction");
      }
   }
   
   private boolean numberSmallerThanOne(Integer integer){
         if (integer <= 0){
            return true;
         }
         return false;        
   }
}
