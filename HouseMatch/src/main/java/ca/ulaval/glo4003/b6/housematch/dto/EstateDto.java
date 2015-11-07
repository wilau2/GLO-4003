package ca.ulaval.glo4003.b6.housematch.dto;

import java.util.Date;

public class EstateDto {

   private String type;

   private AddressDto address;

   private Integer price;

   private String sellerId;

   private DescriptionDto descriptionDto;
   
   private Date dateRegistered;

   public EstateDto() {
      this.address = new AddressDto();
   }

   public EstateDto(String type, AddressDto address, Integer price, String sellerId, Date dateRegistered) {
      this.type = type;
      this.address = address;
      this.price = price;
      this.sellerId = sellerId;
      this.dateRegistered = dateRegistered;
   }

   public EstateDto(String type, AddressDto address, Integer price, String sellerId, Date dateRegistered, DescriptionDto descriptionDto) {
      this.type = type;
      this.address = address;
      this.price = price;
      this.sellerId = sellerId;
      this.dateRegistered = dateRegistered;
      this.descriptionDto = descriptionDto;
   }

   public void setDescriptionDto(DescriptionDto descriptionDto) {
      this.descriptionDto = descriptionDto;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getType() {
      return this.type;
   }

   public void setAddress(AddressDto address) {
      this.address = address;
   }

   public AddressDto getAddress() {
      return this.address;
   }

   public void setPrice(Integer price) {
      this.price = price;
   }

   public Integer getPrice() {
      return this.price;
   }

   public String getSeller() {
      return sellerId;
   }

   public void setSellerId(String sellerId) {
      this.sellerId = sellerId;
   }

   public DescriptionDto getDescriptionDto() {
      return this.descriptionDto;
   }

   public void setDateRegistered(Date dateRegistered) {
      this.dateRegistered = dateRegistered;
      
   }

   public Date getDateRegistered() {
      return this.dateRegistered;
   }

}
