package ca.ulaval.glo4003.b6.housematch.estates.domain.assembler;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Room;
import ca.ulaval.glo4003.b6.housematch.estates.dto.RoomDto;

public class RoomAssemblerTest {
   
   private static final String TYPE = "GARAGE";
   private static final Integer FLOOR = 2;
   private static final String DIMENSIONS = "20x20";
   private static final String SURFACE = "Hard wood";

   @Mock
   private Room room;
   
   private ArrayList<Room> rooms = new ArrayList<Room>();
   
   @InjectMocks
   private RoomAssembler roomAssembler;
   
   @Before
   public void setup(){
      MockitoAnnotations.initMocks(this);

      configureRoom();
   }

   private void configureRoom() {
      when(room.getType()).thenReturn(TYPE);
      when(room.getFloor()).thenReturn(FLOOR);
      when(room.getDimensions()).thenReturn(DIMENSIONS);
      when(room.getSurface()).thenReturn(SURFACE);
      
      rooms.add(room);
   }
   
   @Test
   public void whenAssemblingAnRoomDtoFromAnEstateShouldSetCorrectlyAllFieldOfDto() {
      // Given 
      
      // When
      List<RoomDto> returnedRoomsDto = roomAssembler.assembleRoomDto(rooms);
      RoomDto returnedRoomDto = returnedRoomsDto.get(0);

      // Then
      
      assertEquals(TYPE, returnedRoomDto.getType());
      assertEquals(FLOOR, returnedRoomDto.getFloor());
      assertEquals(DIMENSIONS, returnedRoomDto.getDimensions());
      assertEquals(SURFACE, returnedRoomDto.getSurface());
   }
}
