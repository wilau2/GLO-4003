package ca.ulaval.glo4003.b6.housematch.services.estate;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.EstateNotFoundException;
import ca.ulaval.glo4003.b6.housematch.domain.picture.Album;
import ca.ulaval.glo4003.b6.housematch.domain.picture.AlbumPictureRepository;
import ca.ulaval.glo4003.b6.housematch.domain.picture.PictureRepository;
import ca.ulaval.glo4003.b6.housematch.domain.picture.PictureSelector;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.estate.exceptions.InvalidEstateException;

public class EstatePicturesServiceTest {

   private static final String ADDRESS = "ADDRESS";

   private static final String PICTURENAME = "PICTURENAME";

   @Mock
   private PictureRepository pictureRepository;

   @Mock
   private AlbumPictureRepository albumPictureRepository;

   @Mock
   private PictureSelector pictureSelector;

   @Mock
   private Album album;

   @Mock
   private MultipartFile pictureFile;

   private EstatePicturesService estatesPicturesService;

   @Before
   public void setUp() throws EstateNotFoundException, CouldNotAccessDataException {
      MockitoAnnotations.initMocks(this);

      configurePictureRepository();
      configureAlbum();

      estatesPicturesService = new EstatePicturesService(pictureRepository, albumPictureRepository);
   }

   @Test
   public void gettingAPicturesShouldCallAlbumPictureRepository()
         throws InvalidEstateException, CouldNotAccessDataException, EstateNotFoundException {
      // Given no changes

      // When
      estatesPicturesService.getPicturesOfEstate(ADDRESS);

      // Then
      verify(albumPictureRepository, times(1)).getAlbum(ADDRESS);;
   }

   @Test
   public void gettingAPicturesShouldCallGetRelevantPicturesFromSelector()
         throws InvalidEstateException, CouldNotAccessDataException, EstateNotFoundException {
      // Given no changes

      // When
      estatesPicturesService.getPicturesOfEstate(ADDRESS);

      // Then
      verify(pictureSelector, times(1)).getRelevantPictures();;
   }

   @Test
   public void addingAPictureShouldCallAlbumPictureRepository()
         throws InvalidEstateException, CouldNotAccessDataException, EstateNotFoundException, IOException {
      // Given no changes

      // When
      estatesPicturesService.addPicture(ADDRESS, PICTURENAME, pictureFile);

      // Then
      verify(albumPictureRepository, times(1)).getAlbum(ADDRESS);;
   }

   @Test
   public void addingAPictureShouldCallAddPictureFromSelector()
         throws InvalidEstateException, CouldNotAccessDataException, EstateNotFoundException, IOException {
      // Given no changes

      // When
      estatesPicturesService.addPicture(ADDRESS, PICTURENAME, pictureFile);

      // Then
      verify(pictureSelector, times(1)).addPicture(PICTURENAME, pictureFile);;
   }

   @Test
   public void deletingAPictureShouldCallAlbumPictureRepository()
         throws InvalidEstateException, CouldNotAccessDataException, EstateNotFoundException, IOException {
      // Given no changes

      // When
      estatesPicturesService.deletePicture(ADDRESS, PICTURENAME);

      // Then
      verify(albumPictureRepository, times(1)).getAlbum(ADDRESS);;
   }

   @Test
   public void deletingAPictureShouldCallDeletePictureFromSelector()
         throws InvalidEstateException, CouldNotAccessDataException, EstateNotFoundException, IOException {
      // Given no changes

      // When
      estatesPicturesService.deletePicture(ADDRESS, PICTURENAME);;

      // Then
      verify(pictureSelector, times(1)).deletePicture(PICTURENAME);;
   }

   @Test
   public void gettingAPictureShouldCallAlbumPictureRepository()
         throws InvalidEstateException, CouldNotAccessDataException, EstateNotFoundException, IOException {
      // Given no changes

      // When
      estatesPicturesService.getPicture(ADDRESS, PICTURENAME);

      // Then
      verify(albumPictureRepository, times(1)).getAlbum(ADDRESS);;
   }

   @Test
   public void gettingAPictureShouldCallDeletePictureFromSelector()
         throws InvalidEstateException, CouldNotAccessDataException, EstateNotFoundException, IOException {
      // Given no changes

      // When
      estatesPicturesService.getPicture(ADDRESS, PICTURENAME);;

      // Then
      verify(pictureSelector, times(1)).getPicture(PICTURENAME);;
   }

   private void configurePictureRepository() {
      when(albumPictureRepository.getAlbum(ADDRESS)).thenReturn(album);
   }

   private void configureAlbum() {
      when(album.createCustomPictureSelector(pictureRepository)).thenReturn(pictureSelector);
   }

}
