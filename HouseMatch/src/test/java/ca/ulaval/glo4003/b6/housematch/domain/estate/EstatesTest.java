package ca.ulaval.glo4003.b6.housematch.domain.estate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EstatesTest {

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

   private Estates estates;

   private List<Estate> listEstates;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);
      configureEstates();
      listEstates = new ArrayList<Estate>();
      listEstates.add(estateMin);
      listEstates.add(estateMid);
      listEstates.add(estateMax);
      estates = new Estates(listEstates);

   }

   private void configureEstates() {
      when(estateMin.getPrice()).thenReturn(MIN_PRICE);
      when(estateMid.getPrice()).thenReturn(MID_PRICE);
      when(estateMax.getPrice()).thenReturn(MAX_PRICE);

      when(estateMin.getDateRegistered()).thenReturn(MIN_DATE);
      when(estateMid.getDateRegistered()).thenReturn(MID_DATE);
      when(estateMax.getDateRegistered()).thenReturn(MAX_DATE);
   }

   @Test
   public void whenEstatesSortByPriceAscendantShouldReturnListSortAscendant() {
      // Given

      // When
      estates.sortByPriceAscendantSort();

      // Then
      assertTrue(estates.retreiveListOfEstate().get(0).getPrice() == MIN_PRICE);
      assertTrue(estates.retreiveListOfEstate().get(1).getPrice() == MID_PRICE);
      assertTrue(estates.retreiveListOfEstate().get(2).getPrice() == MAX_PRICE);
   }

   @Test
   public void whenEstatesSortByPriceDescendantShouldReturnListSortDescendant() {
      // Given

      // When
      estates.sortByPriceDescendantSort();

      // Then
      assertTrue(estates.retreiveListOfEstate().get(0).getPrice() == MAX_PRICE);
      assertTrue(estates.retreiveListOfEstate().get(1).getPrice() == MID_PRICE);
      assertTrue(estates.retreiveListOfEstate().get(2).getPrice() == MIN_PRICE);
   }

   @Test
   public void whenEstatesSortByDateAscendantShouldReturnListSortAscendant() {
      // Given

      // When
      estates.sortByDateAscendantSort();

      // Then
      assertEquals(estates.retreiveListOfEstate().get(0).getDateRegistered(), MIN_DATE);
      assertEquals(estates.retreiveListOfEstate().get(1).getDateRegistered(), MID_DATE);
      assertEquals(estates.retreiveListOfEstate().get(2).getDateRegistered(), MAX_DATE);
   }

   @Test
   public void whenEstatesSortByDateDescendantShouldReturnListSortDescendant() {
      // Given

      // When
      estates.sortByDateDescendantSort();

      // Then
      assertTrue(estates.retreiveListOfEstate().get(0).getDateRegistered() == MAX_DATE);
      assertTrue(estates.retreiveListOfEstate().get(1).getDateRegistered() == MID_DATE);
      assertTrue(estates.retreiveListOfEstate().get(2).getDateRegistered() == MIN_DATE);
   }

   @Test
   public void whenEstatesIsNotEmptyShouldReturnRightSizeOfEstates() {
      // Given

      // When
      int size = estates.size();

      // Then
      assertEquals(3, size);
   }

   @Test
   public void whenEstatesAreUpdatedShouldUpdateEsateList() {
      // Given
      listEstates.remove(2);

      // When
      estates.updateEstatesList(listEstates);

      // Then
      assertEquals(2, estates.size());
   }

}
