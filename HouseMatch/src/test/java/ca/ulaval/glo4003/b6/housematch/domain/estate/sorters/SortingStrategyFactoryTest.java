package ca.ulaval.glo4003.b6.housematch.domain.estate.sorters;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class SortingStrategyFactoryTest {

   private static final String DATE_ASCENDANT = "dateAscendant";

   private static final String DATE_DESCENDANT = "dateDescendant";

   private static final String DATE_MODIFIED_ASCENDANT = "dateModifiedAscendant";

   private static final String DATE_MODIFIED_DESCENDANT = "dateModifiedDescendant";

   private static final String PRICE_ASCENDANT = "priceAscendant";

   private static final String PRICE_DESCENDANT = "priceDescendant";

   private SortingStrategyFactory sortingStrategyFactory;

   @Before
   public void setUp() {
      sortingStrategyFactory = new SortingStrategyFactory();
   }

   @Test
   public void dateAscendantStringReturnsDateAscendantStrategy() {
      EstatesSortingStrategy sortingStrategy = sortingStrategyFactory.getStrategy(DATE_ASCENDANT);

      assertEquals(sortingStrategy.getClass(), DateAscendantSortingStrategy.class);
   }

   @Test
   public void dateDescendantStringReturnsDateDescendantStrategy() {
      EstatesSortingStrategy sortingStrategy = sortingStrategyFactory.getStrategy(DATE_DESCENDANT);

      assertEquals(sortingStrategy.getClass(), DateDescendantSortingStrategy.class);
   }

   @Test
   public void dateModifiedAscendantStringReturnsDateModifiedAscendantStrategy() {
      EstatesSortingStrategy sortingStrategy = sortingStrategyFactory.getStrategy(DATE_MODIFIED_ASCENDANT);

      assertEquals(sortingStrategy.getClass(), DateModifiedAscendantSortingStrategy.class);
   }

   @Test
   public void dateModifiedDescendantStringReturnsDateModifiedDescendantStrategy() {
      EstatesSortingStrategy sortingStrategy = sortingStrategyFactory.getStrategy(DATE_MODIFIED_DESCENDANT);

      assertEquals(sortingStrategy.getClass(), DateModifiedDescendantSortingStrategy.class);
   }

   @Test
   public void priceAscendantStringReturnsPriceAscendantStrategy() {
      EstatesSortingStrategy sortingStrategy = sortingStrategyFactory.getStrategy(PRICE_ASCENDANT);

      assertEquals(sortingStrategy.getClass(), PriceAscendantSortingStrategy.class);
   }

   @Test
   public void priceDescendantStringReturnsPriceDescendantStrategy() {
      EstatesSortingStrategy sortingStrategy = sortingStrategyFactory.getStrategy(PRICE_DESCENDANT);

      assertEquals(sortingStrategy.getClass(), PriceDescendantSortingStrategy.class);
   }
}
