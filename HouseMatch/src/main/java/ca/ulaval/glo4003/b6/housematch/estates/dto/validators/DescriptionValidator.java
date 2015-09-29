package ca.ulaval.glo4003.b6.housematch.estates.dto.validators;

import ca.ulaval.glo4003.b6.housematch.estates.dto.DescriptionDto;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.InvalidDescriptionException;

//TODO eventuellement regarder la validité des information dans le domaines
public class DescriptionValidator {

   public void validate(DescriptionDto descriptionDto) throws InvalidDescriptionException {
      if (isNumberSmallerThanOne(descriptionDto.getNumberOfBathrooms())){
         throw new InvalidDescriptionException("invalid number of bathrooms");
      }
      if(isNumberSmallerThanOne(descriptionDto.getLivingSpaceAreaSquareMeter())){
         throw new InvalidDescriptionException("invalid area square meter");
      }
      if(isNumberSmallerThanOne(descriptionDto.getMunicipalValuation())){
         throw new InvalidDescriptionException("invalid municipal valuation");
      }
      if(isNumberSmallerThanOne(descriptionDto.getNumberOfBedRooms())){
         throw new InvalidDescriptionException("invalid number of bed rooms");
      }
      if(isNumberSmallerThanOne(descriptionDto.getNumberOfLevel())){
         throw new InvalidDescriptionException("invalid number of levels");
      }
      if(isNumberSmallerThanOne(descriptionDto.getNumberOfRooms())){
         throw new InvalidDescriptionException("invalid number of rooms");
      }
      if(isNumberSmallerThanOne(descriptionDto.getYearsOfConstruction())){
         throw new InvalidDescriptionException("invalid year of construction");
      }
   }
   
   private boolean isNumberSmallerThanOne(Integer integer){
         if (integer <= 0){
            return true;
         }
         return false;        
   }
}
