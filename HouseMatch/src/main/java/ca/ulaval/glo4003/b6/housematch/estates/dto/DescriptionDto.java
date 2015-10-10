package ca.ulaval.glo4003.b6.housematch.estates.dto;

import java.util.List;

public class DescriptionDto {

   private Integer numberOfBedRooms;
   private Integer numberOfBathrooms;
   private Integer numberOfRooms;
   private Integer numberOfLevel;
   private Integer yearsOfConstruction;
   private String dimensionsBuilding;
   private Integer livingSpaceAreaSquareMeter;
   private Integer municipalValuation;
   private String backyardFaces;
   
   private List<RoomDto> roomsDto;
   private LandDto landDto;
   
   
   public DescriptionDto(Integer numberOfBedRooms, Integer numberOfBathrooms, Integer numberOfRooms,
         Integer numberOfLevel, Integer yearsOfConstruction, String dimensionsBuilding,
         Integer livingSpaceAreaSquareMeter, Integer municipalValuation, String backyardFaces) {
      
      this.numberOfBedRooms = numberOfBedRooms;
      this.numberOfBathrooms = numberOfBathrooms;
      this.numberOfRooms = numberOfRooms;
      this.numberOfLevel = numberOfLevel;
      this.yearsOfConstruction = yearsOfConstruction;
      this.dimensionsBuilding = dimensionsBuilding;
      this.livingSpaceAreaSquareMeter = livingSpaceAreaSquareMeter;
      this.municipalValuation = municipalValuation;
      this.backyardFaces = backyardFaces;
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
   public List<RoomDto> getRoomsDto() {
      return roomsDto;
   }
   public void setRoomsDto(List<RoomDto> roomsDto) {
      this.roomsDto = roomsDto;
   }
   public LandDto getLandDto() {
      return landDto;
   }
   public void setLandDto(LandDto landDto) {
      this.landDto = landDto;
   }
}
