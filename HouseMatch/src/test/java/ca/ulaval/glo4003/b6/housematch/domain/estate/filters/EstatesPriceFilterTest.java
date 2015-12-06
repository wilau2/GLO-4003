package ca.ulaval.glo4003.b6.housematch.domain.estate.filters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.domain.estate.Estate;

public class EstatesPriceFilterTest {

   private static final int LOWER_PRICE = 999;

   private static final int AVERAGE_PRICE = 5000;

   private static final int HIGH_PRICE = 11000;

   private static final int MIN_PRICE = 1000;

   private static final int MAX_PRICE = 10000;

   @Mock
   private Estate estateWithMinimumPrice;

   @Mock
   private Estate estateWithAveragePrice;

   @Mock
   private Estate estateWithMaximumPrice;

   private List<Estate> estates;

   private EstatesPriceFilter estatePriceFilter;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);

      estatePriceFilter = new EstatesPriceFilter();

      configureEstates();
   }

   private void configureEstates() {
      when(estateWithMinimumPrice.getPrice()).thenReturn(LOWER_PRICE);
      when(estateWithAveragePrice.getPrice()).thenReturn(AVERAGE_PRICE);
      when(estateWithMaximumPrice.getPrice()).thenReturn(HIGH_PRICE);

      estates = new ArrayList<Estate>();

      estates.add(estateWithMinimumPrice);
      estates.add(estateWithAveragePrice);
      estates.add(estateWithMaximumPrice);
   }

   @Test
   public void whenFilteringEstateBetweenMinAndMaxShouldReturnEstatesBetweenMinAnMax()
         throws InconsistentFilterParamaterException {
      // Given
      int expectedSize = 1;
      when(estateWithAveragePrice.isPriceBetween(MIN_PRICE, MAX_PRICE)).thenReturn(true);

      // When
      List<Estate> filteredEstates = estatePriceFilter.filter(estates, MIN_PRICE, MAX_PRICE);

      // Then
      assertTrue(filteredEstates.contains(estateWithAveragePrice));
      assertEquals(expectedSize, filteredEstates.size());
   }

   @Test
   public void whenFilteringWithMinPriceAboveEstateWithMinumPriceShouldNotIncludeIt()
         throws InconsistentFilterParamaterException {
      // Given no changes

      // When
      List<Estate> filteredEstates = estatePriceFilter.filter(estates, MIN_PRICE, MAX_PRICE);

      // Then
      assertFalse(filteredEstates.contains(estateWithMinimumPrice));
   }

   @Test
   public void whenFilteringWithMaxPriceBelowEstateWithMaximumShouldNotIncludeIt()
         throws InconsistentFilterParamaterException {
      // Given no changes

      // When
      List<Estate> filteredEstates = estatePriceFilter.filter(estates, MIN_PRICE, MAX_PRICE);

      // Then
      assertFalse(filteredEstates.contains(estateWithMaximumPrice));
   }

   @Test
   public void whenFilteringWithMinPriceEqualToPriceOfEstateWithMinimumPriceShouldIncludeIt()
         throws InconsistentFilterParamaterException {
      // Given
      when(estateWithMinimumPrice.isPriceBetween(LOWER_PRICE, MAX_PRICE)).thenReturn(true);

      // When
      List<Estate> filteredEstates = estatePriceFilter.filter(estates, LOWER_PRICE, MAX_PRICE);

      // Then
      assertTrue(filteredEstates.contains(estateWithMinimumPrice));
   }

   @Test(expected = InconsistentFilterParamaterException.class)
   public void whenFilterWithInconsistentParameterShouldThrowInconsistentFilterParamaterException()
         throws InconsistentFilterParamaterException {
      // Given no changes

      // When
      estatePriceFilter.filter(estates, MAX_PRICE, MIN_PRICE);

      // Then an inconsistent filter parameter exception is thrown
   }

}
