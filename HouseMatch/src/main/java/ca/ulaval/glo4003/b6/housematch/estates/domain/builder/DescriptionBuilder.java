package ca.ulaval.glo4003.b6.housematch.estates.domain.builder;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Description;

public class DescriptionBuilder {
   
   private Integer newNumberOfBedRooms;
   private Integer newNumberOfBathrooms;
   private Integer newNumberOfRooms;
   private Integer newNumberOfLevel;
   private Integer newYearsOfConstruction;
   private String newBuildingDimensions;
   private Integer newLivingSpaceAreaSquareMeter;
   private Integer newMunicipalValuation;
   private String newBackyardOrientation;
   
   public DescriptionBuilder setNewNumberOfBedRooms(Integer newNumberOfBedRooms) {
      this.newNumberOfBedRooms = newNumberOfBedRooms;
      return this;
   }

   
   public DescriptionBuilder setNewNumberOfBathrooms(Integer newNumberOfBathrooms) {
      this.newNumberOfBathrooms = newNumberOfBathrooms;
      return this;
   }

   
   public DescriptionBuilder setNewNumberOfRooms(Integer newNumberOfRooms) {
      this.newNumberOfRooms = newNumberOfRooms;
      return this;
   }

   
   public DescriptionBuilder setNewNumberOfLevel(Integer newNumberOfLevel) {
      this.newNumberOfLevel = newNumberOfLevel;
      return this;
   }

   
   public DescriptionBuilder setNewYearsOfConstruction(Integer newYearsOfConstruction) {
      this.newYearsOfConstruction = newYearsOfConstruction;
      return this;
   }

   
   public DescriptionBuilder setNewDimensionsBuilding(String newDimensionsBuilding) {
      this.newBuildingDimensions = newDimensionsBuilding;
      return this;
   }

   
   public DescriptionBuilder setNewLivingSpaceAreaSquareMeter(Integer newLivingSpaceAreaSquareMeter) {
      this.newLivingSpaceAreaSquareMeter = newLivingSpaceAreaSquareMeter;
      return this;
   }

   
   public DescriptionBuilder setNewMunicipalValuation(Integer newMunicipalValuation) {
      this.newMunicipalValuation = newMunicipalValuation;
      return this;
   }

   
   public DescriptionBuilder setNewBackyardOrientation(String newBackyardOrientation) {
      this.newBackyardOrientation = newBackyardOrientation;
      return this;
   }
   
   public Description buildDescription(){
      return new Description(newNumberOfBedRooms, newNumberOfBathrooms, newNumberOfRooms, newNumberOfLevel, newYearsOfConstruction, newBuildingDimensions, newLivingSpaceAreaSquareMeter, newMunicipalValuation, newBackyardOrientation);
   }
   
}
