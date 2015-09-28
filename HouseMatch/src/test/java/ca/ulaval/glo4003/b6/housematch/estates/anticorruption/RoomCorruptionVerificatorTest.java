package ca.ulaval.glo4003.b6.housematch.estates.anticorruption;

import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.estates.anticorruption.exceptions.InvalidRoomFieldException;
import ca.ulaval.glo4003.b6.housematch.estates.dto.RoomDto;

public class RoomCorruptionVerificatorTest {
   
   private static final String EMPTY_FIELD = "";
   private static final Integer NEGATIVE_NUMBER = -1;
   
   private static final String TYPE = "GARAGE";
   private static final Integer FLOOR = 2;
   private static final String DIMENSIONS = "20x20";
   private static final String SURFACE = "Hard wood";
   
   private ArrayList<RoomDto> roomsDto = new ArrayList<RoomDto>();
   
   
   @Mock
   private RoomDto validRoomDto;
   
   @Mock
   private RoomDto invalidRoomDto;
   
   @InjectMocks
   private RoomCorruptionVerificator roomCorruptionVerificator;
   
   @Before
   public void setup() throws InvalidRoomFieldException{
      MockitoAnnotations.initMocks(this);

      configureValidLandModel();
   }

   private void configureValidLandModel() {
      when(validRoomDto.getType()).thenReturn(TYPE);
      when(validRoomDto.getFloor()).thenReturn(FLOOR);
      when(validRoomDto.getDimensions()).thenReturn(DIMENSIONS);
      when(validRoomDto.getSurface()).thenReturn(SURFACE);
      
      when(invalidRoomDto.getType()).thenReturn(EMPTY_FIELD);
      when(invalidRoomDto.getFloor()).thenReturn(NEGATIVE_NUMBER);
      when(invalidRoomDto.getDimensions()).thenReturn(EMPTY_FIELD);
      when(invalidRoomDto.getSurface()).thenReturn(EMPTY_FIELD);
      
      roomsDto.add(validRoomDto);
   }
   
   @Test
   public void verificatingRoomCorruptionWhenRoomHasNoCorruptionShouldNotThrowInvalidRoomFieldException() throws InvalidRoomFieldException{
      // Given
      
      // When
      roomCorruptionVerificator.validateRoomCorruption(roomsDto);
      
      // Then nothing
   }
   
   @Test(expected = InvalidRoomFieldException.class)
   public void verificatingRoomCorruptionWhenRoomHasNullTypeShouldThrowInvalidRoomFieldException() throws InvalidRoomFieldException {
      // Given
      when(validRoomDto.getType()).thenReturn(null);
      // When
      roomCorruptionVerificator.validateRoomCorruption(roomsDto);
      
      // Then expected InvalidRoomFieldException
   }
   
   @Test(expected = InvalidRoomFieldException.class)
   public void verificatingRoomCorruptionWhenRoomHasEmptyTypeShouldThrowInvalidRoomFieldException() throws InvalidRoomFieldException {
      // Given
      when(validRoomDto.getType()).thenReturn(EMPTY_FIELD);
      // When
      roomCorruptionVerificator.validateRoomCorruption(roomsDto);
      
      // Then expected InvalidRoomFieldException
   }
   
   @Test(expected = InvalidRoomFieldException.class)
   public void verificatingRoomCorruptionWhenRoomHasNullFloorShouldThrowInvalidRoomFieldException() throws InvalidRoomFieldException {
      // Given
      when(validRoomDto.getFloor()).thenReturn(null);
      // When
      roomCorruptionVerificator.validateRoomCorruption(roomsDto);
      
      // Then expected InvalidRoomFieldException
   }
   
   @Test(expected = InvalidRoomFieldException.class)
   public void verificatingRoomCorruptionWhenRoomHasNegativeFloorShouldThrowInvalidRoomFieldException() throws InvalidRoomFieldException {
      // Given
      when(validRoomDto.getFloor()).thenReturn(NEGATIVE_NUMBER);
      // When
      roomCorruptionVerificator.validateRoomCorruption(roomsDto);
      
      // Then expected InvalidRoomFieldException
   }
   
   @Test(expected = InvalidRoomFieldException.class)
   public void verificatingRoomCorruptionWhenRoomHasNullDimensionShouldThrowInvalidRoomFieldException() throws InvalidRoomFieldException {
      // Given
      when(validRoomDto.getDimensions()).thenReturn(null);
      // When
      roomCorruptionVerificator.validateRoomCorruption(roomsDto);
      
      // Then expected InvalidRoomFieldException
   }
   
   @Test(expected = InvalidRoomFieldException.class)
   public void verificatingRoomCorruptionWhenRoomHasEmptyDimensionShouldThrowInvalidRoomFieldException() throws InvalidRoomFieldException {
      // Given
      when(validRoomDto.getDimensions()).thenReturn(EMPTY_FIELD);
      // When
      roomCorruptionVerificator.validateRoomCorruption(roomsDto);
      
      // Then expected InvalidRoomFieldException
   }
   
   @Test(expected = InvalidRoomFieldException.class)
   public void verificatingRoomCorruptionWhenRoomHasNullSurfaceShouldThrowInvalidRoomFieldException() throws InvalidRoomFieldException {
      // Given
      when(validRoomDto.getSurface()).thenReturn(null);
      // When
      roomCorruptionVerificator.validateRoomCorruption(roomsDto);
      
      // Then expected InvalidRoomFieldException
   }
   
   @Test(expected = InvalidRoomFieldException.class)
   public void verificatingRoomCorruptionWhenRoomHasEmptySurfaceShouldThrowInvalidRoomFieldException() throws InvalidRoomFieldException {
      // Given
      when(validRoomDto.getSurface()).thenReturn(EMPTY_FIELD);
      // When
      roomCorruptionVerificator.validateRoomCorruption(roomsDto);
      
      // Then expected InvalidRoomFieldException
   }
   
   @Test
   public void verificatingRoomCorruptionWhenMultipleRoomHasNoCorruptionShouldNotThrowInvalidRoomFieldException() throws InvalidRoomFieldException {
      // Given
      roomsDto.add(validRoomDto);
      // When
      roomCorruptionVerificator.validateRoomCorruption(roomsDto);
      
      // Then nothing
   }
   
   @Test(expected = InvalidRoomFieldException.class)
   public void verificatingRoomCorruptionWhenMultipleRoomHasCorruptionShouldThrowInvalidRoomFieldException() throws InvalidRoomFieldException {
      // Given
      roomsDto.add(invalidRoomDto);
      // When
      roomCorruptionVerificator.validateRoomCorruption(roomsDto);
      
      // Then expected InvalidRoomFieldException
   }
}
