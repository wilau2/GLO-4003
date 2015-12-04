package ca.ulaval.glo4003.b6.housematch.domain.estate.filters;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4003.b6.housematch.domain.estate.Estate;

public class EstatePriceFilter implements EstateFilter {

   public List<Estate> filter(List<Estate> estates, int minPrice, int maxPrice)
         throws InconsistentFilterParamaterException {

      validateParameter(minPrice, maxPrice);

      List<Estate> filteresEstates = new ArrayList<Estate>();
      List<Estate> unFilteredEstates = estates;
      for (int i = 0; i < unFilteredEstates.size(); i++) {
         if (unFilteredEstates.get(i).getPrice() > minPrice && unFilteredEstates.get(i).getPrice() < maxPrice) {
            filteresEstates.add(unFilteredEstates.get(i));
         }
      }
      return filteresEstates;
   }

   private void validateParameter(int minPrice, int maxPrice) throws InconsistentFilterParamaterException {
      if (minPrice > maxPrice) {
         throw new InconsistentFilterParamaterException("minimum price should be lower then maximum price");
      }

   }

}
