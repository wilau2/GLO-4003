package ca.ulaval.glo4003.b6.housematch.domain.picture;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class PictureTest {

   private final String UID = "uid";

   private final String INVALID_UID = "uid 2 ";

   private final String ADDRESS = "Address";

   private final String INVALID_ADDRESS = "Address 2";

   private final String NAME = "name";

   private final String TRUE = "true";

   private final String FALSE = "false";

   private final String EMPTY = "";

   private final String NULL = null;

   @Before
   public void setup() {

   }

   @Test
   public void givenActivePictureWhenIsActiveShouldReturnTrue() {
      // Given
      Picture picture = new Picture(UID, ADDRESS, NAME, TRUE);

      // When
      Boolean rep = picture.isActive();

      // Then
      assertTrue(rep);
   }

   @Test
   public void givenInactivePictureWhenIsActiveShouldReturnFalse() {
      // Given
      Picture picture = new Picture(UID, ADDRESS, NAME, FALSE);

      // When
      Boolean rep = picture.isActive();

      // Then
      assertFalse(rep);
   }

   @Test
   public void givenEmptyActivityPictureWhenIsActiveShouldReturnFalse() {
      // Given
      Picture picture = new Picture(UID, ADDRESS, NAME, EMPTY);

      // When
      Boolean rep = picture.isActive();

      // Then
      assertFalse(rep);
   }

   @Test
   public void givenNullActivityPictureWhenIsActiveShouldReturnFalse() {
      // Given
      Picture picture = new Picture(UID, ADDRESS, NAME, NULL);

      // When
      Boolean rep = picture.isActive();

      // Then
      assertFalse(rep);
   }

   @Test
   public void givenValidUidPictureWhenGetUidShouldReturnIt() {
      // Given
      Picture picture = new Picture(UID, ADDRESS, NAME, TRUE);

      // When
      String rep = picture.getUid();

      // Then
      assertTrue(rep.equals(UID));
   }

   @Test
   public void givenValidAddressPictureWhenGetAddressShouldReturnIt() {
      // Given
      Picture picture = new Picture(UID, ADDRESS, NAME, TRUE);

      // When
      String rep = picture.getAddress();

      // Then
      assertTrue(rep.equals(ADDRESS));
   }

   @Test
   public void givenValidNamePictureWhenGetNamehouldReturnIt() {
      // Given
      Picture picture = new Picture(UID, ADDRESS, NAME, TRUE);

      // When
      String rep = picture.getName();

      // Then
      assertTrue(rep.equals(NAME));
   }

   @Test
   public void givenNullUidPictureUidShouldNotBeNull() {
      // Given
      Picture picture = new Picture(NULL, ADDRESS, NAME, TRUE);

      // When
      String rep = picture.getUid();

      // Then
      assertFalse(rep.isEmpty());
   }

   @Test
   public void givenInactivatePictureWhenActivateShouldBeActive() {
      // Given
      Picture picture = new Picture(UID, ADDRESS, NAME, FALSE);

      // When
      picture.activate();

      // Then
      assertTrue(picture.getActive());
   }

   @Test
   public void givenValidAddressPictureWhenIsFromEstateShouldReturnTrue() {

      // Given
      Picture picture = new Picture(UID, ADDRESS, NAME, TRUE);

      // When is from estate

      // Then
      assertTrue(picture.isFromEstate(ADDRESS));
   }

   @Test
   public void givenInvalidAddressPictureWhenIsFromEstateShouldReturnTrue() {

      // Given
      Picture picture = new Picture(UID, INVALID_ADDRESS, NAME, TRUE);

      // When is not from estate from estate

      // Then
      assertFalse(picture.isFromEstate(ADDRESS));
   }

   @Test
   public void givenValidUidPictureWhenIsValidUidShouldReturnTrue() {

      // Given
      Picture picture = new Picture(UID, ADDRESS, NAME, TRUE);

      // When is valid uid

      // Then
      assertTrue(picture.isValidUid(UID));
   }

   @Test
   public void givenInvalidUidPictureWhenIsValidUidShouldReturnTrue() {

      // Given
      Picture picture = new Picture(INVALID_UID, ADDRESS, NAME, TRUE);

      // When is not valid uid

      // Then
      assertFalse(picture.isValidUid(UID));
   }

}
