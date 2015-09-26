package ca.ulaval.glo4003.b6.housematch.web.viewModel;

public class EstateModel {

   private String type;

   private String address;

   private Integer price;

   private String seller;

   private String postalCode;

   private Integer civicNumber;

   private String country;

   private String state;

   private String street;

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

   public String getPostalCode() {
      return postalCode;
   }

   public void setPostalCode(String postalCode) {
      this.postalCode = postalCode;
   }

   public Integer getCivicNumber() {
      return civicNumber;
   }

   public void setCivicNumber(Integer civicNumber) {
      this.civicNumber = civicNumber;
   }

   public String getCountry() {
      return country;
   }

   public void setCountry(String country) {
      this.country = country;
   }

   public String getState() {
      return state;
   }

   public void setState(String state) {
      this.state = state;
   }

   public String getStreet() {

      return street;
   }

   public void setStreet(String street) {
      this.street = street;
   }

}
