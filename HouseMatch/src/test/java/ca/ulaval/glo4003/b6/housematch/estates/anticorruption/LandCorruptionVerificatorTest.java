package ca.ulaval.glo4003.b6.housematch.estates.anticorruption;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.estates.anticorruption.exceptions.InvalidLandFieldException;
import ca.ulaval.glo4003.b6.housematch.estates.dto.LandDto;

public class LandCorruptionVerificatorTest {
   private static final String EMPTY_FIELD = "";
   
   private static final String AQUEDUCT = "leach field";
   private static final String DIMENSION = "20x20";
   
   @Mock
   private LandDto landDto;
   
   @InjectMocks
   private LandCorruptionVerificator landCorruptionVerificator;
   
   @Before
   public void setup() throws InvalidLandFieldException {
      MockitoAnnotations.initMocks(this);

      configureValidLandModel();
   }

   private void configureValidLandModel() {
      when(landDto.getAqueduct()).thenReturn(AQUEDUCT);
      when(landDto.getDimensionsLot()).thenReturn(DIMENSION);
   }
   
   @Test
   public void verificatingLandCorruptionWhenLandHasNoCorruptionShouldNotThrowInvalidLandFieldException() throws InvalidLandFieldException {
      // Given
      
      // When
      landCorruptionVerificator.validateLandCorruption(landDto);
      
      // Then nothing
   }
   
   @Test(expected = InvalidLandFieldException.class)
   public void verificatingLandCorruptionWhenLandHasNullAqueductShouldThrowInvalidLandFieldException() throws InvalidLandFieldException {
      // Given
      when(landDto.getAqueduct()).thenReturn(null);
      // When
      landCorruptionVerificator.validateLandCorruption(landDto);
      
      // Then expected InvalidLandFieldException
   }
   
   @Test(expected = InvalidLandFieldException.class)
   public void verificatingLandCorruptionWhenLandHasEmptyAqueductShouldThrowInvalidLandFieldException() throws InvalidLandFieldException {
      // Given
      when(landDto.getAqueduct()).thenReturn(EMPTY_FIELD);
      // When
      landCorruptionVerificator.validateLandCorruption(landDto);
      
      // Then expected InvalidLandFieldException
   }
   
   @Test(expected = InvalidLandFieldException.class)
   public void verificatingLandCorruptionWhenLandHasNullDimensionLotShouldThrowInvalidLandFieldException() throws InvalidLandFieldException {
      // Given
      when(landDto.getDimensionsLot()).thenReturn(null);
      // When
      landCorruptionVerificator.validateLandCorruption(landDto);
      
      // Then expected InvalidLandFieldException
   }
   
   @Test(expected = InvalidLandFieldException.class)
   public void verificatingLandCorruptionWhenLandHasEmptyDimensionLotShouldThrowInvalidLandFieldException() throws InvalidLandFieldException {
      // Given
      when(landDto.getDimensionsLot()).thenReturn(EMPTY_FIELD);
      // When
      landCorruptionVerificator.validateLandCorruption(landDto);
      
      // Then expected InvalidLandFieldException
   }
   
}
