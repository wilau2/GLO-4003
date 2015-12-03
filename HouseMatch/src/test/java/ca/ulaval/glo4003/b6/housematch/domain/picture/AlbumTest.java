package ca.ulaval.glo4003.b6.housematch.domain.picture;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;

public class AlbumTest {

   private final String ESTATE_ADDRESS = "address";

   private final String NO_PHOTO_AVAILABLE_MESSAGE = "You should add some photos!";

   private final String PHOTONAME = "PHOTONAME";

   @Mock
   List<String> activePictureNames;

   @Mock
   private PictureRepository pictureRepo;

   private Album album;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);
      album = new Album(ESTATE_ADDRESS, activePictureNames);
   }

   @Test
   public void whenThereAreNoAvailablePicturesFetchDefaultFromRepository() throws CouldNotAccessDataException {
      // Given no changes

      // When
      album.getSpecificPicture(NO_PHOTO_AVAILABLE_MESSAGE, pictureRepo);

      // Then
      verify(pictureRepo, times(1)).getEmptyPicture();
   }

   @Test
   public void whenAskedASpecificPictureFetchWithCorrespondingAddress() throws CouldNotAccessDataException {
      // Given no changes

      // When
      album.getSpecificPicture(PHOTONAME, pictureRepo);

      // Then
      verify(pictureRepo, times(1)).getPicture(PHOTONAME, ESTATE_ADDRESS);
   }

   @Test
   public void whenAskedForRelevantPicturesShouldReturnOnlyTheActivesPicturesNames()
         throws CouldNotAccessDataException {
      // Given no changes

      // When
      album.getRelevantPictures();

      // Then
      assertEquals(activePictureNames, album.getRelevantPictures());
   }

   @Test
   public void whenAskedForRelevantPicturesAndThereIsNoneReturnDefaultMessage() throws CouldNotAccessDataException {
      // Given an album with no active pictures
      List<String> emptyList = new ArrayList<String>();
      album = new Album(ESTATE_ADDRESS, emptyList);

      // When
      List<String> returnedList = album.getRelevantPictures();

      // Then return a list with default message
      List<String> listWithDefaultMessage = new ArrayList<String>();
      listWithDefaultMessage.add(NO_PHOTO_AVAILABLE_MESSAGE);

      assertEquals(listWithDefaultMessage, returnedList);
   }
}
