package ca.ulaval.glo4003.b6.housematch.estates.dto;

public class DescriptionDto {

   private Integer numberOfBedRooms;

   private Integer numberOfBathrooms;

   private Integer numberOfRooms;

   private Integer numberOfLevel;

   private Integer yearOfConstruction;

   private String buildingDimension;

   private Integer livingSpaceAreaSquareMeter;

   private Integer municipalAssessment;

   private String backyardOrientation;

   public DescriptionDto(Integer numberOfBedRooms, Integer numberOfBathrooms, Integer numberOfRooms,
         Integer numberOfLevel, Integer yearsOfConstruction, String dimensionsBuilding,
         Integer livingSpaceAreaSquareMeter, Integer municipalValuation, String backyardFaces) {

      this.numberOfBedRooms = numberOfBedRooms;
      this.numberOfBathrooms = numberOfBathrooms;
      this.numberOfRooms = numberOfRooms;
      this.numberOfLevel = numberOfLevel;
      this.yearOfConstruction = yearsOfConstruction;
      this.buildingDimension = dimensionsBuilding;
      this.livingSpaceAreaSquareMeter = livingSpaceAreaSquareMeter;
      this.municipalAssessment = municipalValuation;
      this.backyardOrientation = backyardFaces;
   }

   public DescriptionDto() {

   }

   public Integer getNumberOfBedRooms() {
      return numberOfBedRooms;
   }

   public void setNumberOfBedRooms(Integer numberOfBedRooms) {
      this.numberOfBedRooms = numberOfBedRooms;
   }

   public Integer getNumberOfBathrooms() {
      return numberOfBathrooms;
   }

   public void setNumberOfBathrooms(Integer numberOfBathrooms) {
      this.numberOfBathrooms = numberOfBathrooms;
   }

   public Integer getNumberOfRooms() {
      return numberOfRooms;
   }

   public void setNumberOfRooms(Integer numberOfRooms) {
      this.numberOfRooms = numberOfRooms;
   }

   public Integer getNumberOfLevel() {
      return numberOfLevel;
   }

   public void setNumberOfLevel(Integer numberOfLevel) {
      this.numberOfLevel = numberOfLevel;
   }

   public Integer getYearOfConstruction() {
      return yearOfConstruction;
   }

   public void setYearsOfConstruction(Integer yearsOfConstruction) {
      this.setYearOfConstruction(yearsOfConstruction);
   }

   public String getBuildingDimension() {
      return buildingDimension;
   }

   public void setBuildingDimension(String dimensionsBuilding) {
      this.buildingDimension = dimensionsBuilding;
   }

   public Integer getLivingSpaceAreaSquareMeter() {
      return livingSpaceAreaSquareMeter;
   }

   public void setLivingSpaceAreaSquareMeter(Integer livingSpaceAreaSquareMeter) {
      this.livingSpaceAreaSquareMeter = livingSpaceAreaSquareMeter;
   }

   public Integer getMunicipalAssessment() {
      return municipalAssessment;
   }

   public void setMunicipalAssessment(Integer municipalValuation) {
      this.municipalAssessment = municipalValuation;
   }

   public String getBackyardOrientation() {
      return backyardOrientation;
   }

   public void setBackyardOrientation(String backyardFaces) {
      this.backyardOrientation = backyardFaces;
   }

   public void setYearOfConstruction(Integer yearOfConstruction) {
      this.yearOfConstruction = yearOfConstruction;
   }
}
