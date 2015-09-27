package ca.ulaval.glo4003.b6.housematch.web.viewModel;

public class EstateModel {

   private static final String COMMA = ", ";

   private static final String UNION = "-";

   private String type;

   private Integer price;

   private String seller;

   private String postalCode;

   private Integer civicNumber;

   private String country;

   private String state;

   private String street;

   private int appartment;

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
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

   public int getAppartment() {
      return appartment;
   }

   public void setAppartment(int appartment) {
      this.appartment = appartment;
   }

   public String addressToString() {

      String formattedAddress = appartment + COMMA + civicNumber + COMMA + street + COMMA + state + COMMA + country
                                + COMMA + postalCode;
      return formattedAddress;
   }

   public String addressToUrl() {
      String formattedAddress = appartment + UNION + civicNumber + UNION + street + UNION + state + UNION + country
                                + UNION + postalCode;
      return formattedAddress;
   }

}
