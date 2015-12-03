package ca.ulaval.glo4003.b6.housematch.domain.picture;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.mockito.Mock;

public class AlbumFactoryTest {

   private final String ESTATE_ADDRESS = "address";

   @Mock
   private List<String> activePictureNames;

   @Mock
   private PictureRepository pictureRepository;

   @Mock
   private Album album;

   @Test
   public void givenValidAlbumFactoryWhenCreateAblumShouldReturnAlbumInstance() {
      // Given
      AlbumFactory factory = new AlbumFactory();

      // When
      Album object = factory.createAlbum(ESTATE_ADDRESS);

      // Then
      assertTrue(object instanceof Album);
   }

   @Test
   public void givenValidAlbumFactoryWhenCreateAlbumWithActivePicturesNamesShouldReturnAlbumInstance() {
      // Given
      AlbumFactory factory = new AlbumFactory();

      // When
      Album object = factory.createAlbum(activePictureNames, ESTATE_ADDRESS);

      // Then
      assertTrue(object instanceof Album);
   }

}
