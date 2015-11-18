package ca.ulaval.glo4003.b6.housematch.dto;

public class EstateEditDto {

   private String type;

   private Integer price;
   
   public EstateEditDto(){}

   public EstateEditDto(String type, Integer price) {
      this.type = type;
      this.price = price;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getType() {
      return this.type;
   }

   public void setPrice(Integer price) {
      this.price = price;
   }

   public Integer getPrice() {
      return this.price;
   }

}
