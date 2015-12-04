package ca.ulaval.glo4003.b6.housematch.domain.estate.filters;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.domain.estate.Estate;
import ca.ulaval.glo4003.b6.housematch.domain.estate.filters.EstatePriceFilter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class EstatePriceFilterTest {
   
   private static final int LOWER_PRICE = 999;
   private static final int AVERAGE_PRICE = 5000;
   private static final int HIGH_PRICE = 11000;
   
   private static final int MIN_PRICE = 1000;
   private static final int MAX_PRICE = 10000;

   @Mock
   private Estate estate1;
   
   @Mock
   private Estate estate2;
   
   @Mock
   private Estate estate3;
   
   private List<Estate> estates;
   
   private EstatePriceFilter estatePriceFilter;
   
   
   @Before
   public void setup(){
      MockitoAnnotations.initMocks(this);
      estatePriceFilter = new EstatePriceFilter();
      configureEstates();
   }

   private void configureEstates() {
      when(estate1.getPrice()).thenReturn(LOWER_PRICE);
      when(estate2.getPrice()).thenReturn(AVERAGE_PRICE);
      when(estate3.getPrice()).thenReturn(HIGH_PRICE);
      estates = new ArrayList<Estate>();
      estates.add(estate1);
      estates.add(estate2);
      estates.add(estate3); 
   }
   
   @Test
   public void whenFilterWithGoodParameterShouldReturnValueBetweenMinAnMax() throws InconsistentFilterParamaterException {
      // Given
      
      // When
      List<Estate> filteredEstates = estatePriceFilter.filter(estates, MIN_PRICE, MAX_PRICE);
       
      // Then
      assertEquals(filteredEstates.get(0).getPrice(), estate2.getPrice());
      assertTrue(filteredEstates.size() == 1);
   }
   
   @Test(expected = InconsistentFilterParamaterException.class)
   public void whenFilterWithInconsistentParameterShouldThrowInconsistentFilterParamaterException() throws InconsistentFilterParamaterException {
      // Given
 
      // When
      estatePriceFilter.filter(estates, MAX_PRICE, MIN_PRICE);
      
      // Then
   }

}
