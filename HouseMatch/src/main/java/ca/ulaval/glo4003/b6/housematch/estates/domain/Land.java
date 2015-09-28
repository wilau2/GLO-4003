package ca.ulaval.glo4003.b6.housematch.estates.domain;


public class Land {
   
   private String aqueduct; 
   private String dimensionsLot;

   public Land(String aqueduct, String dimensionLot){
      this.aqueduct = aqueduct;
      this.dimensionsLot = dimensionLot;
   }

   public String getAqueduct() {
      return aqueduct;
   }

   
   public String getDimensionsLot() {
      return dimensionsLot;
   }
}
