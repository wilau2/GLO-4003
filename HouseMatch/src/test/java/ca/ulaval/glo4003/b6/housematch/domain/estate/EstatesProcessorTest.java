package ca.ulaval.glo4003.b6.housematch.domain.estate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.dom4j.DocumentException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;

public class EstatesProcessorTest {

   private static final String SECOND_SELLER = "SECOND_SELLER";

   private static final String FIRST_SELLER = "FIRST_SELLER";
   
   private static final String FIRST_ESTATE_TYPE = "FIRST_TYPE";
   
   private static final String SECOND_ESTATE_TYPE = "SECOND_TYPE";

   private EstatesProcessor estatesProcessor;

   private List<Estate> estates;

   @Mock
   private Estate estate;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);

      estatesProcessor = new EstatesProcessor();

      estates = new ArrayList<Estate>();
   }

   @Test
   public void askingForTheNumberOfUniqueSellerWhenOnlyOneEstateShouldReturnNumberOfEstate()
         throws DocumentException, CouldNotAccessDataException {
      // Given
      int expectedNumberOfUniqueSeller = 1;
      configureEstateInEstates(expectedNumberOfUniqueSeller);

      // When
      List<String> returnedValue = estatesProcessor.retrieveUniqueSellersName(estates);

      // Then
      assertEquals(expectedNumberOfUniqueSeller, returnedValue.size());
   }

   private void configureEstateInEstates(int numberOfEstates) {
      for (int i = 0; i < numberOfEstates; i++) {
         estates.add(estate);
      }
   }
   
   @Test
   public void askingForTheNumberOfUniqueSellerWhenMoreThanOneSellerEstateWithAnEstateShouldReturnNumberOfEstate()
         throws DocumentException, CouldNotAccessDataException {
      // Given
      int expectedNumberOfUniqueSeller = 2;

      configureEstateInEstates(expectedNumberOfUniqueSeller + 1);
      when(estate.getSeller()).thenReturn(FIRST_SELLER, SECOND_SELLER, FIRST_SELLER);

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
      int wantedNumberOfEstates = 3;
      configureEstateInEstates(wantedNumberOfEstates);
      when(estate.hasBeenBought()).thenReturn(false);

      // When
      List<Estate> estatesSoldLastYear = estatesProcessor.retrieveEstatesSoldLastYear(estates);

      // Then
      assertTrue(estatesSoldLastYear.isEmpty());
   }

   @Test
   public void askingForEstatesSoldLastYearWhenAllEstatesHasBeenSoldForMoreThanAYearShouldReturnEmptyList() {
      // Given
      int wantedNumberOfEstates = 3;
      configureEstateInEstates(wantedNumberOfEstates);
      when(estate.hasBeenBoughtInLastYear()).thenReturn(false);

      // When
      List<Estate> estatesSoldLastYear = estatesProcessor.retrieveEstatesSoldLastYear(estates);

      // Then
      assertTrue(estatesSoldLastYear.isEmpty());
   }

   @Test
   public void askingForEstatesSoldLastYearWhenOneEstatesHasBeenSoldLastYearShouldReturnListWithEstateInside() {
      // Given
      int wantedNumberOfEstates = 3;
      configureEstateInEstates(wantedNumberOfEstates);
      when(estate.hasBeenBoughtInLastYear()).thenReturn(false, true, false);
      int expectedSize = 1;

      // When
      List<Estate> estatesSoldLastYear = estatesProcessor.retrieveEstatesSoldLastYear(estates);

      // Then
      assertEquals(expectedSize, estatesSoldLastYear.size());
   }

   @Test
   public void askingForEstatesSoldLastYearWhenAllEstatesHasBeenSoldLastYearShouldReturnListWithAllEstatesInside() {
      // Given
      int wantedNumberOfEstates = 3;
      configureEstateInEstates(wantedNumberOfEstates);
      when(estate.hasBeenBoughtInLastYear()).thenReturn(true);

      // When
      List<Estate> estatesSoldLastYear = estatesProcessor.retrieveEstatesSoldLastYear(estates);

      // Then
      assertEquals(wantedNumberOfEstates, estatesSoldLastYear.size());
   }
   
   @Test
   public void askingNumberOfEstatesInEachTypeWhenEstatesHaveOneTypeShouldReturnListWithOneType() {
      // Given    
      int wantedNumberOfType = 1;
      int numberOfEstates = 3;
      configureEstateInEstates(numberOfEstates);
      when(estate.getType()).thenReturn(FIRST_ESTATE_TYPE,FIRST_ESTATE_TYPE,FIRST_ESTATE_TYPE);

      // When
      HashMap<String, Integer> numberEstatesInEachType = estatesProcessor.retrieveNumberEstatesInEachType(estates);

      // Then
      assertEquals(wantedNumberOfType, numberEstatesInEachType.size());
   }
   
   @Test
   public void askingNumberOfEstatesInEachTypeWhenEstatesHaveTwoTypeShouldReturnListWithTwoType() {
      // Given
      int wantedNumberOfType = 2;
      int numberOfEstates = 3;
      configureEstateInEstates(numberOfEstates);
      when(estate.getType()).thenReturn(FIRST_ESTATE_TYPE,FIRST_ESTATE_TYPE,SECOND_ESTATE_TYPE);

      // When
      HashMap<String, Integer> numberEstatesInEachType = estatesProcessor.retrieveNumberEstatesInEachType(estates);

      // Then
      assertEquals(wantedNumberOfType, numberEstatesInEachType.size());
   }
   
   @Test
   public void askingNumberOfEstatesInEachTypeWhenThereIsNoEstatesShouldReturnMapWithNoElement() {
      // Given
      int wantedNumberOfType = 0;

      // When
      HashMap<String, Integer> numberEstatesInEachType = estatesProcessor.retrieveNumberEstatesInEachType(estates);

      // Then
      assertEquals(wantedNumberOfType, numberEstatesInEachType.size());
   }
}
