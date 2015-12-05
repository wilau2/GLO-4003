package ca.ulaval.glo4003.b6.housematch.domain.estate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.ChangeIsSignificantException;

public class DescriptionTest {

   private static final Integer numberOfBedRooms = 2;

   private static final Integer numberOfBathrooms = 2;

   private static final Integer numberOfRooms = 2;

   private static final Integer numberOfLevel = 2;

   private static final Integer yearsOfConstruction = 2015;

   private static final Integer livingSpaceAreaSquareMeter = 300;

   private static final Integer municipalValuation = 250000;

   private static final String backyardOrientation = "back";

   private static final String dimensionsBuilding = "dcba";

   private static final double PERCENTAGE = 25.00;

   private Description description;

   private Description updatedDescription;

   @Mock
   private ChangeVerificator changeVerificator;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);

      description = new Description(numberOfBedRooms, numberOfBathrooms, numberOfRooms, numberOfLevel,
            yearsOfConstruction, dimensionsBuilding, livingSpaceAreaSquareMeter, municipalValuation,
            backyardOrientation);

      updatedDescription = new Description(4, 4, 4, 2, 2002, "abcd", 500, 450000, "front");
   }

   @Test
   public void whenValidatingIfChangeIsSignificantShouldCallDegreeChangeVerificatorStringMethodForBothStringField()
         throws ChangeIsSignificantException {
      // Given

      // When
      description.isChangeSignificant(updatedDescription, changeVerificator);

      // Then
      verify(changeVerificator, times(2)).verifyingChangeLevel(eq(PERCENTAGE), anyString(), anyString());
   }

   @Test
   public void whenValidatingIfChangeIsSignificantShouldCallDegreeChangeVerificatorMethod()
         throws ChangeIsSignificantException {
      // Given no changes
      int numberOfIntegerField = 7;

      // When
      description.isChangeSignificant(updatedDescription, changeVerificator);

      // Then
      verify(changeVerificator, times(numberOfIntegerField)).verifyingChangeLevel(eq(PERCENTAGE), any(Integer.class),
            any(Integer.class));
   }

   @Test
   public void validatingIfChangeIsSignificantWhenChangeOnFieldStringIsSignificantShouldReturnTrue()
         throws ChangeIsSignificantException {
      // Given
      doThrow(new ChangeIsSignificantException()).when(changeVerificator).verifyingChangeLevel(eq(PERCENTAGE),
            anyString(), anyString());

      // When
      boolean changeSignificant = description.isChangeSignificant(updatedDescription, changeVerificator);

      // Then
      assertTrue(changeSignificant);
   }

   @Test
   public void validatingIfChangeIsSignificantWhenChangeOnIntFieldIsSignificantShouldReturnTrue()
         throws ChangeIsSignificantException {
      // Given
      doThrow(new ChangeIsSignificantException()).when(changeVerificator).verifyingChangeLevel(eq(PERCENTAGE),
            any(Integer.class), any(Integer.class));

      // When
      boolean changeSignificant = description.isChangeSignificant(updatedDescription, changeVerificator);

      // Then
      assertTrue(changeSignificant);
   }

   @Test
   public void whenCreatingAnEmptyDescriptionShouldInitializeAllFields() {
      // Given no changes
      Integer expectedInitializedInteger = 0;

      // When
      description = new Description();

      // Then
      assertTrue(description.getBackyardOrientation().isEmpty());
      assertTrue(description.getBuildingDimensions().isEmpty());
      assertEquals(expectedInitializedInteger, description.getLivingSpaceAreaSquareMeter());
      assertEquals(expectedInitializedInteger, description.getMunicipalAssessment());
      assertEquals(expectedInitializedInteger, description.getNumberOfBathrooms());
      assertEquals(expectedInitializedInteger, description.getNumberOfBedRooms());
      assertEquals(expectedInitializedInteger, description.getNumberOfLevel());
      assertEquals(expectedInitializedInteger, description.getNumberOfRooms());
      assertEquals(expectedInitializedInteger, description.getYearOfConstruction());
   }
}
