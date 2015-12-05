package ca.ulaval.glo4003.b6.housematch.services.estate;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.b6.housematch.domain.estate.sorters.DateModifiedSortingStrategy;
import ca.ulaval.glo4003.b6.housematch.domain.estate.sorters.DateRegistredSortingStrategy;
import ca.ulaval.glo4003.b6.housematch.domain.estate.sorters.DefaultSortingStrategy;
import ca.ulaval.glo4003.b6.housematch.domain.estate.sorters.EstatesSortingStrategy;
import ca.ulaval.glo4003.b6.housematch.domain.estate.sorters.PriceSortingStrategy;
import ca.ulaval.glo4003.b6.housematch.services.estate.SortingStrategyFactory;

public class SortingStrategyFactoryTest {

   private static final String DATE_MODIFIED = "dateModified";

   private static final String DATE_REGISTRED = "dateRegistred";

   private static final String PRICE = "price";

   private SortingStrategyFactory sortingStrategyFactory;

   @Before
   public void setUp() {
      sortingStrategyFactory = new SortingStrategyFactory();
   }

   @Test
   public void whenAskingForDateRegistredSortingStrategyShouldReturnInstanceOfStrategy() {
      // Given no changes

      // When
      EstatesSortingStrategy estatesSortingStrategy = sortingStrategyFactory.getStrategy(DATE_REGISTRED);

      // Then
      assertTrue(estatesSortingStrategy instanceof DateRegistredSortingStrategy);
   }

   @Test
   public void whenAskingForDateModifiedSortingStrategyShouldReturnInstanceOfStrategy() {
      // Given no changes

      // When
      EstatesSortingStrategy sortingStrategy = sortingStrategyFactory.getStrategy(DATE_MODIFIED);

      // Then
      assertTrue(sortingStrategy instanceof DateModifiedSortingStrategy);
   }

   @Test
   public void whenAskingForPriceSortingStrategyShouldReturnInstanceOfStrategy() {
      // Given no changes

      // When
      EstatesSortingStrategy sortingStrategy = sortingStrategyFactory.getStrategy(PRICE);

      // Then
      assertTrue(sortingStrategy instanceof PriceSortingStrategy);
   }

   @Test
   public void whenPassingDefaultToStrategyFactoryShouldReturnDefaultStrategy() {
      // Given

      // When
      EstatesSortingStrategy strategy = sortingStrategyFactory.getStrategy("default");

      // Then
      assertTrue(strategy instanceof DefaultSortingStrategy);
   }

   @Test
   public void whenPassingNullToStrategyFactoryShouldReturnDefaultStrategy() {
      // Given

      // When
      EstatesSortingStrategy strategy = sortingStrategyFactory.getStrategy(null);

      // Then
      assertTrue(strategy instanceof DefaultSortingStrategy);
   }
}
