package ca.ulaval.glo4003.b6.housematch.estates.domain.assembler;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Description;
import ca.ulaval.glo4003.b6.housematch.estates.dto.DescriptionDto;

public class DescriptionAssemblerTest {

   private static final Integer NUMBER_OF_BED_ROOMS = 1;
   private static final Integer NUMBER_OF_BATHROOMS = 2;
   private static final Integer NUMBER_OF_ROOMS = 9;
   private static final Integer NUMBER_OF_FLOOR = 3;
   private static final Integer YEAR_OF_CONSTRUCTION = 1990;
   private static final String DIMENSION_BUILDING = "20x20";
   private static final Integer LIVING_SPACE = 400;
   private static final Integer MUNICIPAL_VALUATION = 200000;
   private static final String BACKYARD_FACE = "North";
   
   @Mock
   private DescriptionDto descriptionDto;
   
   @Mock
   private Description description;
   
   @InjectMocks
   private DescriptionAssembler descriptionAssembler;
   
   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);

      configureDescription();
      configureDescriptionDto();
   }
   
   private void configureDescriptionDto(){
      when(descriptionDto.getNumberOfBedRooms()).thenReturn(NUMBER_OF_BED_ROOMS);
      when(descriptionDto.getNumberOfBathrooms()).thenReturn(NUMBER_OF_BATHROOMS);
      when(descriptionDto.getNumberOfRooms()).thenReturn(NUMBER_OF_ROOMS);
      when(descriptionDto.getNumberOfLevel()).thenReturn(NUMBER_OF_FLOOR);
      when(descriptionDto.getYearOfConstruction()).thenReturn(YEAR_OF_CONSTRUCTION);
      when(descriptionDto.getBuildingDimensions()).thenReturn(DIMENSION_BUILDING);
      when(descriptionDto.getLivingSpaceAreaSquareMeter()).thenReturn(LIVING_SPACE);
      when(descriptionDto.getMunicipalAssessment()).thenReturn(MUNICIPAL_VALUATION);
      when(descriptionDto.getBackyardOrientation()).thenReturn(BACKYARD_FACE);
   }
   private void configureDescription() {
      when(description.getNumberOfBedRooms()).thenReturn(NUMBER_OF_BED_ROOMS);
      when(description.getNumberOfBathrooms()).thenReturn(NUMBER_OF_BATHROOMS);
      when(description.getNumberOfRooms()).thenReturn(NUMBER_OF_ROOMS);
      when(description.getNumberOfLevel()).thenReturn(NUMBER_OF_FLOOR);
      when(description.getYearOfConstruction()).thenReturn(YEAR_OF_CONSTRUCTION);
      when(description.getBuildingDimensions()).thenReturn(DIMENSION_BUILDING);
      when(description.getLivingSpaceAreaSquareMeter()).thenReturn(LIVING_SPACE);
      when(description.getMunicipalAssessment()).thenReturn(MUNICIPAL_VALUATION);
      when(description.getBackyardOrientation()).thenReturn(BACKYARD_FACE);
   }
   
   @Test
   public void whenAssemblingAnDescriptionDtoFromAnEstateShouldSetCorrectlyAllFieldOfDto() {
      // Given 

      // When
      DescriptionDto returnedDescriptionDto = descriptionAssembler.assembleDescriptionDto(description);

      // Then
      assertEquals(NUMBER_OF_BED_ROOMS, returnedDescriptionDto.getNumberOfBedRooms());
      assertEquals(NUMBER_OF_BATHROOMS, returnedDescriptionDto.getNumberOfBathrooms());
      assertEquals(NUMBER_OF_ROOMS, returnedDescriptionDto.getNumberOfRooms());
      assertEquals(NUMBER_OF_FLOOR, returnedDescriptionDto.getNumberOfLevel());
      assertEquals(YEAR_OF_CONSTRUCTION, returnedDescriptionDto.getYearOfConstruction());
      assertEquals(DIMENSION_BUILDING, returnedDescriptionDto.getBuildingDimensions());
      assertEquals(LIVING_SPACE, returnedDescriptionDto.getLivingSpaceAreaSquareMeter());
      assertEquals(MUNICIPAL_VALUATION, returnedDescriptionDto.getMunicipalAssessment());
      assertEquals(BACKYARD_FACE, returnedDescriptionDto.getBackyardOrientation());
   }
   
   @Test
   public void whenAssemblingAnDescriptionFromAnDescriptionDTOShouldSetCorrectlyAllField() {
      // Given 

      // When
      Description description = descriptionAssembler.assembleDescription(descriptionDto);

      // Then
      assertEquals(NUMBER_OF_BED_ROOMS, description.getNumberOfBedRooms());
      assertEquals(NUMBER_OF_BATHROOMS, description.getNumberOfBathrooms());
      assertEquals(NUMBER_OF_ROOMS, description.getNumberOfRooms());
      assertEquals(NUMBER_OF_FLOOR, description.getNumberOfLevel());
      assertEquals(YEAR_OF_CONSTRUCTION, description.getYearOfConstruction());
      assertEquals(DIMENSION_BUILDING, description.getBuildingDimensions());
      assertEquals(LIVING_SPACE, description.getLivingSpaceAreaSquareMeter());
      assertEquals(MUNICIPAL_VALUATION, description.getMunicipalAssessment());
      assertEquals(BACKYARD_FACE, description.getBackyardOrientation());
   }

}
