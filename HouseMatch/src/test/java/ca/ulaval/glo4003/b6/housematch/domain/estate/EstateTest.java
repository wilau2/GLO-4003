package ca.ulaval.glo4003.b6.housematch.domain.estate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class EstateTest {

   private static final String SELLER_NAME = "SELLER";

   private static final Integer PRICE = 1;

   private static final String TYPE = "TYPE";

   @Mock
   private Address address;

   @Mock
   private Description description;

   private Estate estate;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);

      estate = new Estate(TYPE, address, PRICE, SELLER_NAME, description, null);
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
}
