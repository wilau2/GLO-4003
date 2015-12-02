package ca.ulaval.glo4003.b6.housematch.domain.estate;

import java.util.Collections;
import java.util.List;

public class Estates {

   private List<Estate> estates;

   public Estates(List<Estate> estates) {
      this.estates = estates;
   }

   public void filterEstatesBySellerName(String sellerName) {
      for (Estate estate : estates) {
         if (isNotFromSeller(sellerName, estate)) {
            estates.remove(estate);
         }
      }
   }

   public void sortByPriceDescendantSort() {
      Collections.sort(estates, Estate.EstatePriceDescendantComparator);
   }

   public void sortByPriceAscendantSort() {
      Collections.sort(estates, Estate.EstatePriceAscendantComparator);
   }

   public void sortByDateDescendantSort() {
      Collections.sort(estates, Estate.EstateDateDescendantComparator);
   }

   public void sortByDateAscendantSort() {
      Collections.sort(estates, Estate.EstateDateAscendantComparator);
   }

   public List<Estate> retreiveListOfEstate() {
      return this.estates;
   }

   private boolean isNotFromSeller(String sellerName, Estate estate) {
      return !estate.isFromSeller(sellerName);
   }

}
