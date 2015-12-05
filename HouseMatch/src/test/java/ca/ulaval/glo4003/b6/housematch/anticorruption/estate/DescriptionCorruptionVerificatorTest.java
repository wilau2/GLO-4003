package ca.ulaval.glo4003.b6.housematch.anticorruption.estate;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.anticorruption.estate.exceptions.InvalidDescriptionFieldException;
import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.EstateNotFoundException;
import ca.ulaval.glo4003.b6.housematch.dto.DescriptionDto;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.estate.EstatesService;
import ca.ulaval.glo4003.b6.housematch.services.estate.exceptions.InvalidDescriptionException;
import ca.ulaval.glo4003.b6.housematch.services.estate.exceptions.InvalidEstateException;

public class DescriptionCorruptionVerificatorTest {

   private static final Integer NEGATIVE_NUMBER = -1;

   private static final Integer NUMBER_OF_BEDROOMS = 1;

   private static final Integer NUMBER_OF_BATHROOMS = 2;

   private static final Integer NUMBER_OF_ROOMS = 3;

   private static final Integer NUMBER_OF_LEVEL = 2;

   private static final Integer YEAR_OF_CONSTRUCTION = 1990;

   private static final String DIMENSION_OF_BUILDING = "20x20";

   private static final Integer LIVING_SPACE_AREA = 400;

   private static final Integer MUNICIPAL_VALUATION = 200000;

   private static final String BACKYARD_ORIENTATION = "North";

   private static final String ADDRESS = "address";

   @Mock
   private DescriptionDto descriptionDto;

   @Mock
   private EstatesService estateService;

