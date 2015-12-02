package ca.ulaval.glo4003.b6.housematch.domain.estate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Iterator;
import java.util.List;

import org.dom4j.DocumentException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;

public class EstatesProcessorTest {

   private static final String SECOND_SELLER = "SECOND_SELLER";

   private static final String FIRST_SELLER = "FIRST_SELLER";

   private static final String SELLER_NAME = "seller";

   @InjectMocks
   private EstatesProcessor estatesProcessor;

   @Mock
   private Estates estates;

   @Mock
   private Estate estate;

   @Mock
   private List<Estate> listEstates;

   @Mock
   private Iterator<Estate> iterator;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);

   }

   private void configureOneValidListEstate() {
      configureEstate();
      when(listEstates.iterator()).thenReturn(iterator);
      when(iterator.hasNext()).thenReturn(true, false);
      when(iterator.next()).thenReturn(estate);
      when(listEstates.size()).thenReturn(1);
      configureEstates();
   }

   private void configureOneInValidListEstate() {
      configureInvalidEstate();
      when(listEstates.iterator()).thenReturn(iterator);
      when(iterator.hasNext()).thenReturn(true, false);
      when(iterator.next()).thenReturn(estate);
      when(listEstates.size()).thenReturn(0);
      configureEstates();
   }

   private void configureThreeDifferentValidListEstate() {
      configureThreeEstateWithDifferentNames();
      when(listEstates.iterator()).thenReturn(iterator);
      when(iterator.hasNext()).thenReturn(true, true, true, false);
      when(iterator.next()).thenReturn(estate);
      configureEstates();
   }

   private void configureEstate() {
      when(estate.getSeller()).thenReturn(SELLER_NAME);
      when(estate.isFromSeller(SELLER_NAME)).thenReturn(true);
      when(estate.hasBeenBoughtInLastYear()).thenReturn(true);

   }

   private void configureInvalidEstate() {
      when(estate.getSeller()).thenReturn(FIRST_SELLER);
      when(estate.isFromSeller(SELLER_NAME)).thenReturn(false);
      when(estate.hasBeenBoughtInLastYear()).thenReturn(false);

   }

   private void configureThreeEstateWithDifferentNames() {
      when(estate.getSeller()).thenReturn(SELLER_NAME, FIRST_SELLER, SELLER_NAME);
      when(estate.isFromSeller(SELLER_NAME)).thenReturn(true);
      when(estate.hasBeenBoughtInLastYear()).thenReturn(true);

   }

   private void configureEstates() {
      when(estates.retreiveListOfEstate()).thenReturn(listEstates);

   }

   @Test
   public void askingForTheNumberOfUniqueSellerWhenOnlyOneEstateShouldReturnNumberOfEstate()
         throws DocumentException, CouldNotAccessDataException {
      // Given
      int expectedNumberOfUniqueSeller = 1;
      configureOneValidListEstate();

      // When
      List<String> returnedValue = estatesProcessor.retrieveUniqueSellersName(estates);

      // Then
      assertEquals(expectedNumberOfUniqueSeller, returnedValue.size());
   }

   @Test
   public void askingForTheNumberOfUniqueSellerWhenMoreThanOneSellerEstateWithAnEstateShouldReturnNumberOfEstate()
         throws DocumentException, CouldNotAccessDataException {
      // Given
      int expectedNumberOfUniqueSeller = 2;
      configureThreeDifferentValidListEstate();

      // When
      List<String> returnedValue = estatesProcessor.retrieveUniqueSellersName(estates);

      // Then
      assertEquals(expectedNumberOfUniqueSeller, returnedValue.size());
   }

   @Test
   public void gettingTheNumberOfUniqueSellerWhenNoEstateInRepositoryShouldReturnZero()
         throws CouldNotAccessDataException {
      // Given
      int expectedNumberOfEstate = 0;

      // When
      List<String> returnedValue = estatesProcessor.retrieveUniqueSellersName(estates);

      // Then
      assertEquals(expectedNumberOfEstate, returnedValue.size());
   }

   @Test
   public void askingForEstatesSoldLastYearWhenNoEstatesHasBeenSoldShouldReturnEmptyList() {
      // Given
      configureOneInValidListEstate();

      // When
      Estates estatesSoldLastYear = estatesProcessor.retrieveEstatesSoldLastYear(estates);

      // Then
      assertEquals(0, estatesSoldLastYear.retreiveListOfEstate().size());
   }

   @Test
   public void askingForEstatesSoldLastYearWhenAllEstatesHasBeenSoldForMoreThanAYearShouldReturnEmptyList() {
      // Given
      configureOneInValidListEstate();

      // When
      Estates estatesSoldLastYear = estatesProcessor.retrieveEstatesSoldLastYear(estates);

      // Then
      assertEquals(0, estatesSoldLastYear.retreiveListOfEstate().size());
   }

   @Test
   public void askingForEstatesSoldLastYearWhenOneEstatesHasBeenSoldLastYearShouldReturnListWithEstateInside() {
      // Given
      int expectedSize = 1;
      configureOneValidListEstate();

      // When
      Estates estatesSoldLastYear = estatesProcessor.retrieveEstatesSoldLastYear(estates);

      // Then
      assertEquals(expectedSize, estatesSoldLastYear.retreiveListOfEstate().size());
   }

   @Test
   public void askingForEstatesSoldLastYearWhenAllEstatesHasBeenSoldLastYearShouldReturnListWithAllEstatesInside() {
      // Given
      int wantedNumberOfEstates = 1;
      configureOneValidListEstate();

      // When
      Estates estatesSoldLastYear = estatesProcessor.retrieveEstatesSoldLastYear(estates);

      // Then
      assertEquals(wantedNumberOfEstates, estatesSoldLastYear.retreiveListOfEstate().size());
   }

   @Test
   public void askingForEstatesBySellerNameShouldReturnEstateWithValidSellerName() {
      // Given
      configureOneValidListEstate();

      // When
      Estates sellerEstates = estatesProcessor.retrieveEstatesBySellerName(estates, SELLER_NAME);

      // Then
      assertEquals(1, sellerEstates.retreiveListOfEstate().size());
   }

   @Test
   public void askingForEstatesBySellerNameWhenNoEstateToThisNameShouldReturnNoEstate() {
      // Given
      configureOneInValidListEstate();

      // When
      Estates sellerEstates = estatesProcessor.retrieveEstatesBySellerName(estates, SELLER_NAME);

      // Then
      assertEquals(0, sellerEstates.retreiveListOfEstate().size());
   }
}
