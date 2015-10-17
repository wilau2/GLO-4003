package ca.ulaval.glo4003.b6.housematch.estates.dto;

public class EstateDto {

   private String type;
   private AddressDto address;
   private Integer price;
   private String sellerId;
   private DescriptionDto descriptionDto;

   public EstateDto() {
      this.address = new AddressDto();
   }

   public EstateDto(String type, AddressDto address, Integer price, String sellerId) {
      this.type = type;
      this.address = address;
      this.price = price;
      this.sellerId = sellerId;
   }
   
   public EstateDto(String type, AddressDto address, Integer price, String sellerId, DescriptionDto descriptionDto) {
      this.type = type;
      this.address = address;
      this.price = price;
      this.sellerId = sellerId;
      this.descriptionDto = descriptionDto;
   }
   
   public void setDescriptionDto(DescriptionDto descriptionDto){
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

}
