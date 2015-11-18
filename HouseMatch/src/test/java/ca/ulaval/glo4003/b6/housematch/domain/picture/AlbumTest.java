package ca.ulaval.glo4003.b6.housematch.domain.picture;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.mockito.Mock;

public class AlbumTest {

   private final String ESTATE_ADDRESS = "address";

   @Mock
   List<String> activePictureNames;

   @Mock
   private PictureRepository pictureRepo;

   @Test
   public void givenValidAlbumWhenGetActivePictureNamesShoudlReturnThen() {
      // Given
      Album album = new Album(ESTATE_ADDRESS, activePictureNames);

      // When
      List<String> rep = album.getActivePictureNames();

      // Then
      assertEquals(activePictureNames, rep);
   }

   @Test
   public void givenValidAlbumWhenGetEstateAddressShoudlReturnIt() {
      // Given
      Album album = new Album(ESTATE_ADDRESS, activePictureNames);

      // When
      String rep = album.getEstateAddress();

      // Then
      assertEquals(ESTATE_ADDRESS, rep);
   }

   @Test
   public void givenValidAlbumWhenGetCreateCustomPictureSelectorShoudlReturnIt() {
      // Given
      Album album = new Album(ESTATE_ADDRESS, activePictureNames);

      // When
      PictureSelector rep = album.createAlbumPictureSelector(pictureRepo);

      // Then
      assertTrue(rep instanceof PictureSelector);
   }
}
