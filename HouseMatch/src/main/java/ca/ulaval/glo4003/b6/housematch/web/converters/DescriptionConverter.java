package ca.ulaval.glo4003.b6.housematch.web.converters;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4003.b6.housematch.estates.dto.DescriptionDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.LandDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.RoomDto;
import ca.ulaval.glo4003.b6.housematch.web.viewModel.DescriptionModel;

public class DescriptionConverter {

   public DescriptionDto convertToDto(DescriptionModel descriptionModel) {
      DescriptionDto descriptionDto = new DescriptionDto();

      LandDto landDto = extractLandInformation(descriptionModel);
      //List<RoomDto> roomsDto = extractRoomsInformation(descriptionModel);
            
      descriptionDto.setNumberOfBedRooms(descriptionModel.getNumberOfBedRooms());
      descriptionDto.setNumberOfBathrooms(descriptionModel.getNumberOfBathrooms());
      descriptionDto.setNumberOfRooms(descriptionModel.getNumberOfRooms());
      descriptionDto.setNumberOfLevel(descriptionModel.getNumberOfLevel());
      descriptionDto.setYearsOfConstruction(descriptionModel.getYearsOfConstruction());
      descriptionDto.setDimensionsBuilding(descriptionModel.getDimensionsBuilding());
      descriptionDto.setLivingSpaceAreaSquareMeter(descriptionModel.getLivingSpaceAreaSquareMeter());
      descriptionDto.setMunicipalValuation(descriptionModel.getMunicipalValuation());
      descriptionDto.setBackyardFaces(descriptionModel.getBackyardFaces());
      
      descriptionDto.setLandDto(landDto);
      //descriptionDto.setRoomsDto(roomsDto);

      return descriptionDto;
   }

   private LandDto extractLandInformation(DescriptionModel descriptionModel) {
      LandDto landDto = new LandDto();

      landDto.setAqueduct(descriptionModel.getAqueduct());
      landDto.setDimensionsLot(descriptionModel.getDimensionsLot());

      return landDto;
   }
   
   /*
   private List<RoomDto> extractRoomsInformation(DescriptionModel descriptionModel) {
      List<RoomDto> roomsDto = new ArrayList<RoomDto>();
      rooms = descriptionModel.getRooms();
 
         for (List<String> roomsList : ){
            String type = room.getType();
            Integer floor = room.getFloor();
            String dimensions = room.getDimensions();
            String surface = room.getSurface();
            roomsDto.add(new RoomDto(type,floor,dimensions,surface));
         }  
      return roomsDto;
   }
   */
   
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

      LandDto landDto = descriptionDto.getLandDto();
      descriptionModel.setAqueduct(landDto.getAqueduct());
      descriptionModel.setDimensionsLot(landDto.getDimensionsLot());
      
      List<RoomDto> roomsDto = descriptionDto.getRoomsDto();

      return descriptionModel;
   }
   
   public DescriptionDto createTestDescriptionDto(){
      return new DescriptionDto(1,2,3,4,1900,"20x20",200,20000,"north",new ArrayList<RoomDto>(),new LandDto("Shit Land", "1x1"),"estateID");
   }
   
}
