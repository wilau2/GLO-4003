package ca.ulaval.glo4003.b6.housematch.services.estate;

import com.google.common.base.Strings;

import ca.ulaval.glo4003.b6.housematch.domain.estate.filters.EstateFilter;
import ca.ulaval.glo4003.b6.housematch.domain.estate.filters.EstatesPriceFilter;
import ca.ulaval.glo4003.b6.housematch.services.estate.exceptions.WrongFilterTypeException;

class EstateFilterFactory {

   private static final String PRICE_FILTER = "PRICE";

   public EstateFilter getFilter(String filter) throws WrongFilterTypeException {
      verifyValidityOfFilter(filter);
      if (filter.equals(PRICE_FILTER)) {
         return new EstatesPriceFilter();
      } else {
         throw new WrongFilterTypeException("Wrong parameter type pass to the factory : " + filter);
      }
   }

   private void verifyValidityOfFilter(String filter) throws WrongFilterTypeException {
      if (Strings.isNullOrEmpty(filter)) {
         throw new WrongFilterTypeException("Filter type is empty");
      }
   }
}
