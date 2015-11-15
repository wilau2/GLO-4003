package ca.ulaval.glo4003.b6.housematch.domain.estate;

public class Picture {

   private String url;

   private String name;

   public Picture(String url, String name) {
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
