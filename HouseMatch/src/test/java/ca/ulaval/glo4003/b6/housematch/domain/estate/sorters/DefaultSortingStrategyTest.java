package ca.ulaval.glo4003.b6.housematch.domain.estate.sorters;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.domain.estate.Estate;

public class DefaultSortingStrategyTest {

   private List<Estate> estates;

   private DefaultSortingStrategy defaultStartegy;

   @Mock
   private Estate estateFirst;

   @Mock
   private Estate estateSecond;

   @Mock
   private Estate estateThird;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);
      estates = new ArrayList<Estate>();

      estates.add(estateFirst);
      estates.add(estateSecond);
      estates.add(estateThird);

      defaultStartegy = new DefaultSortingStrategy();

   }

   @Test
   public void whenEstatesSortByDefaultShouldReturnListOfUnsortedEstates() {
      // Given

      // When
      defaultStartegy.sort(estates);

      // Then
      assertEquals(estateFirst, estates.get(0));
      assertEquals(estateSecond, estates.get(1));
      assertEquals(estateThird, estates.get(2));
   }
}
