package ca.ulaval.glo4003.b6.housematch.dto;

public class PictureDto {

   private String url;

   public PictureDto(String url) {
      this.url = url;
   }

   public void setUrl(String url) {
      this.url = url;
   }

   public String getUrl() {
      return this.url;
   }
}