   @InjectMocks
   private DescriptionCorruptionVerificator descriptionCorruptionVerificator;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);

      configureValidDescriptionModel();
   }

   private void configureValidDescriptionModel() {
      when(descriptionDto.getNumberOfBedRooms()).thenReturn(NUMBER_OF_BEDROOMS);
      when(descriptionDto.getNumberOfBathrooms()).thenReturn(NUMBER_OF_BATHROOMS);
      when(descriptionDto.getNumberOfRooms()).thenReturn(NUMBER_OF_ROOMS);
      when(descriptionDto.getNumberOfLevel()).thenReturn(NUMBER_OF_LEVEL);
      when(descriptionDto.getYearOfConstruction()).thenReturn(YEAR_OF_CONSTRUCTION);
      when(descriptionDto.getBuildingDimension()).thenReturn(DIMENSION_OF_BUILDING);
      when(descriptionDto.getLivingSpaceAreaSquareMeter()).thenReturn(LIVING_SPACE_AREA);
      when(descriptionDto.getMunicipalAssessment()).thenReturn(MUNICIPAL_VALUATION);
      when(descriptionDto.getBackyardOrientation()).thenReturn(BACKYARD_ORIENTATION);

   }

   @Test
   public void verificatingDescriptionCorruptionWhenNoCorruptionSouldCallAddDescription()
         throws InvalidDescriptionFieldException, InvalidDescriptionException, InvalidEstateException,
         CouldNotAccessDataException, EstateNotFoundException {
      // Given

      // When
      descriptionCorruptionVerificator.validateDescriptionCorruption(descriptionDto);

      // Then
      verify(estateService, never()).editDescription(ADDRESS, descriptionDto);
   }

   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingDescriptionCorruptionWhenNullNumberOfBedroomsShouldThrowInvalidDescriptionFieldException()
         throws InvalidDescriptionFieldException, InvalidDescriptionException, InvalidEstateException,
         CouldNotAccessDataException, EstateNotFoundException {
      // Given
      when(descriptionDto.getNumberOfBedRooms()).thenReturn(null);

      // When
      descriptionCorruptionVerificator.validateDescriptionCorruption(descriptionDto);

      // Then expected InvalidDescriptionFieldException
   }

   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingCorruptionWhenNegativeNumberOfBedroomsShouldThrowInvalidDescriptionFieldException()
         throws InvalidDescriptionFieldException, InvalidDescriptionException, InvalidEstateException,
         CouldNotAccessDataException, EstateNotFoundException {
      // Given
      when(descriptionDto.getNumberOfBedRooms()).thenReturn(NEGATIVE_NUMBER);

      // When
      descriptionCorruptionVerificator.validateDescriptionCorruption(descriptionDto);

      // Then expected InvalidDescriptionFieldException
   }

   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingDescriptionCorruptionWhenNullNumberOfBathroomsShouldThrowInvalidDescriptionFieldException()
         throws InvalidDescriptionFieldException, InvalidDescriptionException, InvalidEstateException,
         CouldNotAccessDataException, EstateNotFoundException {
      // Given
      when(descriptionDto.getNumberOfBathrooms()).thenReturn(null);

      // When
      descriptionCorruptionVerificator.validateDescriptionCorruption(descriptionDto);

      // Then expected InvalidDescriptionFieldException
   }

   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingCorruptionWhenNegativeNumberOfBathroomsShouldThrowInvalidDescriptionFieldException()
         throws InvalidDescriptionFieldException, InvalidDescriptionException, InvalidEstateException,
         CouldNotAccessDataException, EstateNotFoundException {
      // Given
      when(descriptionDto.getNumberOfBathrooms()).thenReturn(NEGATIVE_NUMBER);

      // When
      descriptionCorruptionVerificator.validateDescriptionCorruption(descriptionDto);

      // Then expected InvalidDescriptionFieldException
   }

   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingDescriptionCorruptionWhenNullNumberOfRoomsShouldThrowInvalidDescriptionFieldException()
         throws InvalidDescriptionFieldException, InvalidDescriptionException, InvalidEstateException,
         CouldNotAccessDataException, EstateNotFoundException {
      // Given
      when(descriptionDto.getNumberOfRooms()).thenReturn(null);

      // When
      descriptionCorruptionVerificator.validateDescriptionCorruption(descriptionDto);

      // Then expected InvalidDescriptionFieldException
   }

   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingDescriptionCorruptionWhenNegativeNumberOfRoomsShouldThrowInvalidDescriptionFieldException()
         throws InvalidDescriptionFieldException, InvalidDescriptionException, InvalidEstateException,
         CouldNotAccessDataException, EstateNotFoundException {
      // Given
      when(descriptionDto.getNumberOfRooms()).thenReturn(NEGATIVE_NUMBER);

      // When
      descriptionCorruptionVerificator.validateDescriptionCorruption(descriptionDto);

      // Then expected InvalidDescriptionFieldException
   }

   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingDescriptionCorruptionWhenNullNumberOfLevelShouldThrowInvalidDescriptionFieldException()
         throws InvalidDescriptionFieldException, InvalidDescriptionException, InvalidEstateException,
         CouldNotAccessDataException, EstateNotFoundException {
      // Given
      when(descriptionDto.getNumberOfLevel()).thenReturn(null);

      // When
      descriptionCorruptionVerificator.validateDescriptionCorruption(descriptionDto);

      // Then expected InvalidDescriptionFieldException
   }

   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingDescriptionCorruptionWhenNegativeNumberOfLevelShouldThrowInvalidDescriptionFieldException()
         throws InvalidDescriptionFieldException, InvalidDescriptionException, InvalidEstateException,
         CouldNotAccessDataException, EstateNotFoundException {
      // Given
      when(descriptionDto.getNumberOfLevel()).thenReturn(NEGATIVE_NUMBER);

      // When
      descriptionCorruptionVerificator.validateDescriptionCorruption(descriptionDto);

      // Then expected InvalidDescriptionFieldException
   }

   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingDescriptionCorruptionWhenNullYearOfConstructionShouldThrowInvalidDescriptionFieldException()
         throws InvalidDescriptionFieldException, InvalidDescriptionException, InvalidEstateException,
         CouldNotAccessDataException, EstateNotFoundException {
      // Given
      when(descriptionDto.getYearOfConstruction()).thenReturn(null);
      // When
      descriptionCorruptionVerificator.validateDescriptionCorruption(descriptionDto);

      // Then expected InvalidDescriptionFieldException
   }

   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingCorruptionWhenNegativeYearOfConstructionShouldThrowInvalidDescriptionFieldException()
         throws InvalidDescriptionFieldException, InvalidDescriptionException, InvalidEstateException,
         CouldNotAccessDataException, EstateNotFoundException {
      // Given
      when(descriptionDto.getYearOfConstruction()).thenReturn(NEGATIVE_NUMBER);
      // When
      descriptionCorruptionVerificator.validateDescriptionCorruption(descriptionDto);

      // Then expected InvalidDescriptionFieldException
   }

   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingDescriptionCorruptionWhenNullDimensionBuildingShouldThrowInvalidDescriptionFieldException()
         throws InvalidDescriptionFieldException, InvalidDescriptionException, InvalidEstateException,
         CouldNotAccessDataException, EstateNotFoundException {
      // Given
      when(descriptionDto.getBuildingDimension()).thenReturn(null);

      // When
      descriptionCorruptionVerificator.validateDescriptionCorruption(descriptionDto);

      // Then expected InvalidDescriptionFieldException
   }

   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingDescriptionCorruptionWhenNullLivingSpaceAreaShouldThrowInvalidDescriptionFieldException()
         throws InvalidDescriptionFieldException, InvalidDescriptionException, InvalidEstateException,
         CouldNotAccessDataException, EstateNotFoundException {
      // Given
      when(descriptionDto.getLivingSpaceAreaSquareMeter()).thenReturn(null);

      // When
      descriptionCorruptionVerificator.validateDescriptionCorruption(descriptionDto);

      // Then expected InvalidDescriptionFieldException
   }

   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingDescriptionCorruptionWhenNegativeLivingSpaceAreaShouldThrowInvalidDescriptionFieldException()
         throws InvalidDescriptionFieldException, InvalidDescriptionException, InvalidEstateException,
         CouldNotAccessDataException, EstateNotFoundException {
      // Given
      when(descriptionDto.getLivingSpaceAreaSquareMeter()).thenReturn(NEGATIVE_NUMBER);

      // When
      descriptionCorruptionVerificator.validateDescriptionCorruption(descriptionDto);

      // Then expected InvalidDescriptionFieldException
   }

   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingDescriptionCorruptionWhenNullMunicipalAssessmentShouldThrowInvalidDescriptionFieldException()
         throws InvalidDescriptionFieldException, InvalidDescriptionException, InvalidEstateException,
         CouldNotAccessDataException, EstateNotFoundException {
      // Given
      when(descriptionDto.getMunicipalAssessment()).thenReturn(null);

      // When
      descriptionCorruptionVerificator.validateDescriptionCorruption(descriptionDto);

      // Then expected InvalidDescriptionFieldException
   }

   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingCorruptionWhenNegativeMunicipalAssessmentShouldThrowInvalidDescriptionFieldException()
         throws InvalidDescriptionFieldException, InvalidDescriptionException, InvalidEstateException,
         CouldNotAccessDataException, EstateNotFoundException {
      // Given
      when(descriptionDto.getMunicipalAssessment()).thenReturn(NEGATIVE_NUMBER);

      // When
      descriptionCorruptionVerificator.validateDescriptionCorruption(descriptionDto);

      // Then expected InvalidDescriptionFieldException
   }

   @Test(expected = InvalidDescriptionFieldException.class)
   public void verificatingDescriptionCorruptionWhenNullBackyardOrientationShouldThrowInvalidDescriptionFieldException()
         throws InvalidDescriptionFieldException, InvalidDescriptionException, InvalidEstateException,
         CouldNotAccessDataException, EstateNotFoundException {
      // Given
      when(descriptionDto.getBackyardOrientation()).thenReturn(null);

      // When
      descriptionCorruptionVerificator.validateDescriptionCorruption(descriptionDto);

      // Then expected InvalidDescriptionFieldException
   }
}
