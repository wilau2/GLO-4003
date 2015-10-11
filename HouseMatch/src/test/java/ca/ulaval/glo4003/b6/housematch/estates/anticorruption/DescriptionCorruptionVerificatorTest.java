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
import ca.ulaval.glo4003.b6.housematch.estates.dto.DescriptionDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.InvalidDescriptionException;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.InvalidEstateException;
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
   private EstateDto estateDto;

   @Mock
   private EstatesService estateService;


   @InjectMocks
   private DescriptionCorruptionVerificator descriptionCorruptionVerificator;
   
   @Before
   public void setup(){
      MockitoAnnotations.initMocks(this);

      configureValidDescriptionModel();
   }

   private void configureValidDescriptionModel() {
      when(estateDto.getDescriptionDto()).thenReturn(descriptionDto);
      when(descriptionDto.getNumberOfBedRooms()).thenReturn(NUMBER_OF_BEDROOMS);
      when(descriptionDto.getNumberOfBathrooms()).thenReturn(NUMBER_OF_BATHROOMS);
      when(descriptionDto.getNumberOfRooms()).thenReturn(NUMBER_OF_ROOMS);
      when(descriptionDto.getNumberOfLevel()).thenReturn(NUMBER_OF_LEVEL);    
      when(descriptionDto.getYearsOfConstruction()).thenReturn(YEAR_OF_CONSTRUCTION);
      when(descriptionDto.getDimensionsBuilding()).thenReturn(DIMENSION_OF_BUILDING);
      when(descriptionDto.getLivingSpaceAreaSquareMeter()).thenReturn(LIVING_SPACE_AREA);
      when(descriptionDto.getMunicipalValuation()).thenReturn(MUNICIPAL_VALUATION);
      when(descriptionDto.getBackyardFaces()).thenReturn(BACKYARD_FACES);
      
   }
   
   @Test
   public void verificatingDescriptionCorruptionWhenDescriptionHasNoCorruptionSouldCallAddDescription() 
         throws InvalidDescriptionFieldException, InvalidDescriptionException, InvalidEstateException {
      // Given

      // When
      descriptionCorruptionVerificator.editEstate(estateDto);
      
      // Then
      verify(estateService, times(1)).editEstate(estateDto);
   }
   
   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingDescriptionCorruptionWhenDescriptionHasNullNumberOfBedroomsShouldThrowInvalidDescriptionFieldException() 
         throws InvalidDescriptionFieldException, InvalidDescriptionException, InvalidEstateException {
      // Given
      when(descriptionDto.getNumberOfBedRooms()).thenReturn(null);
      // When
      descriptionCorruptionVerificator.editEstate(estateDto);
      
      // Then expected InvalidDescriptionFieldException
   }
   
   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingDescriptionCorruptionWhenDescriptionHasNegativeNumberOfBedroomsShouldThrowInvalidDescriptionFieldException() 
         throws InvalidDescriptionFieldException, InvalidDescriptionException, InvalidEstateException {
      // Given
      when(descriptionDto.getNumberOfBedRooms()).thenReturn(NEGATIVE_NUMBER);
      // When
      descriptionCorruptionVerificator.editEstate(estateDto);
      
      // Then expected InvalidDescriptionFieldException
   }
   
   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingDescriptionCorruptionWhenDescriptionHasNullNumberOfBathroomsShouldThrowInvalidDescriptionFieldException() 
         throws InvalidDescriptionFieldException, InvalidDescriptionException, InvalidEstateException {
      // Given
      when(descriptionDto.getNumberOfBathrooms()).thenReturn(null);
      // When
      descriptionCorruptionVerificator.editEstate(estateDto);
      
      // Then expected InvalidDescriptionFieldException
   }
   
   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingDescriptionCorruptionWhenDescriptionHasNegativeNumberOfBathroomsShouldThrowInvalidDescriptionFieldException() 
         throws InvalidDescriptionFieldException, InvalidDescriptionException, InvalidEstateException {
      // Given
      when(descriptionDto.getNumberOfBathrooms()).thenReturn(NEGATIVE_NUMBER);
      // When
      descriptionCorruptionVerificator.editEstate(estateDto);
      
      // Then expected InvalidDescriptionFieldException
   }
   
   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingDescriptionCorruptionWhenDescriptionHasNullNumberOfRoomsShouldThrowInvalidDescriptionFieldException() 
         throws InvalidDescriptionFieldException, InvalidDescriptionException, InvalidEstateException {
      // Given
      when(descriptionDto.getNumberOfRooms()).thenReturn(null);
      // When
      descriptionCorruptionVerificator.editEstate(estateDto);
      
      // Then expected InvalidDescriptionFieldException
   }
   
   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingDescriptionCorruptionWhenDescriptionHasNegativeNumberOfRoomsShouldThrowInvalidDescriptionFieldException() 
         throws InvalidDescriptionFieldException, InvalidDescriptionException, InvalidEstateException {
      // Given
      when(descriptionDto.getNumberOfRooms()).thenReturn(NEGATIVE_NUMBER);
      // When
      descriptionCorruptionVerificator.editEstate(estateDto);
      
      // Then expected InvalidDescriptionFieldException
   }
   
   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingDescriptionCorruptionWhenDescriptionHasNullNumberOfLevelShouldThrowInvalidDescriptionFieldException() 
         throws InvalidDescriptionFieldException, InvalidDescriptionException, InvalidEstateException {
      // Given
      when(descriptionDto.getNumberOfLevel()).thenReturn(null);
      // When
      descriptionCorruptionVerificator.editEstate(estateDto);
      
      // Then expected InvalidDescriptionFieldException
   }
   
   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingDescriptionCorruptionWhenDescriptionHasNegativeNumberOfLevelShouldThrowInvalidDescriptionFieldException() 
         throws InvalidDescriptionFieldException, InvalidDescriptionException, InvalidEstateException {
      // Given
      when(descriptionDto.getNumberOfLevel()).thenReturn(NEGATIVE_NUMBER);
      // When
      descriptionCorruptionVerificator.editEstate(estateDto);
      
      // Then expected InvalidDescriptionFieldException
   }
   
   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingDescriptionCorruptionWhenDescriptionHasNullYearsOfConstructionShouldThrowInvalidDescriptionFieldException() 
         throws InvalidDescriptionFieldException, InvalidDescriptionException, InvalidEstateException {
      // Given
      when(descriptionDto.getYearsOfConstruction()).thenReturn(null);
      // When
      descriptionCorruptionVerificator.editEstate(estateDto);
      
      // Then expected InvalidDescriptionFieldException
   }
   
   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingDescriptionCorruptionWhenDescriptionHasNegativeYearsOfConstructionShouldThrowInvalidDescriptionFieldException() 
         throws InvalidDescriptionFieldException, InvalidDescriptionException, InvalidEstateException {
      // Given
      when(descriptionDto.getYearsOfConstruction()).thenReturn(NEGATIVE_NUMBER);
      // When
      descriptionCorruptionVerificator.editEstate(estateDto);
      
      // Then expected InvalidDescriptionFieldException
   }
   
   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingDescriptionCorruptionWhenDescriptionHasNullDimensionBuildingShouldThrowInvalidDescriptionFieldException() 
         throws InvalidDescriptionFieldException, InvalidDescriptionException, InvalidEstateException {
      // Given
      when(descriptionDto.getDimensionsBuilding()).thenReturn(null);
      // When
      descriptionCorruptionVerificator.editEstate(estateDto);
      
      // Then expected InvalidDescriptionFieldException
   }
   
   
   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingDescriptionCorruptionWhenDescriptionHasNullLivingSpaceAreaShouldThrowInvalidDescriptionFieldException() 
         throws InvalidDescriptionFieldException, InvalidDescriptionException, InvalidEstateException {
      // Given
      when(descriptionDto.getLivingSpaceAreaSquareMeter()).thenReturn(null);
      // When
      descriptionCorruptionVerificator.editEstate(estateDto);
      
      // Then expected InvalidDescriptionFieldException
   }
   
   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingDescriptionCorruptionWhenDescriptionHasNegativeLivingSpaceAreaShouldThrowInvalidDescriptionFieldException() 
         throws InvalidDescriptionFieldException, InvalidDescriptionException, InvalidEstateException {
      // Given
      when(descriptionDto.getLivingSpaceAreaSquareMeter()).thenReturn(NEGATIVE_NUMBER);
      // When
      descriptionCorruptionVerificator.editEstate(estateDto);
      
      // Then expected InvalidDescriptionFieldException
   }
   
   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingDescriptionCorruptionWhenDescriptionHasNullMunicipalValuationShouldThrowInvalidDescriptionFieldException() 
         throws InvalidDescriptionFieldException, InvalidDescriptionException, InvalidEstateException {
      // Given
      when(descriptionDto.getMunicipalValuation()).thenReturn(null);
      // When
      descriptionCorruptionVerificator.editEstate(estateDto);
      
      // Then expected InvalidDescriptionFieldException
   }
   
   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingDescriptionCorruptionWhenDescriptionHasNegativeMunicipalValuationShouldThrowInvalidDescriptionFieldException() 
         throws InvalidDescriptionFieldException, InvalidDescriptionException, InvalidEstateException {
      // Given
      when(descriptionDto.getMunicipalValuation()).thenReturn(NEGATIVE_NUMBER);
      // When
      descriptionCorruptionVerificator.editEstate(estateDto);
      
      // Then expected InvalidDescriptionFieldException
   }
   
   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingDescriptionCorruptionWhenDescriptionHasNullBackyardFacesShouldThrowInvalidDescriptionFieldException() 
         throws InvalidDescriptionFieldException, InvalidDescriptionException, InvalidEstateException {
      // Given
      when(descriptionDto.getBackyardFaces()).thenReturn(null);
      // When
      descriptionCorruptionVerificator.editEstate(estateDto);
      
      // Then expected InvalidDescriptionFieldException
   }
}
