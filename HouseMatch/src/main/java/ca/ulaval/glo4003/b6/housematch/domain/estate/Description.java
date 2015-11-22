package ca.ulaval.glo4003.b6.housematch.domain.estate;

import org.apache.commons.lang.StringUtils;

public class Description {

   private Integer numberOfBedRooms;

   private Integer numberOfBathrooms;

   private Integer numberOfRooms;

   private Integer numberOfLevel;

   private Integer yearOfConstruction;

   private String buildingDimensions;

   private Integer livingSpaceAreaSquareMeter;

   private Integer municipalAssessment;

   private String backyardOrientation;

   public Description(Integer numberOfBedRooms, Integer numberOfBathrooms, Integer numberOfRooms, Integer numberOfLevel,
         Integer yearsOfConstruction, String dimensionsBuilding, Integer livingSpaceAreaSquareMeter,
         Integer municipalValuation, String backyardOrientation) {

      this.numberOfBedRooms = numberOfBedRooms;
      this.numberOfBathrooms = numberOfBathrooms;
      this.numberOfRooms = numberOfRooms;
      this.numberOfLevel = numberOfLevel;
      this.yearOfConstruction = yearsOfConstruction;
      this.buildingDimensions = dimensionsBuilding;
      this.livingSpaceAreaSquareMeter = livingSpaceAreaSquareMeter;
      this.municipalAssessment = municipalValuation;
      this.backyardOrientation = backyardOrientation;
   }

   public Description() {
      this.numberOfBedRooms = 0;
      this.numberOfBathrooms = 0;
      this.numberOfRooms = 0;
      this.numberOfLevel = 0;
      this.yearOfConstruction = 0;
      this.buildingDimensions = "";
      this.livingSpaceAreaSquareMeter = 0;
      this.municipalAssessment = 0;
      this.backyardOrientation = "";
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

   public String getBackyardOrientation() {
      return backyardOrientation;
   }

   public boolean isChangeSignificant(Description description) {
      
      boolean result = false;
      
      if(this.buildingDimensions.length() == 0 || this.backyardOrientation.length() == 0){
         return true;
      }
      
      double differenceBuildingDimensions = StringUtils.getLevenshteinDistance(this.buildingDimensions, description.getBuildingDimensions()) / this.buildingDimensions.length() * 100;
      double differencebackyardOrientation = StringUtils.getLevenshteinDistance(this.backyardOrientation, description.getBackyardOrientation()) / this.backyardOrientation.length() * 100;
      
      if( getDifferencePercentage(this.livingSpaceAreaSquareMeter, description.getLivingSpaceAreaSquareMeter()) > 25.00 ||
            getDifferencePercentage(this.getMunicipalAssessment(), description.getMunicipalAssessment()) > 25.00 ||
            getDifferencePercentage(this.getNumberOfBathrooms(), description.getNumberOfBathrooms()) > 25.00 ||
            getDifferencePercentage(this.getNumberOfBedRooms(), description.getNumberOfBedRooms()) > 25.00 ||
            getDifferencePercentage(this.getNumberOfLevel(), description.getNumberOfLevel()) > 25.00 ||
            getDifferencePercentage(this.getNumberOfRooms(), description.getNumberOfRooms()) > 25.00 ||
            getDifferencePercentage(this.getYearOfConstruction(), description.getYearOfConstruction()) > 25.00 ||
            differencebackyardOrientation > 25.00 ||
            differenceBuildingDimensions > 25.00)
         {
            result = true;
         }
      
      return result;
   }
   
   public double getDifferencePercentage(int numberOne, int numberTwo){
      if (numberOne == 0){
         return 100;
      }
      return Math.abs((numberOne-numberTwo)/numberOne * 100);
   }
}
