package ca.ulaval.glo4003.b6.housematch.estates.dto;


public class AddressDto {
   
   private Integer appartment; 
   private Integer civicNumber;  
   private String street;  
   private String postalCode;  
   private String province;  
   private String country;

   public AddressDto() {
   } 
   
   public AddressDto(Integer appartment, Integer civicNumber, String street, String postalCode,
         String province, String country) {
      this.appartment = appartment;
      this.civicNumber = civicNumber;
      this.street = street;
      this.postalCode = postalCode;
      this.province = province;
      this.country = country;
   }

   public Integer getAppartment() {
      return appartment;
   }

   public void setAppartment(Integer appartment) {
      this.appartment = appartment;
   }

   public Integer getCivicNumber() {
      return civicNumber;
   }

   public void setCivicNumber(Integer civicNumber) {
      this.civicNumber = civicNumber;
   }

   public String getStreet() {
      return street;
   }

   public void setStreet(String street) {
      this.street = street;
   }

   public String getPostalCode() {
      return postalCode;
   }

   public void setPostalCode(String postalCode) {
      this.postalCode = postalCode;
   }

   public String getProvince() {
      return province;
   }

   public void setProvince(String province) {
      this.province = province;
   }

   public String getCountry() {
      return country;
   }

   public void setCountry(String country) {
      this.country = country;
   }

}
