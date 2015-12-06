package ca.ulaval.glo4003.b6.housematch.domain.estate.sorters;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.domain.estate.Estate;

public class DateRegistredSortingStrategyTest {

   private static final LocalDateTime MIN_DATE = LocalDateTime.of(2000, 12, 12, 12, 12);

   private static final LocalDateTime MID_DATE = LocalDateTime.of(2005, 12, 12, 12, 12);

   private static final LocalDateTime MAX_DATE = LocalDateTime.of(2010, 12, 12, 12, 12);

   private List<Estate> estates;

   private DateRegistredSortingStrategy dateRegisteredStrategy;

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

      dateRegisteredStrategy = new DateRegistredSortingStrategy();

      configureEstates();
   }

   private void configureEstates() {
      when(estateMin.getDateRegistered()).thenReturn(MIN_DATE);
      when(estateMid.getDateRegistered()).thenReturn(MID_DATE);
      when(estateMax.getDateRegistered()).thenReturn(MAX_DATE);

   }

   @Test
   public void whenEstatesSortByDateAscendantShouldReturnListSortAscendant() {
      // Given

      // When
      dateRegisteredStrategy.sort(estates);

      // Then
      assertEquals(MIN_DATE, estates.get(0).getDateRegistered());
      assertEquals(MID_DATE, estates.get(1).getDateRegistered());
      assertEquals(MAX_DATE, estates.get(2).getDateRegistered());
   }
}
