package ca.ulaval.glo4003.b6.housematch.domain.estate;

import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.ChangeIsSignificantException;

public class Description {

   private static final double PERCENTAGE_OF_ACCEPTABLE_CHANGE = 25.00;

   private Integer numberOfBedRooms;

   private Integer numberOfBathrooms;

   private Integer numberOfRooms;

   private Integer numberOfLevel;

   private Integer yearOfConstruction;

   private Integer livingSpaceAreaSquareMeter;

   private Integer municipalAssessment;

   private String buildingDimensions;

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

   boolean isChangeSignificant(Description updatedDescription, ChangeVerificator changeVerificator) {

      try {
         changeVerificator.verifyingChangeLevel(PERCENTAGE_OF_ACCEPTABLE_CHANGE, buildingDimensions,
               updatedDescription.buildingDimensions);
         changeVerificator.verifyingChangeLevel(PERCENTAGE_OF_ACCEPTABLE_CHANGE, backyardOrientation,
               updatedDescription.backyardOrientation);

         changeVerificator.verifyingChangeLevel(PERCENTAGE_OF_ACCEPTABLE_CHANGE, livingSpaceAreaSquareMeter,
               updatedDescription.getLivingSpaceAreaSquareMeter());
         changeVerificator.verifyingChangeLevel(PERCENTAGE_OF_ACCEPTABLE_CHANGE, municipalAssessment,
               updatedDescription.getMunicipalAssessment());
         changeVerificator.verifyingChangeLevel(PERCENTAGE_OF_ACCEPTABLE_CHANGE, numberOfBathrooms,
               updatedDescription.getNumberOfBathrooms());
         changeVerificator.verifyingChangeLevel(PERCENTAGE_OF_ACCEPTABLE_CHANGE, numberOfBedRooms,
               updatedDescription.getNumberOfBedRooms());
         changeVerificator.verifyingChangeLevel(PERCENTAGE_OF_ACCEPTABLE_CHANGE, numberOfLevel,
               updatedDescription.getNumberOfLevel());
         changeVerificator.verifyingChangeLevel(PERCENTAGE_OF_ACCEPTABLE_CHANGE, numberOfRooms,
               updatedDescription.getNumberOfRooms());
         changeVerificator.verifyingChangeLevel(PERCENTAGE_OF_ACCEPTABLE_CHANGE, yearOfConstruction,
               updatedDescription.getYearOfConstruction());

      } catch (ChangeIsSignificantException e) {
         return true;
      }
      return false;

   }

}
