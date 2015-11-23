package ca.ulaval.glo4003.b6.housematch.domain.estate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

   private static final ArrayList<Integer> PRICE_HISTORY = new ArrayList<Integer>();

   private static final boolean BOUGHT = false;

   @Mock
   private Address address;

   @Mock
   private Description description;

   private Estate estate;

   private LocalDateTime dateRegistered;

   @Before
   public void setup() {

      MockitoAnnotations.initMocks(this);

      dateRegistered = LocalDateTime.of(2000, 12, 12, 12, 12);

      estate = new Estate(TYPE, address, PRICE, SELLER_NAME, description, dateRegistered, PRICE_HISTORY, BOUGHT);
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
   public void estateHasBeenBoughtWhenAnEstateIsStillForSaleShouldReturnFalse() {
      // Given no changes

      // When
      boolean hasBeenBought = estate.hasBeenBought();

      // Then
      assertFalse(hasBeenBought);
   }
}
