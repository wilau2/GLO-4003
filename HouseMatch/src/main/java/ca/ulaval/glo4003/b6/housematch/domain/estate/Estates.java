package ca.ulaval.glo4003.b6.housematch.domain.estate;

import java.util.Collections;
import java.util.List;

public class Estates {

   private List<Estate> estates;

   public Estates(List<Estate> estates) {
      this.estates = estates;
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

   public void updateEstatesList(List<Estate> listEstates) {
      estates = listEstates;
   }

   public int size() {
      return this.estates.size();
   }

}
