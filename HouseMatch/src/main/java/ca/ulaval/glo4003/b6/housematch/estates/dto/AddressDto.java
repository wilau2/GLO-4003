package ca.ulaval.glo4003.b6.housematch.estates.dto;

public class AddressDto {

   private static final String COMMA = ", ";

   private static final String UNION = "-";

   private String street;

   private Integer civicNumber;

   private String postalCode;

   private String country;

   private String state;

   private Integer appartment;

   public AddressDto() {

   }

   public void setStreet(String street) {
      this.street = street;
   }

   public void setCivicNumber(Integer civicNumber) {
      this.civicNumber = civicNumber;
   }

   public void setPostalCode(String postalCode) {
      this.postalCode = postalCode;
   }

   public void setCountry(String country) {
      this.country = country;
   }

   public void setState(String state) {
      this.state = state;

   }

   public void setAppartment(int appartment) {
      this.appartment = appartment;
   }

   public String getCountry() {
      return country;
   }

   public Integer getCivicNumber() {
      return civicNumber;
   }

   public String getPostalCode() {
      return postalCode;
   }

   public String getStreet() {
      return street;
   }

   public Integer getAppartment() {
      return appartment;
   }

   public String getState() {
      return state;
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
