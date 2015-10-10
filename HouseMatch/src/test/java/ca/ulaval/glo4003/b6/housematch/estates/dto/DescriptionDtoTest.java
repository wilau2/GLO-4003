package ca.ulaval.glo4003.b6.housematch.estates.dto;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DescriptionDtoTest {
   
   DescriptionDto descriptionDto;
   
   private Integer numberOfBedRooms = 22;
   private Integer numberOfBathrooms = 22;
   private Integer numberOfRooms = 22;
   private Integer numberOfLevel =22;
   private Integer yearsOfConstruction=22;
   private String dimensionsBuilding="22";
   private Integer livingSpaceAreaSquareMeter=22;
   private Integer municipalValuation=22;
   private String backyardFaces="22";
   
   @Before
   public void setUp(){
      
      descriptionDto = new DescriptionDto(0, 0, 0, 0, 0, "", 0, 0, "");
      
      configureDto();
   }
   
   private void configureDto() {
      descriptionDto.setDimensionsBuilding(dimensionsBuilding);
      descriptionDto.setLivingSpaceAreaSquareMeter(livingSpaceAreaSquareMeter);
      descriptionDto.setMunicipalValuation(municipalValuation);
      descriptionDto.setNumberOfBathrooms(numberOfBathrooms);
      descriptionDto.setNumberOfBedRooms(numberOfBedRooms);
      descriptionDto.setNumberOfLevel(numberOfLevel);
      descriptionDto.setNumberOfRooms(numberOfRooms);
      descriptionDto.setYearsOfConstruction(yearsOfConstruction);
      descriptionDto.setBackyardFaces(backyardFaces);
   }

   @Test
   public void settersWorksCorrectly() {

      assertTrue(descriptionDto.getDimensionsBuilding() == this.dimensionsBuilding);
      assertTrue(descriptionDto.getLivingSpaceAreaSquareMeter() == this.livingSpaceAreaSquareMeter);
      assertTrue(descriptionDto.getMunicipalValuation() == this.municipalValuation);
      assertTrue(descriptionDto.getNumberOfBathrooms() == this.numberOfBathrooms);
      assertTrue(descriptionDto.getNumberOfBedRooms() == this.numberOfBedRooms);
      assertTrue(descriptionDto.getNumberOfLevel() == this.numberOfLevel);
      assertTrue(descriptionDto.getNumberOfRooms() == this.numberOfRooms);
      assertTrue(descriptionDto.getYearsOfConstruction() == this.yearsOfConstruction);
      assertTrue(descriptionDto.getBackyardFaces() == this.backyardFaces);
   }

}
