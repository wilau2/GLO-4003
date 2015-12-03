package ca.ulaval.glo4003.b6.housematch.dto;

public class PictureDto {

   private String name;

   private String isActive;

   public PictureDto(String name) {
      this.name = name;
      this.isActive = "true";
   }

   public String getName() {
      return this.name;
   }

   public void setActive(String active) {
      this.isActive = active;
   }

   public String getActive() {
      return this.isActive;
   }
}
