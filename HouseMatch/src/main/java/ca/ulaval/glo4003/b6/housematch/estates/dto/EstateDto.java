package ca.ulaval.glo4003.b6.housematch.estates.dto;

public class EstateDto {

   private String type;

   private String address;

   private Integer price;

   public EstateDto() {
   }

   public EstateDto(String type, String address, Integer price) {
      this.type = type;
      this.address = address;
      this.price = price;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getType() {
      return this.type;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getAddress() {
      return this.address;
   }

   public void setPrice(Integer price) {
      this.price = price;
   }

   public Integer getPrice() {
      return this.price;
   }

}
