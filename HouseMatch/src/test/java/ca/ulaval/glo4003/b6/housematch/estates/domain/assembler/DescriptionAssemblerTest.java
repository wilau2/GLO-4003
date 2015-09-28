package ca.ulaval.glo4003.b6.housematch.estates.domain.assembler;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Description;
import ca.ulaval.glo4003.b6.housematch.estates.domain.Land;
import ca.ulaval.glo4003.b6.housematch.estates.domain.Room;
import ca.ulaval.glo4003.b6.housematch.estates.dto.DescriptionDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.LandDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.RoomDto;

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
   
   private static final String ESTATE_ID = "123falsestreetG3K1P5"; 
   
   @Mock
   private RoomAssembler roomAssembler;
   
   @Mock
   private LandAssembler landAssembler;
   
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
   }
   
   private void configureDescription() {
      when(description.getNumberOfBedRooms()).thenReturn(NUMBER_OF_BED_ROOMS);
      when(description.getNumberOfBathrooms()).thenReturn(NUMBER_OF_BATHROOMS);
      when(description.getNumberOfRooms()).thenReturn(NUMBER_OF_ROOMS);
      when(description.getNumberOfLevel()).thenReturn(NUMBER_OF_FLOOR);
      when(description.getYearsOfConstruction()).thenReturn(YEAR_OF_CONSTRUCTION);
      when(description.getDimensionsBuilding()).thenReturn(DIMENSION_BUILDING);
      when(description.getLivingSpaceAreaSquareMeter()).thenReturn(LIVING_SPACE);
      when(description.getMunicipalValuation()).thenReturn(MUNICIPAL_VALUATION);
      when(description.getBackyardFaces()).thenReturn(BACKYARD_FACE);
   }
   
   @Test
   public void whenAssemblingAnDescriptionDtoFromAnEstateShouldSetCorrectlyAllFieldOfDto() {
      // Given 

      // When
      DescriptionDto returnedDescriptionDto = descriptionAssembler.assembleDescriptionDto(description, ESTATE_ID);

      // Then
      assertEquals(NUMBER_OF_BED_ROOMS, returnedDescriptionDto.getNumberOfBedRooms());
      assertEquals(NUMBER_OF_BATHROOMS, returnedDescriptionDto.getNumberOfBathrooms());
      assertEquals(NUMBER_OF_ROOMS, returnedDescriptionDto.getNumberOfRooms());
      assertEquals(NUMBER_OF_FLOOR, returnedDescriptionDto.getNumberOfLevel());
      assertEquals(YEAR_OF_CONSTRUCTION, returnedDescriptionDto.getYearsOfConstruction());
      assertEquals(DIMENSION_BUILDING, returnedDescriptionDto.getDimensionsBuilding());
      assertEquals(LIVING_SPACE, returnedDescriptionDto.getLivingSpaceAreaSquareMeter());
      assertEquals(MUNICIPAL_VALUATION, returnedDescriptionDto.getMunicipalValuation());
      assertEquals(BACKYARD_FACE, returnedDescriptionDto.getBackyardFaces());
   }

}
