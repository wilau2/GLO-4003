package ca.ulaval.glo4003.b6.housematch.services.estate;

import com.google.common.base.Strings;

import ca.ulaval.glo4003.b6.housematch.domain.estate.sorters.DateModifiedSortingStrategy;
import ca.ulaval.glo4003.b6.housematch.domain.estate.sorters.DateRegistredSortingStrategy;
import ca.ulaval.glo4003.b6.housematch.domain.estate.sorters.DefaultSortingStrategy;
import ca.ulaval.glo4003.b6.housematch.domain.estate.sorters.EstatesSortingStrategy;
import ca.ulaval.glo4003.b6.housematch.domain.estate.sorters.PriceSortingStrategy;

class SortingStrategyFactory {

   private static final String DATE_REGISTRED = "dateRegistred";

   private static final String DATE_MODIFIED = "dateModified";

   private static final String PRICE = "price";

   EstatesSortingStrategy getStrategy(String strategyName) {
      if (Strings.isNullOrEmpty(strategyName)) {
         return new DefaultSortingStrategy();
      } else
         if (strategyName.equals(DATE_REGISTRED)) {
            return new DateRegistredSortingStrategy();

         } else
            if (strategyName.equals(DATE_MODIFIED)) {

               return new DateModifiedSortingStrategy();

            } else
               if (strategyName.equals(PRICE)) {
                  return new PriceSortingStrategy();

               } else {
                  return new DefaultSortingStrategy();
               }
   }

}
