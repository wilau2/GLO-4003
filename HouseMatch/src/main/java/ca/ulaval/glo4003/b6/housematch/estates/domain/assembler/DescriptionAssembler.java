package ca.ulaval.glo4003.b6.housematch.estates.domain.assembler;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Description;
import ca.ulaval.glo4003.b6.housematch.estates.domain.Land;
import ca.ulaval.glo4003.b6.housematch.estates.domain.Room;
import ca.ulaval.glo4003.b6.housematch.estates.dto.DescriptionDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.LandDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.RoomDto;

public class DescriptionAssembler {
   
   private RoomAssembler roomAssembler;
   private LandAssembler landAssembler;
   
   public DescriptionAssembler(RoomAssembler roomAssembler, LandAssembler landAssembler){
      this.roomAssembler = roomAssembler;
      this.landAssembler = landAssembler;
   }
   
   public Description assembleDescription(DescriptionDto descriptionDto){
     
      Integer numberOfBedRooms = descriptionDto.getNumberOfBedRooms();
      Integer numberOfBathrooms = descriptionDto.getNumberOfBathrooms();
      Integer numberOfRooms = descriptionDto.getNumberOfRooms();
      Integer numberOfLevel = descriptionDto.getNumberOfLevel();
      Integer yearsOfConstruction = descriptionDto.getYearsOfConstruction();
      String dimensionsBuilding = descriptionDto.getDimensionsBuilding();
      Integer livingSpaceAreaSquareMeter = descriptionDto.getLivingSpaceAreaSquareMeter();
      Integer municipalValuation = descriptionDto.getMunicipalValuation();
      String backyardFaces = descriptionDto.getBackyardFaces();
      
      List<Room> rooms = roomAssembler.assembleRooms(descriptionDto.getRoomsDto());
      Land land = landAssembler.assembleLand(descriptionDto.getLandDto());
      
      Description description = new Description(numberOfBedRooms, numberOfBathrooms, numberOfRooms,
            numberOfLevel, yearsOfConstruction, dimensionsBuilding, livingSpaceAreaSquareMeter,
            municipalValuation, backyardFaces, rooms, land);
      
      return description;
   }
   
   public DescriptionDto assembleDescriptionDto(Description description, String estateID){
      
      Integer numberOfBedRooms = description.getNumberOfBedRooms();
      Integer numberOfBathrooms = description.getNumberOfBathrooms();
      Integer numberOfRooms = description.getNumberOfRooms();
      Integer numberOfLevel = description.getNumberOfLevel();
      Integer yearsOfConstruction = description.getYearsOfConstruction();
      String dimensionsBuilding = description.getDimensionsBuilding();
      Integer livingSpaceAreaSquareMeter = description.getLivingSpaceAreaSquareMeter();
      Integer municipalValuation = description.getMunicipalValuation();
      String backyardFaces = description.getBackyardFaces();
      List<RoomDto> roomDto = roomAssembler.assembleRoomDto(description.getRooms());
      LandDto landDto = landAssembler.assembleLandDto(description.getLand());
   
      DescriptionDto descriptionDto = new DescriptionDto(numberOfBedRooms, numberOfBathrooms, numberOfRooms,
            numberOfLevel, yearsOfConstruction, dimensionsBuilding, livingSpaceAreaSquareMeter,
            municipalValuation, backyardFaces, roomDto, landDto, estateID);
      
      return descriptionDto;
   }
}