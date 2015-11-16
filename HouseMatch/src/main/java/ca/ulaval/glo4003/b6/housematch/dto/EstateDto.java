package ca.ulaval.glo4003.b6.housematch.dto;

import java.time.LocalDateTime;
import java.util.Date;

import ca.ulaval.glo4003.b6.housematch.domain.estate.Estate;

public class EstateDto {

   private String type;

   private AddressDto address;

   private Integer price;

   private String sellerId;

   private DescriptionDto descriptionDto;
   
   private LocalDateTime dateRegistered;

   public EstateDto() {
      this.address = new AddressDto();
   }

   public EstateDto(String type, AddressDto address, Integer price, String sellerId, LocalDateTime dateRegistered) {
      this.type = type;
      this.address = address;
      this.price = price;
      this.sellerId = sellerId;
      this.dateRegistered = dateRegistered;
   }

   public EstateDto(String type, AddressDto address, Integer price, String sellerId, LocalDateTime dateRegistered, DescriptionDto descriptionDto) {
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

   public void setDateRegistered(LocalDateTime localDateTime) {
      this.dateRegistered = localDateTime;
      
   }

   public LocalDateTime getDateRegistered() {
      return this.dateRegistered;
   }
   
   public boolean isEarlyer(EstateDto estate) {
      return this.dateRegistered.isBefore(estate.getDateRegistered());
   }

}
