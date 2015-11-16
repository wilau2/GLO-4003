package ca.ulaval.glo4003.b6.housematch.domain.picture;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

public class PictureSelectorTest {

   private final String NO_PHOTO_AVAILABLE_MESSAGE = "You should add some photos!";

   private final String PHOTONAME = "PHOTONAME";

   private final String ADDRESS = "ADDRESS";

   @Mock
   private Album album;

   @Mock
   private PictureRepository pictureRepository;

   @Mock
   private MultipartFile pictureFile;

   private PictureSelector pictureSelector;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);
      configureAlbum();

      pictureSelector = new PictureSelector(album, pictureRepository);
   }

   @Test
   public void whenThereAreNoAvailablePicturesFetchDefaultFromRepository() throws IOException {
      // When
      pictureSelector.getPicture(NO_PHOTO_AVAILABLE_MESSAGE);

      // Then
      verify(pictureRepository, times(1)).getEmptyPicture();
   }

   @Test
   public void whenAskedASpecificPictureFetchWithCorrespondingAddress() throws IOException {
      // When
      pictureSelector.getPicture(PHOTONAME);

      // Then
      verify(pictureRepository, times(1)).getPicture(PHOTONAME, ADDRESS);
   }

   @Test
   public void whenDeletingAPictureUseCorrespondingAddress() throws IOException {
      // When
      pictureSelector.deletePicture(PHOTONAME);

      // Then
      verify(pictureRepository, times(1)).deletePicture(PHOTONAME, ADDRESS);
   }

   @Test
   public void whenAddingAPictureUseCorrespondingAddress() throws IOException {
      // When
      pictureSelector.addPicture(PHOTONAME, pictureFile);

      // Then
      verify(pictureRepository, times(1)).addPicture(PHOTONAME, ADDRESS, pictureFile);
   }

   @Test
   public void whenAskedForRelevantPicturesAskRepositoryForEveryPicturesAvailable() throws IOException {
      // When
      pictureSelector.getRelevantPictures();

      // Then
      verify(pictureRepository, times(1)).getEveryPicturesNames(ADDRESS);
   }

   @Test
   public void whenAskedForRelevantPicturesAndThereIsNoneReturnDefaultMessage() throws IOException {
      // Given a repository that cant find pictures for the estate
      List<String> everyAvailablePictures = new ArrayList<String>();
      everyAvailablePictures.add(NO_PHOTO_AVAILABLE_MESSAGE);
      when(pictureRepository.getEveryPicturesNames(ADDRESS)).thenReturn(everyAvailablePictures);

      // When
      pictureSelector.getRelevantPictures();

      // Then
      assertEquals(everyAvailablePictures, pictureSelector.getRelevantPictures());
   }

   private void configureAlbum() {
      when(album.getEstateAddress()).thenReturn(ADDRESS);
   }

}
