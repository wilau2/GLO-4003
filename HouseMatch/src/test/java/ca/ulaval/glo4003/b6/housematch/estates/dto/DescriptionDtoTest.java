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
      descriptionDto.setBuildingDimension(dimensionsBuilding);
      descriptionDto.setLivingSpaceAreaSquareMeter(livingSpaceAreaSquareMeter);
      descriptionDto.setMunicipalAssessment(municipalValuation);
      descriptionDto.setNumberOfBathrooms(numberOfBathrooms);
      descriptionDto.setNumberOfBedRooms(numberOfBedRooms);
      descriptionDto.setNumberOfLevel(numberOfLevel);
      descriptionDto.setNumberOfRooms(numberOfRooms);
      descriptionDto.setYearsOfConstruction(yearsOfConstruction);
      descriptionDto.setBackyardOrientation(backyardFaces);
   }

   @Test
   public void settersWorksCorrectly() {

      assertTrue(descriptionDto.getBuildingDimension() == this.dimensionsBuilding);
      assertTrue(descriptionDto.getLivingSpaceAreaSquareMeter() == this.livingSpaceAreaSquareMeter);
      assertTrue(descriptionDto.getMunicipalAssessment() == this.municipalValuation);
      assertTrue(descriptionDto.getNumberOfBathrooms() == this.numberOfBathrooms);
      assertTrue(descriptionDto.getNumberOfBedRooms() == this.numberOfBedRooms);
      assertTrue(descriptionDto.getNumberOfLevel() == this.numberOfLevel);
      assertTrue(descriptionDto.getNumberOfRooms() == this.numberOfRooms);
      assertTrue(descriptionDto.getYearOfConstruction() == this.yearsOfConstruction);
      assertTrue(descriptionDto.getBackyardOrientation() == this.backyardFaces);
   }

}
