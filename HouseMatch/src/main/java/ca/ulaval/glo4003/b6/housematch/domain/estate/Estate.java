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

   private LocalDateTime dateModified;

   private ArrayList<Integer> priceHistory;

   private boolean bought;

   private LocalDateTime dateOfPurchase;

   public Estate(String type, Address address, Integer price, String seller, Description description,
         LocalDateTime dateRegistered, ArrayList<Integer> priceHistory, boolean bought, LocalDateTime dateOfPurchase,
         LocalDateTime dateModified) {

      this.type = type;
      this.address = address;
      this.price = price;
      this.seller = seller;
      this.description = description;
      this.dateRegistered = dateRegistered;
      this.dateModified = dateModified;
      this.priceHistory = priceHistory;
      this.bought = bought;
      this.dateOfPurchase = dateOfPurchase;
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
      if (this.description.isChangeSignificant(description)) {
         this.udateModifiedDate();
      }
      this.description = description;
   }

   public LocalDateTime getDateRegistered() {
      return dateRegistered;
   }

   public LocalDateTime getDateModified() {
      return this.dateModified;
   }

   public static Comparator<Estate> EstatePriceAscendantComparator = new Comparator<Estate>() {

      public int compare(Estate estate1, Estate estate2) {
         return estate1.getPrice().compareTo(estate2.getPrice());
      }
   };

   public static Comparator<Estate> EstateDateAscendantComparator = new Comparator<Estate>() {

      public int compare(Estate estate1, Estate estate2) {
         return estate1.getDateRegistered().compareTo(estate2.getDateRegistered());
      }
   };

   public static Comparator<Estate> EstateDateModifiedAscendantComparator = new Comparator<Estate>() {

      public int compare(Estate estate1, Estate estate2) {
         return estate1.getDateModified().compareTo(estate2.getDateModified());
      }
   };

   public void editType(String newType) {
      this.type = newType;
      udateModifiedDate();
   }

   public void editPrice(Integer newPrice) {
      if (this.price.intValue() != newPrice.intValue()) {
         this.priceHistory.add(this.price);
         this.price = newPrice;
         udateModifiedDate();
      }
   }

   public void udateModifiedDate() {
      dateModified = LocalDateTime.now();
   }

   public void buy() throws EstateAlreadyBoughtException {
      if (hasBeenBought()) {
         throw new EstateAlreadyBoughtException("This estates already has a new familly, sorry :(");
      }
      bought = true;
      dateOfPurchase = LocalDateTime.now();
   }

   public Boolean hasBeenBought() {
      return bought;
   }

   public LocalDateTime getDateOfPurchase() {
      return dateOfPurchase;
   }

   public boolean hasBeenBoughtInLastYear() {
      if (hasBeenBought()) {
         LocalDateTime lastYearDate = LocalDateTime.now().minusYears(1);

         return dateOfPurchase.isAfter(lastYearDate);
      }
      return false;
   }

   public boolean isPriceBetween(int minValue, int maxValue) {
      if (price.intValue() >= minValue && price.intValue() <= maxValue) {
         return true;
      }
      return false;
   }
}
