package ca.ulaval.glo4003.b6.housematch.estates.domain.assembler;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Description;
import ca.ulaval.glo4003.b6.housematch.estates.dto.DescriptionDto;

public class DescriptionAssembler {
   
   public DescriptionAssembler(){
   }
   
   public Description assembleDescription(DescriptionDto descriptionDto){
      
      if(descriptionDto == null){    
         return new Description();
      }
     
      Integer numberOfBedRooms = descriptionDto.getNumberOfBedRooms();
      Integer numberOfBathrooms = descriptionDto.getNumberOfBathrooms();
      Integer numberOfRooms = descriptionDto.getNumberOfRooms();
      Integer numberOfLevel = descriptionDto.getNumberOfLevel();
      Integer yearsOfConstruction = descriptionDto.getYearsOfConstruction();
      String dimensionsBuilding = descriptionDto.getDimensionsBuilding();
      Integer livingSpaceAreaSquareMeter = descriptionDto.getLivingSpaceAreaSquareMeter();
      Integer municipalValuation = descriptionDto.getMunicipalValuation();
      String backyardFaces = descriptionDto.getBackyardFaces();
      
      Description description = new Description(numberOfBedRooms, numberOfBathrooms, numberOfRooms,
            numberOfLevel, yearsOfConstruction, dimensionsBuilding, livingSpaceAreaSquareMeter,
            municipalValuation, backyardFaces);
      
      return description;
   }
   
   public DescriptionDto assembleDescriptionDto(Description description){
      
      Integer numberOfBedRooms = description.getNumberOfBedRooms();
      Integer numberOfBathrooms = description.getNumberOfBathrooms();
      Integer numberOfRooms = description.getNumberOfRooms();
      Integer numberOfLevel = description.getNumberOfLevel();
      Integer yearsOfConstruction = description.getYearsOfConstruction();
      String dimensionsBuilding = description.getDimensionsBuilding();
      Integer livingSpaceAreaSquareMeter = description.getLivingSpaceAreaSquareMeter();
      Integer municipalValuation = description.getMunicipalValuation();
      String backyardFaces = description.getBackyardFaces();
   
      DescriptionDto descriptionDto = new DescriptionDto(numberOfBedRooms, numberOfBathrooms, numberOfRooms,
            numberOfLevel, yearsOfConstruction, dimensionsBuilding, livingSpaceAreaSquareMeter, municipalValuation, backyardFaces);
      
      return descriptionDto;
   }
}