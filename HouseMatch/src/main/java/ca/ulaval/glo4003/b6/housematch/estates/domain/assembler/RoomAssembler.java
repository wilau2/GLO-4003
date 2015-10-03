package ca.ulaval.glo4003.b6.housematch.estates.domain.assembler;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Room;
import ca.ulaval.glo4003.b6.housematch.estates.dto.RoomDto;

public class RoomAssembler {

   public List<Room> assembleRooms(List<RoomDto> roomsDto){
      List<Room> rooms = new ArrayList<Room>();
      
      for (RoomDto roomDto : roomsDto){
         String type = roomDto.getType();
         Integer floor = roomDto.getFloor();
         String dimensions = roomDto.getDimensions();
         String surface = roomDto.getSurface();
         rooms.add(new Room(type,floor,dimensions,surface));
      }
      
      return rooms;
   }

   public List<RoomDto> assembleRoomDto(List<Room> rooms){
      
      
      List<RoomDto> roomsDto = new ArrayList<RoomDto>();
      
      if ( rooms == null){
         return roomsDto;
      }
      
      for (Room room : rooms){
         String type = room.getType();
         Integer floor = room.getFloor();
         String dimensions = room.getDimensions();
         String surface = room.getSurface();
         roomsDto.add(new RoomDto(type,floor,dimensions,surface));
      }  
      return roomsDto;
   }
}
