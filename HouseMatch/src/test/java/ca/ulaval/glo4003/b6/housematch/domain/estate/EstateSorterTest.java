package ca.ulaval.glo4003.b6.housematch.domain.estate;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class EstateSorterTest {
   
   private static final int MIN_PRICE = 1;
   private static final int MID_PRICE = 50000;
   private static final int MAX_PRICE = 10000000;
   
   @Mock
   private Estate estateMin;
   
   @Mock
   private Estate estateMid;
   
   @Mock
   private Estate estateMax;
     
   private List<Estate> estates;
   
   private List<Estate> estatesSort;
   
   private EstateSorter estateSorter;
 
   
   @Before
   public void setup(){
      MockitoAnnotations.initMocks(this);
      EstateComparator priceEstateComparator = new EstatePriceComparator();
      estateSorter = new EstateSorter(priceEstateComparator);
      configureEstates();
      configureListEstate();
   }
   
   private void configureEstates() {
      when(estateMin.getPrice()).thenReturn(MIN_PRICE);
      when(estateMid.getPrice()).thenReturn(MID_PRICE);
      when(estateMax.getPrice()).thenReturn(MAX_PRICE);
      
   }

   private void configureListEstate() {
      estates = new ArrayList<Estate>();
      estates.add(estateMax);
      estates.add(estateMid);
      estates.add(estateMin);
      
   }

   @Test
   public void whenEstateSorterSortPriceShouldReturnListSort() {
      // Given

      // When
      estatesSort = estateSorter.priceSort(estates);
      
      // Then
      assertTrue(estatesSort.get(0).getPrice() == MAX_PRICE);
      assertTrue(estatesSort.get(1).getPrice() == MID_PRICE);
      assertTrue(estatesSort.get(2).getPrice() == MIN_PRICE);
   }

}
