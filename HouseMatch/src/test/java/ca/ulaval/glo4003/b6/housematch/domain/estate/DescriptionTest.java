package ca.ulaval.glo4003.b6.housematch.domain.estate;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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
   
   private Description description1;
   private Description description2;
   
   @Before
   public void setup(){
      
      description1 = new Description(numberOfBedRooms, numberOfBathrooms, numberOfRooms, 
            numberOfLevel, yearsOfConstruction, dimensionsBuilding, livingSpaceAreaSquareMeter, 
            municipalValuation, backyardOrientation);
      
      description2 = new Description(4, 4, 4, 
            2, 2002, "abcd", 500, 
            450000, "front");
   }
   
   @Test
   public void havingBackYardZeroWhenComparingReturnsTrue() {
      //given
      description1 = new Description(0, numberOfBathrooms, numberOfRooms, 
            numberOfLevel, yearsOfConstruction, dimensionsBuilding, livingSpaceAreaSquareMeter, 
            municipalValuation, backyardOrientation);
      //when
      boolean result = description1.isChangeSignificant(description2);

      //then
      assertTrue(result);
   }
   
   @Test
   public void havingBuildingDimensionZeroWhenComparingReturnsTrue() {
      //given
      description1 = new Description(numberOfBedRooms, 0, numberOfRooms, 
            numberOfLevel, yearsOfConstruction, dimensionsBuilding, livingSpaceAreaSquareMeter, 
            municipalValuation, backyardOrientation);
      //when
      boolean result = description1.isChangeSignificant(description2);

      //then
      assertTrue(result);
   }
   
   @Test
   public void havingDescription1DifferentReturnTrue(){
      
      boolean result = description1.isChangeSignificant(description2);
      
      assertTrue(result);
   }
   
   @Test
   public void havingDescription1SameReturnFalse(){
      
      boolean result = description1.isChangeSignificant(description1);
      
      assertFalse(result);
   }
   
   @Test
   public void havingVeryCloseButDifferentReturnFalse(){
      description1 = new Description(10, 10, 10, 
            10, 2002, "abcdefg", 550,
            45000, "abcdefg");
      
      description2 = new Description(9, 9, 9, 
            9, 2003, "abcdefgh", 545,
            45003, "abcdef");
      
      boolean result = description1.isChangeSignificant(description2);
      
      assertFalse(result);
   }
   
   

}
