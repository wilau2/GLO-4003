package ca.ulaval.glo4003.b6.housematch.estates.domain;

public class Estate {

   private Integer price;

   private String address;

   private String type;

   private String seller;
   
   private Description description;

   public Estate(String type, String address, Integer price, String seller, Description description) {
      this.type = type;
      this.address = address;
      this.price = price;
      this.seller = seller;
      this.description = description;
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
   
   public Description getDescription(){
      return description;
   }

   public void setDescription(Description description) {
      this.description = description;
   }
}
