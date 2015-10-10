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
      descriptionDto.setBackyardFaces(descriptionModel.getBackyardFaces());

      return descriptionDto;
   }
   
   public DescriptionModel convertToModel(DescriptionDto descriptionDto) {
      DescriptionModel descriptionModel = new DescriptionModel();

      descriptionModel.setNumberOfBedRooms(descriptionDto.getNumberOfBedRooms());
      descriptionModel.setNumberOfBathrooms(descriptionDto.getNumberOfBathrooms());
      descriptionModel.setNumberOfRooms(descriptionDto.getNumberOfRooms());
      descriptionModel.setNumberOfLevel(descriptionDto.getNumberOfLevel());
      descriptionModel.setYearsOfConstruction(descriptionDto.getYearsOfConstruction());
      descriptionModel.setDimensionsBuilding(descriptionDto.getDimensionsBuilding());
      descriptionModel.setLivingSpaceAreaSquareMeter(descriptionDto.getLivingSpaceAreaSquareMeter());
      descriptionModel.setMunicipalValuation(descriptionDto.getMunicipalValuation());
      descriptionModel.setBackyardFaces(descriptionDto.getBackyardFaces());

      return descriptionModel;
   }
   
   public DescriptionDto createTestDescriptionDto(){
      return new DescriptionDto(1,2,3,4,1900,"20x20",200,20000,"north");
   }
   
}
