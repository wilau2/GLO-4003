package ca.ulaval.glo4003.b6.housematch.domain.picture;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.mockito.Mock;

public class AlbumPictureFactoryTest {

   private final String ESTATE_ADDRESS = "address";

   @Mock
   private List<String> activePictureNames;

   @Test
   public void givenValidAlbumFactoryWhenCreateAblumShouldReturnAlbumInstance() {
      // Given
      AlbumPictureFactory factory = new AlbumPictureFactory();

      // When
      Album object = factory.createAlbum(ESTATE_ADDRESS);

      // Then
      assertTrue(object instanceof Album);
   }

   @Test
   public void givenValidAlbumFactoryWhenCreateAblumWithActivePicturesNamesShouldReturnAlbumInstance() {
      // Given
      AlbumPictureFactory factory = new AlbumPictureFactory();

      // When
      Album object = factory.createAlbum(activePictureNames, ESTATE_ADDRESS);

      // Then
      assertTrue(object instanceof Album);
   }

}
