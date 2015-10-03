package ca.ulaval.glo4003.b6.housematch.estates.anticorruption;

import org.springframework.beans.factory.annotation.Autowired;

import ca.ulaval.glo4003.b6.housematch.estates.anticorruption.exceptions.InvalidDescriptionFieldException;
import ca.ulaval.glo4003.b6.housematch.estates.anticorruption.exceptions.InvalidLandFieldException;
import ca.ulaval.glo4003.b6.housematch.estates.anticorruption.exceptions.InvalidRoomFieldException;
import ca.ulaval.glo4003.b6.housematch.estates.dto.DescriptionDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.InvalidDescriptionException;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.InvalidEstateException;
import ca.ulaval.glo4003.b6.housematch.estates.services.EstatesService;

public class DescriptionCorruptionVerificator {
   
   private RoomCorruptionVerificator roomCorruptionVerificator;
   private LandCorruptionVerificator landCorruptionVerificator;
   private EstatesService estateService;
   
   @Autowired
   public DescriptionCorruptionVerificator(EstatesService estateService, 
         RoomCorruptionVerificator roomCorruptionVerificator, LandCorruptionVerificator landCorruptionVerificator ){
      
      this.roomCorruptionVerificator = roomCorruptionVerificator;
      this.landCorruptionVerificator = landCorruptionVerificator;
      this.estateService = estateService;
   }
   
   public void editEstate(EstateDto estateDto) throws InvalidDescriptionFieldException, InvalidRoomFieldException, InvalidLandFieldException, InvalidDescriptionException, InvalidEstateException{
      validateDescriptionCorruption(estateDto.getDescriptionDto());
      estateService.editEstate(estateDto);
      
   }

   private void validateDescriptionCorruption(DescriptionDto descriptionDto) throws InvalidDescriptionFieldException, InvalidRoomFieldException, InvalidLandFieldException {
      validateNumberOfBedrooms(descriptionDto);      
      validateNumberOfBathrooms(descriptionDto);      
      validateNumberOfRooms(descriptionDto);      
      validateNumberOfLevel(descriptionDto);     
      validateYearsOfContruction(descriptionDto);      
      validateDimensionsBuilding(descriptionDto);      
      validateLivingSpaceArea(descriptionDto);      
      validateMunicipalValuation(descriptionDto);      
      validateBackyardFaces(descriptionDto);
      
      roomCorruptionVerificator.validateRoomCorruption(descriptionDto.getRoomsDto());
      landCorruptionVerificator.validateLandCorruption(descriptionDto.getLandDto());
      
   }

   private void validateBackyardFaces(DescriptionDto descriptionDto) throws InvalidDescriptionFieldException {
      if(descriptionDto.getBackyardFaces() == null || descriptionDto.getBackyardFaces() == ""){
         throw new InvalidDescriptionFieldException("The entered backyard faces is not valid");
      }
   }

   private void validateMunicipalValuation(DescriptionDto descriptionDto) throws InvalidDescriptionFieldException {
      if(descriptionDto.getMunicipalValuation() == null || descriptionDto.getMunicipalValuation() < 0){
         throw new InvalidDescriptionFieldException("The entered municipal valuation is not valid");
      }
   }

   private void validateLivingSpaceArea(DescriptionDto descriptionDto) throws InvalidDescriptionFieldException {
      if(descriptionDto.getLivingSpaceAreaSquareMeter() == null || descriptionDto.getLivingSpaceAreaSquareMeter() < 0){
         throw new InvalidDescriptionFieldException("The entered living space area is not valid");
      }
   }

   private void validateDimensionsBuilding(DescriptionDto descriptionDto) throws InvalidDescriptionFieldException {
      if(descriptionDto.getDimensionsBuilding() == null || descriptionDto.getDimensionsBuilding() == ""){
         throw new InvalidDescriptionFieldException("The entered building dimension is not valid");
      }
   }

   private void validateYearsOfContruction(DescriptionDto descriptionDto) throws InvalidDescriptionFieldException {
      if(descriptionDto.getYearsOfConstruction() == null || descriptionDto.getYearsOfConstruction() < 0){
         throw new InvalidDescriptionFieldException("The entered years of construction is not valid");
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
