package ca.ulaval.glo4003.b6.housematch.domain.estate;

import java.util.Collections;
import java.util.List;

import ca.ulaval.glo4003.b6.housematch.domain.estate.sorters.EstatesSortingStrategy;

public class Estates {

   private List<Estate> estates;

   public Estates(List<Estate> estates) {
      this.estates = estates;
   }

   public void sortByHighestToLowestPrice() {
      Collections.sort(estates, Estate.EstatePriceDescendantComparator);
   }

   public void sortByLowestToHighestPrice() {
      Collections.sort(estates, Estate.EstatePriceAscendantComparator);
   }

   public void sortByNewestToOldestDate() {
      Collections.sort(estates, Estate.EstateDateDescendantComparator);
   }

   public void sortByOldestToNewestDate() {
      Collections.sort(estates, Estate.EstateDateAscendantComparator);
   }

   public List<Estate> retreiveListOfEstate() {
      return this.estates;
   }

   public void updateEstatesList(List<Estate> listEstates) {
      estates = listEstates;
   }

   public int retreiveNumberOfEstates() {
      return this.estates.size();
   }

   public void sortByStrategy(EstatesSortingStrategy estateSortingStrategy) {
      estates = estateSortingStrategy.sort(estates);
   }

   public void filterEstates(EstateFilter estateFilter, int minPrice, int maxPrice)
         throws InconsistentFilterParamaterException {
      estates = estateFilter.filter(estates, minPrice, maxPrice);
   }

}
