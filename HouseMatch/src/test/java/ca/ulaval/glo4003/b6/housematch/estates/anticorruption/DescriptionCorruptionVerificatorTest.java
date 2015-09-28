package ca.ulaval.glo4003.b6.housematch.estates.anticorruption;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.estates.anticorruption.exceptions.InvalidDescriptionFieldException;
import ca.ulaval.glo4003.b6.housematch.estates.anticorruption.exceptions.InvalidLandFieldException;
import ca.ulaval.glo4003.b6.housematch.estates.anticorruption.exceptions.InvalidRoomFieldException;
import ca.ulaval.glo4003.b6.housematch.estates.dto.DescriptionDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.LandDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.RoomDto;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.InvalidDescriptionException;
import ca.ulaval.glo4003.b6.housematch.estates.services.EstatesService;

public class DescriptionCorruptionVerificatorTest {
   
   private static final String EMPTY_FIELD = "";
   private static final Integer NEGATIVE_NUMBER = -1;  
   
   private static final Integer NUMBER_OF_BEDROOMS = 1;
   private static final Integer NUMBER_OF_BATHROOMS = 2;
   private static final Integer NUMBER_OF_ROOMS = 3;
   private static final Integer NUMBER_OF_LEVEL = 2;
   private static final Integer YEAR_OF_CONSTRUCTION = 1990;   
   private static final String DIMENSION_OF_BUILDING = "20x20";
   private static final Integer LIVING_SPACE_AREA = 400;
   private static final Integer MUNICIPAL_VALUATION = 200000;
   private static final String BACKYARD_FACES = "North"; 
   
   @Mock
   private DescriptionDto descriptionDto;
   
   @Mock
   private List<RoomDto> roomsDto;
   
   @Mock
   private LandDto landDto;

   @Mock
   private EstatesService estateService;
   
   @Mock
   private RoomCorruptionVerificator roomCorruptionVerificator;
   
   @Mock
   private LandCorruptionVerificator landCorruptionVerificator;

   @InjectMocks
   private DescriptionCorruptionVerificator descriptionCorruptionVerificator;
   
   @Before
   public void setup() throws InvalidRoomFieldException, InvalidLandFieldException {
      MockitoAnnotations.initMocks(this);

      configureValidDescriptionModel();
   }

   private void configureValidDescriptionModel() throws InvalidRoomFieldException, InvalidLandFieldException {
      when(descriptionDto.getNumberOfBedRooms()).thenReturn(NUMBER_OF_BEDROOMS);
      when(descriptionDto.getNumberOfBathrooms()).thenReturn(NUMBER_OF_BATHROOMS);
      when(descriptionDto.getNumberOfRooms()).thenReturn(NUMBER_OF_ROOMS);
      when(descriptionDto.getNumberOfLevel()).thenReturn(NUMBER_OF_LEVEL);    
      when(descriptionDto.getYearsOfConstruction()).thenReturn(YEAR_OF_CONSTRUCTION);
      when(descriptionDto.getDimensionsBuilding()).thenReturn(DIMENSION_OF_BUILDING);
      when(descriptionDto.getLivingSpaceAreaSquareMeter()).thenReturn(LIVING_SPACE_AREA);
      when(descriptionDto.getMunicipalValuation()).thenReturn(MUNICIPAL_VALUATION);
      when(descriptionDto.getBackyardFaces()).thenReturn(BACKYARD_FACES);
      when(descriptionDto.getRoomsDto()).thenReturn(roomsDto);
      when(descriptionDto.getLandDto()).thenReturn(landDto);
      
      doNothing().when(roomCorruptionVerificator).validateRoomCorruption(roomsDto);
      doNothing().when(landCorruptionVerificator).validateLandCorruption(landDto);
      
   }
   
