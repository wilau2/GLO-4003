package ca.ulaval.glo4003.b6.housematch.domain.estate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.EstateAlreadyBoughtException;

public class EstateTest {

   private static final String SELLER_NAME = "SELLER";

   private static final Integer PRICE = 1;

   private static final String TYPE = "TYPE";

   private static final boolean BOUGHT = false;

   private static final int MIN_PRICE = 10;

   private static final int MAX_PRICE = 100;

   private static final Integer PRICE_BETWEEN_MAX_AND_MIN = 50;

   @Mock
   private Address address;

   @Mock
   private Description description;

   private ArrayList<Integer> priceHistory;

   private Estate estate;

   private LocalDateTime dateRegistered;

   private LocalDateTime dateModified;

   private LocalDateTime dateOfPurchase;

   @Mock
   private Description newDescription;

   @Mock
   private ChangeVerificator changeVerificator;

   @Before
   public void setup() {

      MockitoAnnotations.initMocks(this);

      dateRegistered = LocalDateTime.of(2000, 12, 12, 12, 12);
      dateModified = LocalDateTime.of(2000, 12, 12, 12, 12);
      priceHistory = new ArrayList<>();
      estate = new Estate(TYPE, address, PRICE, SELLER_NAME, description, dateRegistered, priceHistory, BOUGHT,
            dateOfPurchase, dateModified);

   }

   @Test
   public void shouldReturnTheTypeGivenAtInitialisation() {
      // Given an estate

      // Then
      assertEquals(TYPE, estate.getType());
   }

   @Test
   public void shouldReturnTheAddressGivenAtInitialisation() {
      // Given an estate

      // Then
      assertEquals(address, estate.getAddress());
   }

   @Test
   public void shouldReturnThePriceGivenAtInitialisation() {
      // Given an estate

      // Then
      assertEquals(PRICE, estate.getPrice());
   }

   @Test
   public void shouldReturnTheSellerGivenAtInitialisation() {
      // Given an estate

      // Then
      assertEquals(SELLER_NAME, estate.getSeller());
   }

   @Test
   public void verifyingIfEstateIsFromSellerWhenEstateIsFromSellerShouldReturnTrue() {
      // Given no changes

      // When
      boolean isFromSeller = estate.isFromSeller(SELLER_NAME);

      // Then
      assertTrue(isFromSeller);
   }

   @Test
   public void verifyingIfEstateIsFromSellerWhenEstateIsNotFromSellerShouldReturnFalse() {
      // Given
      String notEstateSeller = "NOT_ESTATE_SELLER";

      // When
      boolean isFromSeller = estate.isFromSeller(notEstateSeller);

      // Then
      assertFalse(isFromSeller);
   }

   @Test
   public void buyingAnEstateWhenEstateIsStillForSaleShouldNotThrowException() throws EstateAlreadyBoughtException {
      // Given no changes

      // When
      estate.buy();

      // Then no exception is thrown;
   }

   @Test(expected = EstateAlreadyBoughtException.class)
   public void buyingAnEstateWhenEstateIsAlreadyBoughtShouldThrowException() throws EstateAlreadyBoughtException {
      // Given
      estate.buy(); // buying for the first time the estate

      // When
      estate.buy();

      // Then an estate already bought exception
   }

   @Test
   public void whenBuyingAnEstateShouldChangeTheBoughtStatusToTrue() throws EstateAlreadyBoughtException {
      // Given no changes

      // When
      estate.buy();

      // Then
      assertTrue(estate.hasBeenBought());
   }

   @Test
   public void whenBuyingAnEstateShouldAddADateBoughtTime() throws EstateAlreadyBoughtException {
      // Given no changes
      LocalDateTime expectedTimeOfPurchase = LocalDateTime.now();

      // When
      estate.buy();

      // Then
      LocalDateTime timeOfPurchase = estate.getDateOfPurchase();
      assertEquals(expectedTimeOfPurchase, timeOfPurchase);
   }

   @Test
   public void estateHasBeenBoughtWhenAnEstateIsStillForSaleShouldReturnFalse() {
      // Given no changes

      // When
      boolean hasBeenBought = estate.hasBeenBought();

      // Then
      assertFalse(hasBeenBought);
   }

   @Test
   public void editingPriceOfEstateWhenPriceIsTheSameShouldNotChangeThePrice() {
      // Given
      Integer newPrice = new Integer(PRICE);
      int expectedEmptyListSize = 0;

      // When
      estate.editPrice(newPrice);

      // Then
      ArrayList<Integer> priceHistory = estate.getPriceHistory();
      assertEquals(expectedEmptyListSize, priceHistory.size());
   }

   @Test
   public void editingPriceOfEstateWhenPriceIsNotTheSameShouldAddOldPriceInsidePriceHistory() {
      // Given
      Integer newPrice = new Integer(PRICE.intValue() - 1);
      int expectedListSize = 1;

      // When
      estate.editPrice(newPrice);

      // Then
      ArrayList<Integer> priceHistory = estate.getPriceHistory();
      assertEquals(expectedListSize, priceHistory.size());
      assertEquals(PRICE, priceHistory.get(expectedListSize - 1));
   }

