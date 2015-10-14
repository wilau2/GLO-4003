package ca.ulaval.glo4003.b6.housematch.estates.anticorruption;

import org.springframework.beans.factory.annotation.Autowired;

import ca.ulaval.glo4003.b6.housematch.estates.anticorruption.exceptions.InvalidDescriptionFieldException;
import ca.ulaval.glo4003.b6.housematch.estates.dto.DescriptionDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.InvalidDescriptionException;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.InvalidEstateException;
import ca.ulaval.glo4003.b6.housematch.estates.services.EstatesService;
import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;

public class DescriptionCorruptionVerificator {
 
   private EstatesService estateService;
   
   @Autowired
   public DescriptionCorruptionVerificator(EstatesService estateService){    
      this.estateService = estateService;
   }
   
   public void editDescription(String address, DescriptionDto descriptionDto) throws InvalidDescriptionFieldException, InvalidDescriptionException, 
   InvalidEstateException, CouldNotAccessDataException {
      validateDescriptionCorruption(descriptionDto);
      estateService.editDescription(address, descriptionDto);  
   }

   private void validateDescriptionCorruption(DescriptionDto descriptionDto) throws InvalidDescriptionFieldException {
      validateNumberOfBedrooms(descriptionDto);      
      validateNumberOfBathrooms(descriptionDto);      
      validateNumberOfRooms(descriptionDto);      
      validateNumberOfLevel(descriptionDto);     
      validateYearOfContruction(descriptionDto);      
      validateBuildingDimensions(descriptionDto);      
      validateLivingSpaceArea(descriptionDto);      
      validateMunicipalAssessment(descriptionDto);      
      validateBackyardFaces(descriptionDto);
      
   }

   private void validateBackyardFaces(DescriptionDto descriptionDto) throws InvalidDescriptionFieldException {
      if(descriptionDto.getBackyardFaces() == null){
         throw new InvalidDescriptionFieldException("The entered backyard faces is not valid");
      }
   }

   private void validateMunicipalAssessment(DescriptionDto descriptionDto) throws InvalidDescriptionFieldException {
      if(descriptionDto.getMunicipalAssessment() == null || descriptionDto.getMunicipalAssessment() < 0){
         throw new InvalidDescriptionFieldException("The entered municipal valuation is not valid");
      }
   }

   private void validateLivingSpaceArea(DescriptionDto descriptionDto) throws InvalidDescriptionFieldException {
      if(descriptionDto.getLivingSpaceAreaSquareMeter() == null || descriptionDto.getLivingSpaceAreaSquareMeter() < 0){
         throw new InvalidDescriptionFieldException("The entered living space area is not valid");
      }
   }

   private void validateBuildingDimensions(DescriptionDto descriptionDto) throws InvalidDescriptionFieldException {
      if(descriptionDto.getBuildingDimensions() == null){
         throw new InvalidDescriptionFieldException("The entered building dimension is not valid");
      }
   }

   private void validateYearOfContruction(DescriptionDto descriptionDto) throws InvalidDescriptionFieldException {
      if(descriptionDto.getYearOfConstruction() == null || descriptionDto.getYearOfConstruction() < 0){
         throw new InvalidDescriptionFieldException("The entered year of construction is not valid");
      }
   }

   private void validateNumberOfLevel(DescriptionDto descriptionDto) throws InvalidDescriptionFieldException {
      if(descriptionDto.getNumberOfLevel() == null || descriptionDto.getNumberOfLevel() < 0){
         throw new InvalidDescriptionFieldException("The entered number of level is not valid");
      }
   }

   private void validateNumberOfRooms(DescriptionDto descriptionDto) throws InvalidDescriptionFieldException {
      if(descriptionDto.getNumberOfRooms() == null || descriptionDto.getNumberOfRooms() < 0){
         throw new InvalidDescriptionFieldException("The entered number of rooms is not valid");
      }
   }

   private void validateNumberOfBathrooms(DescriptionDto descriptionDto) throws InvalidDescriptionFieldException {
      if(descriptionDto.getNumberOfBathrooms() == null || descriptionDto.getNumberOfBathrooms() < 0){
         throw new InvalidDescriptionFieldException("The entered number of bathrooms is not valid");
      }
   }

   private void validateNumberOfBedrooms(DescriptionDto descriptionDto) throws InvalidDescriptionFieldException {
      if(descriptionDto.getNumberOfBedRooms() == null || descriptionDto.getNumberOfBedRooms() < 0){
         throw new InvalidDescriptionFieldException("The entered number of bedrooms is not valid");
      }
   }

}
