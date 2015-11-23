package ca.ulaval.glo4003.b6.housematch.domain.estate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.EstateAlreadyBoughtException;

public class Estate {

   private Integer price;

   private Address address;

   private String type;

   private String seller;

   private Description description;

   private LocalDateTime dateRegistered;

   private ArrayList<Integer> priceHistory;

   private boolean bought;

   public Estate(String type, Address address, Integer price, String seller, Description description,
         LocalDateTime dateRegistered, ArrayList<Integer> priceHistory, boolean bought) {

      this.type = type;
      this.address = address;
      this.price = price;
      this.seller = seller;
      this.description = description;

      this.dateRegistered = dateRegistered;

      this.priceHistory = priceHistory;
      this.bought = bought;
   }

   public String getType() {
      return type;
   }

   public Address getAddress() {
      return address;
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

   public LocalDateTime getDateRegistered() {
      return dateRegistered;
   }

   public static Comparator<Estate> EstatePriceAscendantComparator = new Comparator<Estate>() {

      public int compare(Estate estate1, Estate estate2) {
         return estate1.getPrice().compareTo(estate2.getPrice());
      }
   };

   public static Comparator<Estate> EstatePriceDescendantComparator = new Comparator<Estate>() {

      public int compare(Estate estate1, Estate estate2) {
         return estate2.getPrice().compareTo(estate1.getPrice());
      }
   };

   public static Comparator<Estate> EstateDateAscendantComparator = new Comparator<Estate>() {

      public int compare(Estate estate1, Estate estate2) {
         return estate1.getDateRegistered().compareTo(estate2.getDateRegistered());
      }
   };

   public static Comparator<Estate> EstateDateDescendantComparator = new Comparator<Estate>() {

      public int compare(Estate estate1, Estate estate2) {
         return estate2.getDateRegistered().compareTo(estate1.getDateRegistered());
      }
   };

   public void editType(String newType) {
      this.type = newType;
   }

   public void editPrice(Integer newPrice) {
      if (this.price != newPrice) {
         this.priceHistory.add(this.price);
         this.price = newPrice;
      }
   }

   public void buy() throws EstateAlreadyBoughtException {
      if (hasBeenBought()) {
         throw new EstateAlreadyBoughtException("This estates already has a new familly, sorry :(");
      }
      bought = true;
   }

   public Boolean hasBeenBought() {
      return bought;
   }
}
