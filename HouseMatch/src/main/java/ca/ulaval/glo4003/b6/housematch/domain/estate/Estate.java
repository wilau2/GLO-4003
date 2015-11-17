package ca.ulaval.glo4003.b6.housematch.domain.estate;

import java.util.ArrayList;

import ca.ulaval.glo4003.b6.housematch.domain.picture.Album;

public class Estate {

   private Integer price;

   private Address address;

   private String type;

   private String seller;

   private Description description;

   private Album album;
   
   private ArrayList<Integer> priceHistory;
   
   public Estate(String type, Address address, Integer price, String seller, Description description, ArrayList<Integer> priceHistory) {
      this.type = type;
      this.address = address;
      this.price = price;
      this.seller = seller;
      this.description = description;
      this.priceHistory = priceHistory;
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

   public Album getAlbum() {
      return this.album;
   }
   
   public ArrayList<Integer> getPriceHistory() {
      return this.priceHistory;
   }

   public boolean isFromSeller(String sellerName) {
      if (seller.equals(sellerName)) {
         return true;
      }
      return false;
   }

   public void editDescription(Description description) {
      this.description = description;
   }
   
   public void editType(String newType) {
      this.type = newType;
   }
   
   public void editPrice(Integer newPrice) {
      if(this.price != newPrice){
         this.priceHistory.add(this.price);
         this.price = newPrice;
      }
   }
}
