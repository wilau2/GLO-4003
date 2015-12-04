package ca.ulaval.glo4003.b6.housematch.domain.estate;

import ca.ulaval.glo4003.b6.housematch.domain.estate.filters.EstateFilter;
import ca.ulaval.glo4003.b6.housematch.domain.estate.filters.EstatePriceFilter;

public class EstateFilterFactory {

   private String PRICE_FILTER = "PRICE";

   public EstateFilter getFilter(String filter) throws WrongFilterTypeException {

      if (filter.equals(PRICE_FILTER)) {
         return new EstatePriceFilter();
      } else {
         throw new WrongFilterTypeException("Wrong parameter type pass to the factory : " + filter);
      }
   }

}
