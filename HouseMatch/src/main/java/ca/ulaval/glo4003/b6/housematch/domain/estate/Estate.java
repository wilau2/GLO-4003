package ca.ulaval.glo4003.b6.housematch.domain.estate;

import java.util.Comparator;

public class Estate{

   private Integer price;

   private Address address;

   private String type;

   private String seller;

   private Description description;

   public Estate(String type, Address address, Integer price, String seller, Description description) {

      this.type = type;
      this.address = address;
      this.price = price;
      this.seller = seller;
      this.description = description;
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
   
   public static Comparator<Estate> EstatePriceDescendantComparator = new Comparator<Estate>(){
      public int compare(Estate estate1, Estate estate2) {
         return estate2.getPrice().compareTo(estate1.getPrice());
      }
   };
   
   public static Comparator<Estate> EstatePriceAscendantComparator = new Comparator<Estate>(){
      public int compare(Estate estate1, Estate estate2) {
         return estate1.getPrice().compareTo(estate2.getPrice());
      }
   };
   
   //IL FAUT AJOUTER ICI LES COMPARATOR POUR DATE
 
}
