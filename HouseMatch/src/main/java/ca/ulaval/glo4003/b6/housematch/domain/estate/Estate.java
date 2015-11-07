package ca.ulaval.glo4003.b6.housematch.domain.estate;

import java.util.Date;

public class Estate {

   private Integer price;

   private Address address;

   private String type;

   private String seller;

   private Description description;

   private Date dateRegistered;

   public Estate(String type, Address address, Integer price, String seller, Description description, Date dateRegistered) {

      this.type = type;
      this.address = address;
      this.price = price;
      this.seller = seller;
      this.description = description;
      this.dateRegistered = dateRegistered;
   }

   public String getType() {
      return this.type;
   }

   public Address getAddress() {
      return this.address;
   }

   public Integer getPrice() {
      return this.price;
   }

   public String getSeller() {
      return seller;
   }

   public Description getDescription() {
      return description;
   }

   public boolean isFromSeller(String sellerName) {
      if (seller.equals(sellerName)) {
         return true;
      }
      return false;
   }

   public Date getDateRegistered() {
      return this.dateRegistered;
   }
}
