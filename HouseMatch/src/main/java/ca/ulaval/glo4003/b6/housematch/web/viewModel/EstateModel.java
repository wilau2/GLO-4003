package ca.ulaval.glo4003.b6.housematch.web.viewModel;

public class EstateModel {

   private String type;

   private String address;

   private Integer price;

   private String seller;

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public Integer getPrice() {
      return price;
   }

   public void setPrice(Integer price) {
      this.price = price;
   }

   public String getSeller() {
      return seller;
   }

   public void setSeller(String seller) {
      this.seller = seller;
   }

}
