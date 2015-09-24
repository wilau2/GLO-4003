package ca.ulaval.glo4003.b6.housematch.estates.domain;

public class Estate {

   private Integer price;

   private String address;

   private String type;

   private String seller;

   public Estate(String type, String address, Integer price, String seller) {
      this.type = type;
      this.address = address;
      this.price = price;
      this.seller = seller;
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

   public String getSeller() {
      return seller;
   }
}
