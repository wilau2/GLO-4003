package ca.ulaval.glo4003.b6.housematch.domain.estate.filters;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4003.b6.housematch.domain.estate.Estate;

public class EstatesPriceFilter implements EstateFilter {

   public List<Estate> filter(List<Estate> estates, int minValue, int maxValue)
         throws InconsistentFilterParamaterException {

      validateMaxValueIsBiggerThanMinValue(minValue, maxValue);

      List<Estate> filteredEstates = new ArrayList<Estate>();
      for (Estate estate : estates) {
         if (estate.isPriceBetween(minValue, maxValue)) {
            filteredEstates.add(estate);
         }
      }
      return filteredEstates;
   }

   private void validateMaxValueIsBiggerThanMinValue(int minValue, int maxValue)
         throws InconsistentFilterParamaterException {
      if (minValue > maxValue) {
         throw new InconsistentFilterParamaterException(
               "Minimum price should be lower than maximum price when filtering estates by price");
      }
   }
}
