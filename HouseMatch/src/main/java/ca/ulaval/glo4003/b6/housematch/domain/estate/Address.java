package ca.ulaval.glo4003.b6.housematch.domain.estate;

public class Address {

   private static final String UNION = "-";

   private Integer appartment;

   private Integer civicNumber;

   private String street;

   private String postalCode;

   private String province;

   private String country;

   public Address(Integer appartment, Integer civiNumber, String street, String postalCode, String province,
         String country) {
      this.appartment = appartment;
      this.civicNumber = civiNumber;
      this.street = street;
      this.postalCode = postalCode;
      this.province = province;
      this.country = country;

   }

   public Integer getAppartment() {
      return appartment;
   }

   public Integer getCivicNumber() {
      return civicNumber;
   }

   public String getStreet() {
      return street;
   }

   public String getPostalCode() {
      return postalCode;
   }

   public String getState() {
      return province;
   }

   public String getCountry() {
      return country;
   }

   @Override
   public String toString() {
      String formattedAddress = appartment.toString() + UNION + civicNumber.toString() + UNION + street + UNION
                                + province + UNION + country + UNION + postalCode;
      return formattedAddress;
   }
}