   @Test
   public void verificatingDescriptionCorruptionWhenDescriptionHasNoCorruptionSouldCallAddDescription() 
         throws InvalidDescriptionFieldException, InvalidRoomFieldException, InvalidLandFieldException, InvalidDescriptionException {
      // Given

      // When
      descriptionCorruptionVerificator.addDescription(descriptionDto);
      
      // Then
      verify(estateService, times(1)).addDescription(descriptionDto);
   }
   
   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingDescriptionCorruptionWhenDescriptionHasNullNumberOfBedroomsShouldThrowInvalidDescriptionFieldException() 
         throws InvalidDescriptionFieldException, InvalidRoomFieldException, InvalidLandFieldException, InvalidDescriptionException {
      // Given
      when(descriptionDto.getNumberOfBedRooms()).thenReturn(null);
      // When
      descriptionCorruptionVerificator.addDescription(descriptionDto);
      
      // Then expected InvalidDescriptionFieldException
   }
   
   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingDescriptionCorruptionWhenDescriptionHasNegativeNumberOfBedroomsShouldThrowInvalidDescriptionFieldException() 
         throws InvalidDescriptionFieldException, InvalidRoomFieldException, InvalidLandFieldException, InvalidDescriptionException {
      // Given
      when(descriptionDto.getNumberOfBedRooms()).thenReturn(NEGATIVE_NUMBER);
      // When
      descriptionCorruptionVerificator.addDescription(descriptionDto);
      
      // Then expected InvalidDescriptionFieldException
   }
   
   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingDescriptionCorruptionWhenDescriptionHasNullNumberOfBathroomsShouldThrowInvalidDescriptionFieldException() 
         throws InvalidDescriptionFieldException, InvalidRoomFieldException, InvalidLandFieldException, InvalidDescriptionException {
      // Given
      when(descriptionDto.getNumberOfBathrooms()).thenReturn(null);
      // When
      descriptionCorruptionVerificator.addDescription(descriptionDto);
      
      // Then expected InvalidDescriptionFieldException
   }
   
   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingDescriptionCorruptionWhenDescriptionHasNegativeNumberOfBathroomsShouldThrowInvalidDescriptionFieldException() 
         throws InvalidDescriptionFieldException, InvalidRoomFieldException, InvalidLandFieldException, InvalidDescriptionException {
      // Given
      when(descriptionDto.getNumberOfBathrooms()).thenReturn(NEGATIVE_NUMBER);
      // When
      descriptionCorruptionVerificator.addDescription(descriptionDto);
      
      // Then expected InvalidDescriptionFieldException
   }
   
   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingDescriptionCorruptionWhenDescriptionHasNullNumberOfRoomsShouldThrowInvalidDescriptionFieldException() 
         throws InvalidDescriptionFieldException, InvalidRoomFieldException, InvalidLandFieldException, InvalidDescriptionException {
      // Given
      when(descriptionDto.getNumberOfRooms()).thenReturn(null);
      // When
      descriptionCorruptionVerificator.addDescription(descriptionDto);
      
      // Then expected InvalidDescriptionFieldException
   }
   
   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingDescriptionCorruptionWhenDescriptionHasNegativeNumberOfRoomsShouldThrowInvalidDescriptionFieldException() 
         throws InvalidDescriptionFieldException, InvalidRoomFieldException, InvalidLandFieldException, InvalidDescriptionException {
      // Given
      when(descriptionDto.getNumberOfRooms()).thenReturn(NEGATIVE_NUMBER);
      // When
      descriptionCorruptionVerificator.addDescription(descriptionDto);
      
      // Then expected InvalidDescriptionFieldException
   }
   
   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingDescriptionCorruptionWhenDescriptionHasNullNumberOfLevelShouldThrowInvalidDescriptionFieldException() 
         throws InvalidDescriptionFieldException, InvalidRoomFieldException, InvalidLandFieldException, InvalidDescriptionException {
      // Given
      when(descriptionDto.getNumberOfLevel()).thenReturn(null);
      // When
      descriptionCorruptionVerificator.addDescription(descriptionDto);
      
      // Then expected InvalidDescriptionFieldException
   }
   
   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingDescriptionCorruptionWhenDescriptionHasNegativeNumberOfLevelShouldThrowInvalidDescriptionFieldException() 
         throws InvalidDescriptionFieldException, InvalidRoomFieldException, InvalidLandFieldException, InvalidDescriptionException {
      // Given
      when(descriptionDto.getNumberOfLevel()).thenReturn(NEGATIVE_NUMBER);
      // When
      descriptionCorruptionVerificator.addDescription(descriptionDto);
      
      // Then expected InvalidDescriptionFieldException
   }
   
   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingDescriptionCorruptionWhenDescriptionHasNullYearsOfConstructionShouldThrowInvalidDescriptionFieldException() 
         throws InvalidDescriptionFieldException, InvalidRoomFieldException, InvalidLandFieldException, InvalidDescriptionException {
      // Given
      when(descriptionDto.getYearsOfConstruction()).thenReturn(null);
      // When
      descriptionCorruptionVerificator.addDescription(descriptionDto);
      
      // Then expected InvalidDescriptionFieldException
   }
   
   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingDescriptionCorruptionWhenDescriptionHasNegativeYearsOfConstructionShouldThrowInvalidDescriptionFieldException() 
         throws InvalidDescriptionFieldException, InvalidRoomFieldException, InvalidLandFieldException, InvalidDescriptionException {
      // Given
      when(descriptionDto.getYearsOfConstruction()).thenReturn(NEGATIVE_NUMBER);
      // When
      descriptionCorruptionVerificator.addDescription(descriptionDto);
      
      // Then expected InvalidDescriptionFieldException
   }
   
   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingDescriptionCorruptionWhenDescriptionHasNullDimensionBuildingShouldThrowInvalidDescriptionFieldException() 
         throws InvalidDescriptionFieldException, InvalidRoomFieldException, InvalidLandFieldException, InvalidDescriptionException {
      // Given
      when(descriptionDto.getDimensionsBuilding()).thenReturn(null);
      // When
      descriptionCorruptionVerificator.addDescription(descriptionDto);
      
      // Then expected InvalidDescriptionFieldException
   }
   
   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingDescriptionCorruptionWhenDescriptionHasEmptyDimensionBuildingShouldThrowInvalidDescriptionFieldException() 
         throws InvalidDescriptionFieldException, InvalidRoomFieldException, InvalidLandFieldException, InvalidDescriptionException {
      // Given
      when(descriptionDto.getDimensionsBuilding()).thenReturn(EMPTY_FIELD);
      // When
      descriptionCorruptionVerificator.addDescription(descriptionDto);
      
      // Then expected InvalidDescriptionFieldException
   }
   
   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingDescriptionCorruptionWhenDescriptionHasNullLivingSpaceAreaShouldThrowInvalidDescriptionFieldException() 
         throws InvalidDescriptionFieldException, InvalidRoomFieldException, InvalidLandFieldException, InvalidDescriptionException {
      // Given
      when(descriptionDto.getLivingSpaceAreaSquareMeter()).thenReturn(null);
      // When
      descriptionCorruptionVerificator.addDescription(descriptionDto);
      
      // Then expected InvalidDescriptionFieldException
   }
   
   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingDescriptionCorruptionWhenDescriptionHasNegativeLivingSpaceAreaShouldThrowInvalidDescriptionFieldException() 
         throws InvalidDescriptionFieldException, InvalidRoomFieldException, InvalidLandFieldException, InvalidDescriptionException {
      // Given
      when(descriptionDto.getLivingSpaceAreaSquareMeter()).thenReturn(NEGATIVE_NUMBER);
      // When
      descriptionCorruptionVerificator.addDescription(descriptionDto);
      
      // Then expected InvalidDescriptionFieldException
   }
   
   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingDescriptionCorruptionWhenDescriptionHasNullMunicipalValuationShouldThrowInvalidDescriptionFieldException() 
         throws InvalidDescriptionFieldException, InvalidRoomFieldException, InvalidLandFieldException, InvalidDescriptionException {
      // Given
      when(descriptionDto.getMunicipalValuation()).thenReturn(null);
      // When
      descriptionCorruptionVerificator.addDescription(descriptionDto);
      
      // Then expected InvalidDescriptionFieldException
   }
   
   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingDescriptionCorruptionWhenDescriptionHasNegativeMunicipalValuationShouldThrowInvalidDescriptionFieldException() 
         throws InvalidDescriptionFieldException, InvalidRoomFieldException, InvalidLandFieldException, InvalidDescriptionException {
      // Given
      when(descriptionDto.getMunicipalValuation()).thenReturn(NEGATIVE_NUMBER);
      // When
      descriptionCorruptionVerificator.addDescription(descriptionDto);
      
      // Then expected InvalidDescriptionFieldException
   }
   
   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingDescriptionCorruptionWhenDescriptionHasNullBackyardFacesShouldThrowInvalidDescriptionFieldException() 
         throws InvalidDescriptionFieldException, InvalidRoomFieldException, InvalidLandFieldException, InvalidDescriptionException {
      // Given
      when(descriptionDto.getBackyardFaces()).thenReturn(null);
      // When
      descriptionCorruptionVerificator.addDescription(descriptionDto);
      
      // Then expected InvalidDescriptionFieldException
   }
   
   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingDescriptionCorruptionWhenDescriptionHasEmptyBackyardFacesShouldThrowInvalidDescriptionFieldException() 
         throws InvalidDescriptionFieldException, InvalidRoomFieldException, InvalidLandFieldException, InvalidDescriptionException {
      // Given
      when(descriptionDto.getBackyardFaces()).thenReturn(EMPTY_FIELD);
      // When
      descriptionCorruptionVerificator.addDescription(descriptionDto);
      
      // Then expected InvalidDescriptionFieldException
   }
   

}
