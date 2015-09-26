package ca.ulaval.glo4003.b6.housematch.estates.dto;


public class LandDto {
   
   private String aqueduct; 
   private String dimensionsLot;
   
   public LandDto(String aqueduct, String dimensionsLot) {
      this.aqueduct = aqueduct;
      this.dimensionsLot = dimensionsLot;
   }
   
   public String getAqueduct() {
      return aqueduct;
   }
   public void setAqueduct(String aqueduct) {
      this.aqueduct = aqueduct;
   }
   public String getDimensionsLot() {
      return dimensionsLot;
   }
   public void setDimensionsLot(String dimensionsLot) {
      this.dimensionsLot = dimensionsLot;
   }

}