   @Test
   public void whenEditingTypeOfEstateShouldChangeTheTypeOfTheEstate() {
      // Given
      String newType = "Type";

      // When
      estate.editType(newType);

      // Then
      assertEquals(newType, estate.getType());
   }

   @Test
   public void whenEditingDescriptionShouldSetNewDescription() {
      // Given no changes

      // When
      estate.editDescription(newDescription, changeVerificator);

      // Then
      assertEquals(newDescription, estate.getDescription());
   }

   @Test
   public void editingDescriptionWhenUpdatedDescriptionIsNotChangedEnoughShouldNotUpdateDateOfLastModification() {
      // Given no changes
      when(description.isChangeSignificant(newDescription, changeVerificator)).thenReturn(false);

      // When
      estate.editDescription(newDescription, changeVerificator);

      // Then
      assertEquals(estate.getDateModified(), dateModified);
   }

   @Test
   public void editingDescriptionWhenUpdatedDescriptionIsChangedEnoughShouldUpdateDateOfLastModification() {
      // Given no changes
      when(description.isChangeSignificant(newDescription, changeVerificator)).thenReturn(true);

      // When
      estate.editDescription(newDescription, changeVerificator);

      // Then
      assertNotSame(estate.getDateModified(), dateModified);
   }

   @Test
   public void askingIfEstateHasBeenSoldInThePastYearWhenEstateHasNotBeenBoughtShouldReturnFalse() {
      // Given no changes

      // When
      boolean hasBeenBoughtInLastYear = estate.hasBeenBoughtInLastYear();

      // Then
      assertFalse(hasBeenBoughtInLastYear);
   }

   @Test
   public void askingIfEstateHasBeenSoldInThePastYearWhenEstateHasBeenBoughtBeforeLastYearShouldReturnFalse()
         throws EstateAlreadyBoughtException {
      // Given
      LocalDateTime dateOfPurchaseLastYear = LocalDateTime.now().minusYears(1).minusNanos(1);
      estate = new Estate(TYPE, address, PRICE, SELLER_NAME, description, dateRegistered, priceHistory, true,
            dateOfPurchaseLastYear, dateModified);

      // When
      boolean hasBeenBoughtInLastYear = estate.hasBeenBoughtInLastYear();

      // Then
      assertFalse(hasBeenBoughtInLastYear);
   }

   @Test
   public void askingIfEstateHasBeenSoldInThePastYearWhenEstateHasBeenBoughtInTheLastYearShouldReturnTrue() {
      // Given
      LocalDateTime dateOfPurchaseLastYear = LocalDateTime.now().minusYears(1).plusSeconds(1);
      estate = new Estate(TYPE, address, PRICE, SELLER_NAME, description, dateRegistered, priceHistory, true,
            dateOfPurchaseLastYear, dateModified);

      // When
      boolean hasBeenBoughtInLastYear = estate.hasBeenBoughtInLastYear();

      // Then
      assertTrue(hasBeenBoughtInLastYear);
   }

   @Test
   public void askingIfEstatePriceIsBetweenValueWhenEstateIsBelowMinValueShouldReturnFalse() {
      // Given
      estate.editPrice(MIN_PRICE - 1);

      // When
      boolean priceBetween = estate.isPriceBetween(MIN_PRICE, MAX_PRICE);

      // Then
      assertFalse(priceBetween);
   }

   @Test
   public void askingIfEstatePriceIsBetweenValueWhenEstatePriceIsEqualToMinValueShouldReturnTrue() {
      // Given
      estate.editPrice(MIN_PRICE);

      // When
      boolean priceBetween = estate.isPriceBetween(MIN_PRICE, MAX_PRICE);

      // Then
      assertTrue(priceBetween);
   }

   @Test
   public void askingIfEstatePriceIsBetweenValuesWhenEstatePriceIsEqualToMaxValueShouldReturnTrue() {
      // Given
      estate.editPrice(MAX_PRICE);

      // When
      boolean priceBetween = estate.isPriceBetween(MIN_PRICE, MAX_PRICE);

      // Then
      assertTrue(priceBetween);
   }

   @Test
   public void askingIfEstatePriceIsBetweenValuesWhenPriceOfEstateIsAboveMaxValueShouldReturnFalse() {
      // Given
      estate.editPrice(MAX_PRICE + 1);

      // When
      boolean priceBetween = estate.isPriceBetween(MIN_PRICE, MAX_PRICE);

      // Then
      assertFalse(priceBetween);
   }

   @Test
   public void askingIfEstatePriceIsBetweenValuesWhenEstatePriceIsBetweenShouldReturnTrue() {
      // Given
      estate.editPrice(PRICE_BETWEEN_MAX_AND_MIN);

      // When
      boolean priceBetween = estate.isPriceBetween(MIN_PRICE, MAX_PRICE);

      // Then
      assertTrue(priceBetween);
   }
}
