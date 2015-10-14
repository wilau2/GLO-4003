package ca.ulaval.glo4003.b6.housematch.estates.domain;

import java.util.List;

public class Description {
   
   private Integer numberOfBedRooms;
   private Integer numberOfBathrooms;
   private Integer numberOfRooms;
   private Integer numberOfLevel;
   private Integer yearOfConstruction;
   private String buildingDimensions;
   private Integer livingSpaceAreaSquareMeter;
   private Integer municipalAssessment;
   private String backyardFaces;

   public Description(Integer numberOfBedRooms, Integer numberOfBathrooms, Integer numberOfRooms,
          Integer numberOfLevel, Integer yearsOfConstruction, String dimensionsBuilding,
          Integer livingSpaceAreaSquareMeter, Integer municipalValuation, String backyardFaces) {
         
         this.numberOfBedRooms = numberOfBedRooms;
         this.numberOfBathrooms = numberOfBathrooms;
         this.numberOfRooms = numberOfRooms;
         this.numberOfLevel = numberOfLevel;
         this.yearOfConstruction = yearsOfConstruction;
         this.buildingDimensions = dimensionsBuilding;
         this.livingSpaceAreaSquareMeter = livingSpaceAreaSquareMeter;
         this.municipalAssessment = municipalValuation;
         this.backyardFaces = backyardFaces;     
   }
   
   public Description() {
   }

   public Integer getNumberOfBedRooms() {
      return numberOfBedRooms;
   }

   
   public Integer getNumberOfBathrooms() {
      return numberOfBathrooms;
   }

   
   public Integer getNumberOfRooms() {
      return numberOfRooms;
   }

   
   public Integer getNumberOfLevel() {
      return numberOfLevel;
   }

   
   public Integer getYearOfConstruction() {
      return yearOfConstruction;
   }

   
   public String getBuildingDimensions() {
      return buildingDimensions;
   }

   
   public Integer getLivingSpaceAreaSquareMeter() {
      return livingSpaceAreaSquareMeter;
   }

   
   public Integer getMunicipalAssessment() {
      return municipalAssessment;
   }

   
   public String getBackyardFaces() {
      return backyardFaces;
   }
}
