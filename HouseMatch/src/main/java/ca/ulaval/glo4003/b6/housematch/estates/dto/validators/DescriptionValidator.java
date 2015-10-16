package ca.ulaval.glo4003.b6.housematch.estates.dto.validators;

import ca.ulaval.glo4003.b6.housematch.estates.dto.DescriptionDto;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.InvalidDescriptionException;

public class DescriptionValidator {

   public void validate(DescriptionDto descriptionDto) throws InvalidDescriptionException {
      if (isNumberSmallerThanOne(descriptionDto.getNumberOfBathrooms())){
         throw new InvalidDescriptionException("Invalid number of bathrooms");
      }
      if(isNumberSmallerThanOne(descriptionDto.getLivingSpaceAreaSquareMeter())){
         throw new InvalidDescriptionException("Invalid area square meter");
      }
      if(isNumberSmallerThanOne(descriptionDto.getMunicipalAssessment())){
         throw new InvalidDescriptionException("Invalid municipal assessment");
      }
      if(isNumberSmallerThanOne(descriptionDto.getNumberOfBedRooms())){
         throw new InvalidDescriptionException("Invalid number of bed rooms");
      }
      if(isNumberSmallerThanOne(descriptionDto.getNumberOfLevel())){
         throw new InvalidDescriptionException("Invalid number of levels");
      }
      if(isNumberSmallerThanOne(descriptionDto.getNumberOfRooms())){
         throw new InvalidDescriptionException("Invalid number of rooms");
      }
      if(isNumberSmallerThanOne(descriptionDto.getYearOfConstruction())){
         throw new InvalidDescriptionException("Invalid year of construction");
      }
   }
   
   private boolean isNumberSmallerThanOne(Integer integer){
         if (integer <= 0){
            return true;
         }
         return false;        
   }
}
