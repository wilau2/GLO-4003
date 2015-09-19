package ca.ulaval.glo4003.b6.housematch.estates.domain;

public class Estate {

   private Integer price;

   private String address;

   private String type;

   public Estate(String type, String address, Integer price) {
      this.type = type;
      this.address = address;
      this.price = price;
   }

   public String getType() {
      return this.type;
   }

   public String getAddress() {
      return this.address;
   }

   public Integer getPrice() {
      return this.price;
   }

}
