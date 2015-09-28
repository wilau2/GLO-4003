package ca.ulaval.glo4003.b6.housematch.estates.domain;


public class Room {

   private String type;   
   private Integer floor;
   private String dimensions;   
   private String surface;
   
   public Room(String type, Integer floor, String dimensions, String surface) {
     this.type = type;
     this.floor = floor; 
     this.dimensions = dimensions; 
     this.surface = surface; 
   }
   
   public String getType() {
      return type;
   }

   
   public Integer getFloor() {
      return floor;
   }

   
   public String getDimensions() {
      return dimensions;
   }

   
   public String getSurface() {
      return surface;
   }

}
