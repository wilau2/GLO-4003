package ca.ulaval.glo4003.b6.housematch.domain.estate.sorters;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.domain.estate.Estate;

public class PriceSortingStrategyTest {

   private static final int MIN_PRICE = 1;

   private static final int MID_PRICE = 50000;

   private static final int MAX_PRICE = 10000000;

   private List<Estate> estates;

   private PriceSortingStrategy priceSortingStrategy;

   @Mock
   private Estate estateMin;

   @Mock
   private Estate estateMid;

   @Mock
   private Estate estateMax;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);
      estates = new ArrayList<Estate>();

      estates.add(estateMin);
      estates.add(estateMid);
      estates.add(estateMax);

      priceSortingStrategy = new PriceSortingStrategy();

      configureEstates();
   }

   private void configureEstates() {
      when(estateMin.getPrice()).thenReturn(MIN_PRICE);
      when(estateMid.getPrice()).thenReturn(MID_PRICE);
      when(estateMax.getPrice()).thenReturn(MAX_PRICE);

   }

   @Test
   public void whenEstatesSortByPriceShouldReturnListSortAscendant() {
      // Given

      // When
      priceSortingStrategy.sort(estates);

      // Then
      assertEquals(MIN_PRICE, estates.get(0).getPrice().intValue());
      assertEquals(MID_PRICE, estates.get(1).getPrice().intValue());
      assertEquals(MAX_PRICE, estates.get(2).getPrice().intValue());
   }
}
