package ca.ulaval.glo4003.b6.housematch.domain.estate.sorters;

import java.util.Collections;
import java.util.List;

import ca.ulaval.glo4003.b6.housematch.domain.estate.Estate;

public class DateModifiedSortingStrategy implements EstatesSortingStrategy {

   @Override
   public void sort(List<Estate> estates) {
      Collections.sort(estates, Estate.EstateDateModifiedAscendantComparator);
   }
}
