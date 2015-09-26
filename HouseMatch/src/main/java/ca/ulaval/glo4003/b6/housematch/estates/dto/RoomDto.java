package ca.ulaval.glo4003.b6.housematch.estates.dto;


public class RoomDto {
   
   private String type;   
   private Integer floor;
   private String dimensions;   
   private String surface;
   
   public RoomDto(String type, Integer floor, String dimensions, String surface) {
      this.type = type;
      this.floor = floor;
      this.dimensions = dimensions;
      this.surface = surface;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public Integer getFloor() {
      return floor;
   }

   public void setFloor(Integer floor) {
      this.floor = floor;
   }

   public String getDimensions() {
      return dimensions;
   }

   public void setDimensions(String dimensions) {
      this.dimensions = dimensions;
   }

   public String getSurface() {
      return surface;
   }

   public void setSurface(String surface) {
      this.surface = surface;
   }

}
