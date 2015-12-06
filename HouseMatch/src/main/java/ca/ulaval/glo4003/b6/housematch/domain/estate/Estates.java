package ca.ulaval.glo4003.b6.housematch.domain.estate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ca.ulaval.glo4003.b6.housematch.domain.estate.filters.EstateFilter;
import ca.ulaval.glo4003.b6.housematch.domain.estate.filters.InconsistentFilterParamaterException;
import ca.ulaval.glo4003.b6.housematch.domain.estate.sorters.EstatesSortingStrategy;

public class Estates {

   private List<Estate> estates;

   private List<Estate> shownEstates;

   public Estates(List<Estate> estates) {
      this.estates = estates;
      copyEstatesToShownEstates(estates);
   }

   private void copyEstatesToShownEstates(List<Estate> estates) {
      shownEstates = new ArrayList<Estate>(estates);
   }

   public List<Estate> retreiveListOfEstate() {
      return shownEstates;
   }

   public void updateEstatesList(List<Estate> listEstates) {
      estates = listEstates;
      copyEstatesToShownEstates(listEstates);
   }

   public int retreiveNumberOfEstates() {
      return this.estates.size();
   }

   public void sortByStrategy(EstatesSortingStrategy estateSortingStrategy) {
      estateSortingStrategy.sort(shownEstates);
   }

   public void filterEstates(EstateFilter estateFilter, int minValue, int maxValue)
         throws InconsistentFilterParamaterException {
      shownEstates = estateFilter.filter(shownEstates, minValue, maxValue);
   }

   public void reverseShownEstates() {
      Collections.reverse(shownEstates);
   }

}
