package ca.ulaval.glo4003.b6.housematch.web.converters;

import ca.ulaval.glo4003.b6.housematch.estates.dto.DescriptionDto;
import ca.ulaval.glo4003.b6.housematch.web.viewModel.DescriptionModel;

public class DescriptionConverter {

   public DescriptionDto convertToDto(DescriptionModel descriptionModel) {
      DescriptionDto descriptionDto = new DescriptionDto();

      descriptionDto.setNumberOfBedRooms(descriptionModel.getNumberOfBedRooms());
      descriptionDto.setNumberOfBathrooms(descriptionModel.getNumberOfBathrooms());
      descriptionDto.setNumberOfRooms(descriptionModel.getNumberOfRooms());
      descriptionDto.setNumberOfLevel(descriptionModel.getNumberOfLevel());
      descriptionDto.setYearsOfConstruction(descriptionModel.getYearsOfConstruction());
      descriptionDto.setDimensionsBuilding(descriptionModel.getDimensionsBuilding());
      descriptionDto.setLivingSpaceAreaSquareMeter(descriptionModel.getLivingSpaceAreaSquareMeter());
      descriptionDto.setMunicipalValuation(descriptionModel.getMunicipalValuation());
      descriptionDto.setBackyardOrientation(descriptionModel.getBackyardFaces());

      return descriptionDto;
   }
   
   public DescriptionModel convertToModel(DescriptionDto descriptionDto) {
      DescriptionModel descriptionModel = new DescriptionModel();

      descriptionModel.setNumberOfBedRooms(descriptionDto.getNumberOfBedRooms());
      descriptionModel.setNumberOfBathrooms(descriptionDto.getNumberOfBathrooms());
      descriptionModel.setNumberOfRooms(descriptionDto.getNumberOfRooms());
      descriptionModel.setNumberOfLevel(descriptionDto.getNumberOfLevel());
      descriptionModel.setYearsOfConstruction(descriptionDto.getYearOfConstruction());
      descriptionModel.setDimensionsBuilding(descriptionDto.getBuildingDimensions());
      descriptionModel.setLivingSpaceAreaSquareMeter(descriptionDto.getLivingSpaceAreaSquareMeter());
      descriptionModel.setMunicipalValuation(descriptionDto.getMunicipalAssessment());
      descriptionModel.setBackyardFaces(descriptionDto.getBackyardOrientation());

      return descriptionModel;
   }
}
