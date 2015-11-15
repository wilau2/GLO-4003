package ca.ulaval.glo4003.b6.housematch.dto;

import java.util.ArrayList;

public class EstateDto {

   private String type;

   private AddressDto address;

   private Integer price;

   private String sellerId;

   private DescriptionDto descriptionDto;

   private AlbumDto albumDto;
   
   private ArrayList<Integer> priceHistory;

   public EstateDto() {
      this.address = new AddressDto();
   }

   public EstateDto(String type, AddressDto address, Integer price, String sellerId, DescriptionDto descriptionDto,
         AlbumDto albumDto, ArrayList<Integer> priceHistory) {
      this.type = type;
      this.address = address;
      this.price = price;
      this.sellerId = sellerId;
      this.descriptionDto = descriptionDto;
      this.albumDto = albumDto;
      this.priceHistory = priceHistory;
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

   public Object getAlbumDto() {
      return this.albumDto;
   }
   
   public ArrayList<Integer> getPriceHistory() {
      return priceHistory;
   }

   public void setPriceHistory(ArrayList<Integer> priceHistory) {
      this.priceHistory = priceHistory;
   }

}
