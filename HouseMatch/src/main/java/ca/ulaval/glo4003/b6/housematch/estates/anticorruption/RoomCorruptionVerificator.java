package ca.ulaval.glo4003.b6.housematch.estates.anticorruption;

import java.util.List;

import ca.ulaval.glo4003.b6.housematch.estates.anticorruption.exceptions.InvalidRoomFieldException;
import ca.ulaval.glo4003.b6.housematch.estates.dto.RoomDto;

public class RoomCorruptionVerificator {

   public void validateRoomCorruption(List<RoomDto> roomsDto) throws InvalidRoomFieldException {
      for (RoomDto roomDto : roomsDto){
         validateType(roomDto);
         validateFloor(roomDto);
         validateDimension(roomDto);
         validateSurface(roomDto);
      }  
   }

   private void validateType(RoomDto roomDto) throws InvalidRoomFieldException {
      if(roomDto.getType() == null || roomDto.getType() == ""){
         throw new InvalidRoomFieldException("The entered type is not valid");
      }
   }
   
   private void validateFloor(RoomDto roomDto) throws InvalidRoomFieldException {
      if(roomDto.getFloor() == null || roomDto.getFloor() < 0){
            throw new InvalidRoomFieldException("The entered floor is not valid");
      }
   } 
   
   private void validateDimension(RoomDto roomDto) throws InvalidRoomFieldException {
      if(roomDto.getDimensions() == null || roomDto.getDimensions() == ""){
            throw new InvalidRoomFieldException("The entered dimensions is not valid");
      }
   }
   
   private void validateSurface(RoomDto roomDto) throws InvalidRoomFieldException {
      if(roomDto.getSurface() == null || roomDto.getSurface() == ""){
            throw new InvalidRoomFieldException("The entered surface is not valid");
      }
   } 
  
}
