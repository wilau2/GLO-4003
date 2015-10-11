package ca.ulaval.glo4003.b6.housematch.web.viewModel;

public class DescriptionModel {

   private Integer numberOfBedRooms;
   private Integer numberOfBathrooms;
   private Integer numberOfRooms;
   private Integer numberOfLevel;
   private Integer yearsOfConstruction;
   private String dimensionsBuilding;
   private Integer livingSpaceAreaSquareMeter;
   private Integer municipalValuation;
   private String backyardFaces;
   
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
   
   public Integer getYearsOfConstruction() {
      return yearsOfConstruction;
   }
   
   public void setYearsOfConstruction(Integer yearsOfConstruction) {
      this.yearsOfConstruction = yearsOfConstruction;
   }
   
   public String getDimensionsBuilding() {
      return dimensionsBuilding;
   }
   
   public void setDimensionsBuilding(String dimensionsBuilding) {
      this.dimensionsBuilding = dimensionsBuilding;
   }
   
   public Integer getLivingSpaceAreaSquareMeter() {
      return livingSpaceAreaSquareMeter;
   }
   
   public void setLivingSpaceAreaSquareMeter(Integer livingSpaceAreaSquareMeter) {
      this.livingSpaceAreaSquareMeter = livingSpaceAreaSquareMeter;
   }
   
   public Integer getMunicipalValuation() {
      return municipalValuation;
   }
   
   public void setMunicipalValuation(Integer municipalValuation) {
      this.municipalValuation = municipalValuation;
   }
   
   public String getBackyardFaces() {
      return backyardFaces;
   }
   
   public void setBackyardFaces(String backyardFaces) {
      this.backyardFaces = backyardFaces;
   }

}
