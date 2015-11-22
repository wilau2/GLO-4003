package ca.ulaval.glo4003.b6.housematch.persistence.estate.converter;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.b6.housematch.domain.estate.Description;

public class DescriptionElementConverterTest {
   
   private static final String NUMBER_OF_BEDROOMS = "numberOfBedRooms";
   private static final String NUMBER_OF_BATHROOMS = "numberOfBathrooms";
   private static final String NUMBER_OF_ROOMS = "numberOfRooms";
   private static final String NUMBER_OF_LEVELS = "numberOfLevel";
   private static final String YEAR_OF_CONSTRUCTION = "yearsOfConstruction";
   private static final String BUILDING_DIMENSIONS = "dimensionsBuilding";
   private static final String LIVING_SPACE = "livingSpaceAreaSquareMeter";
   private static final String MUNICIPAL_VALUATION = "municipalValuation";
   private static final String BACKYARD_FACES = "backyardFaces";
   
   private static final String STRING = "string";
   private static final Integer INTEGER = 45;
   private static final String STRINGINTEGER = "45";

   @Mock
   private Description description; 
   
   private DescriptionElementConverter descriptionElementConverter;
   
   @Before
   public void setUp(){
      MockitoAnnotations.initMocks(this);
      configureDescription();
      descriptionElementConverter = new DescriptionElementConverter();
   }
   
   @Test
   public void convertToAttributesCreatesHashMapWithAccurateValues(){
      //given
      
      //when
      HashMap<String, String> attributes = descriptionElementConverter.convertToAttributes(description);
      //then
      assertEquals(attributes.get(NUMBER_OF_BEDROOMS), STRINGINTEGER);
      assertEquals(attributes.get(NUMBER_OF_BATHROOMS), STRINGINTEGER);
      assertEquals(attributes.get(NUMBER_OF_ROOMS), STRINGINTEGER);
      assertEquals(attributes.get(NUMBER_OF_LEVELS), STRINGINTEGER);
      assertEquals(attributes.get(YEAR_OF_CONSTRUCTION), STRINGINTEGER);
      assertEquals(attributes.get(BUILDING_DIMENSIONS), STRING);
      assertEquals(attributes.get(LIVING_SPACE), STRINGINTEGER);
      assertEquals(attributes.get(MUNICIPAL_VALUATION), STRINGINTEGER);
      assertEquals(attributes.get(BACKYARD_FACES), STRING);
      
      
   }
   
   private void configureDescription(){
      when(description.getBackyardOrientation()).thenReturn(STRING);
      when(description.getMunicipalAssessment()).thenReturn(INTEGER);
      when(description.getBuildingDimensions()).thenReturn(STRING);
      when(description.getLivingSpaceAreaSquareMeter()).thenReturn(INTEGER);
      when(description.getNumberOfBathrooms()).thenReturn(INTEGER);
      when(description.getNumberOfBedRooms()).thenReturn(INTEGER);
      when(description.getNumberOfLevel()).thenReturn(INTEGER);
      when(description.getNumberOfRooms()).thenReturn(INTEGER);
      when(description.getYearOfConstruction()).thenReturn(INTEGER);
   }
}