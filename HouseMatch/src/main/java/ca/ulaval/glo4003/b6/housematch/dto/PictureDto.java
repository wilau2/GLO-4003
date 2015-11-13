package ca.ulaval.glo4003.b6.housematch.dto;

public class PictureDto {

   private String url;

   private String name;

   public PictureDto(String url, String name) {
      this.url = url;
      this.name = name;
   }

   public String getUrl() {
      return this.url;
   }

   public String getName() {
      return this.name;
   }
}
