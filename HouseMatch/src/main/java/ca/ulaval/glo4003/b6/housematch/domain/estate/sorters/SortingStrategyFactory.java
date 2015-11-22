package ca.ulaval.glo4003.b6.housematch.domain.estate.sorters;


public class SortingStrategyFactory {

   private static final String DATE_ASCENDANT = "dateAscendant";
   private static final String DATE_DESCENDANT = "dateDescendant";
   private static final String DATE_MODIFIED_ASCENDANT = "dateModifiedAscendant";
   private static final String DATE_MODIFIED_DESCENDANT = "dateModifiedDescendant";
   private static final String PRICE_ASCENDANT = "priceAscendant";
   private static final String PRICE_DESCENDANT = "priceDescendant";

   public SortingStrategy getStrategy(String strategyName) {
      
      if(strategyName == null || strategyName.equals(DATE_ASCENDANT)){
         return new DateAscendantSortingStrategy();
      }
      
      if(strategyName.equals(DATE_DESCENDANT)){
         return new DateDescendantSortingStrategy();
      }
      
      if(strategyName.equals(DATE_MODIFIED_ASCENDANT)){
         return new DateModifiedAscendantSortingStrategy();
      }
      
      if(strategyName.equals(DATE_MODIFIED_DESCENDANT)){
         return new DateModifiedDescendantSortingStrategy();
      }
      
      if(strategyName.equals(PRICE_ASCENDANT)){
         return new PriceAscendantSortingStrategy();
      }
      
      else{
         return new PriceDescendantSortingStrategy();
      }
   }

}
