package ca.ulaval.glo4003.b6.housematch.domain.estate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
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

   private static final LocalDateTime MIN_DATE = LocalDateTime.of(2000, 12, 12, 12, 12);

   private static final LocalDateTime MID_DATE = LocalDateTime.of(2005, 12, 12, 12, 12);

   private static final LocalDateTime MAX_DATE = LocalDateTime.of(2010, 12, 12, 12, 12);

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
   public void setup() {
      MockitoAnnotations.initMocks(this);
      estateSorter = new EstateSorter();
      configureEstates();
      configureListEstate();
      configureEstateSorter();
   }

   private void configureEstates() {
      when(estateMin.getPrice()).thenReturn(MIN_PRICE);
      when(estateMid.getPrice()).thenReturn(MID_PRICE);
      when(estateMax.getPrice()).thenReturn(MAX_PRICE);

      when(estateMin.getDateRegistered()).thenReturn(MIN_DATE);
      when(estateMid.getDateRegistered()).thenReturn(MID_DATE);
      when(estateMax.getDateRegistered()).thenReturn(MAX_DATE);
   }

   private void configureListEstate() {
      estates = new ArrayList<Estate>();
      estates.add(estateMax);
      estates.add(estateMid);
      estates.add(estateMin);
   }

   private void configureEstateSorter() {
      estateSorter.setEstates(estates);
   }

   @Test
   public void whenEstateSorterPriceAscendantShouldReturnListSortAscendant() {
      // Given

      // When
      estatesSort = estateSorter.getPriceAscendantSort();

      // Then
      assertTrue(estatesSort.get(0).getPrice() == MIN_PRICE);
      assertTrue(estatesSort.get(1).getPrice() == MID_PRICE);
      assertTrue(estatesSort.get(2).getPrice() == MAX_PRICE);
   }

   @Test
   public void whenEstateSorterPriceDescendantShouldReturnListSortDescendant() {
      // Given

      // When
      estatesSort = estateSorter.getPriceDescendantSort();

      // Then
      assertTrue(estatesSort.get(0).getPrice() == MAX_PRICE);
      assertTrue(estatesSort.get(1).getPrice() == MID_PRICE);
      assertTrue(estatesSort.get(2).getPrice() == MIN_PRICE);
   }

   @Test
   public void whenEstateSorterDateAscendantShouldReturnListSortAscendant() {
      // Given

      // When
      estatesSort = estateSorter.getDateAscendantSort();

      // Then
      assertEquals(estatesSort.get(0).getDateRegistered(), MIN_DATE);
      assertEquals(estatesSort.get(1).getDateRegistered(), MID_DATE);
      assertEquals(estatesSort.get(2).getDateRegistered(), MAX_DATE);
   }

   @Test
   public void whenEstateSorterDateDescendantShouldReturnListSortDescendant() {
      // Given

      // When
      estatesSort = estateSorter.getDateDescendantSort();

      // Then
      assertTrue(estatesSort.get(0).getDateRegistered() == MAX_DATE);
      assertTrue(estatesSort.get(1).getDateRegistered() == MID_DATE);
      assertTrue(estatesSort.get(2).getDateRegistered() == MIN_DATE);
   }

}