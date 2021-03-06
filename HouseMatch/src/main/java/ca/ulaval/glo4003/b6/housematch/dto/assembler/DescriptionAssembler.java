package ca.ulaval.glo4003.b6.housematch.dto.assembler;

import ca.ulaval.glo4003.b6.housematch.domain.estate.Description;
import ca.ulaval.glo4003.b6.housematch.domain.estate.builder.DescriptionBuilder;
import ca.ulaval.glo4003.b6.housematch.dto.DescriptionDto;

public class DescriptionAssembler {

   public Description assembleDescription(DescriptionDto descriptionDto) {

      if (descriptionDto == null) {

         return new Description();
      }

      DescriptionBuilder descriptionBuilder = new DescriptionBuilder();

      Integer numberOfBedRooms = descriptionDto.getNumberOfBedRooms();
      Integer numberOfBathrooms = descriptionDto.getNumberOfBathrooms();
      Integer numberOfRooms = descriptionDto.getNumberOfRooms();
      Integer numberOfLevel = descriptionDto.getNumberOfLevel();
      Integer yearsOfConstruction = descriptionDto.getYearOfConstruction();
      String dimensionsBuilding = descriptionDto.getBuildingDimension();
      Integer livingSpaceAreaSquareMeter = descriptionDto.getLivingSpaceAreaSquareMeter();
      Integer municipalValuation = descriptionDto.getMunicipalAssessment();
      String backyardOrientation = descriptionDto.getBackyardOrientation();

      return descriptionBuilder.setNewBackyardOrientation(backyardOrientation)
            .setNewDimensionsBuilding(dimensionsBuilding).setNewLivingSpaceAreaSquareMeter(livingSpaceAreaSquareMeter)
            .setNewMunicipalValuation(municipalValuation).setNewNumberOfBathrooms(numberOfBathrooms)
            .setNewNumberOfBedRooms(numberOfBedRooms).setNewNumberOfLevel(numberOfLevel)
            .setNewNumberOfRooms(numberOfRooms).setNewYearsOfConstruction(yearsOfConstruction).buildDescription();
   }

   public DescriptionDto assembleDescriptionDto(Description description) {

      Integer numberOfBedRooms = description.getNumberOfBedRooms();
      Integer numberOfBathrooms = description.getNumberOfBathrooms();
      Integer numberOfRooms = description.getNumberOfRooms();
      Integer numberOfLevel = description.getNumberOfLevel();
      Integer yearsOfConstruction = description.getYearOfConstruction();
      String dimensionsBuilding = description.getBuildingDimensions();
      Integer livingSpaceAreaSquareMeter = description.getLivingSpaceAreaSquareMeter();
      Integer municipalValuation = description.getMunicipalAssessment();
      String backyardOrientation = description.getBackyardOrientation();

      DescriptionDto descriptionDto = new DescriptionDto(numberOfBedRooms, numberOfBathrooms, numberOfRooms,
            numberOfLevel, yearsOfConstruction, dimensionsBuilding, livingSpaceAreaSquareMeter, municipalValuation,
            backyardOrientation);

      return descriptionDto;
   }
}
