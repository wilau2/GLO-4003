package ca.ulaval.glo4003.b6.housematch.services.estate;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.EstateNotFoundException;
import ca.ulaval.glo4003.b6.housematch.domain.picture.Album;
import ca.ulaval.glo4003.b6.housematch.domain.picture.AlbumPictureFactory;
import ca.ulaval.glo4003.b6.housematch.domain.picture.ApprovalPictureRepository;
import ca.ulaval.glo4003.b6.housematch.domain.picture.PictureRepository;
import ca.ulaval.glo4003.b6.housematch.domain.picture.PictureSelector;
import ca.ulaval.glo4003.b6.housematch.domain.picture.Pictures;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.persistence.picture.UUIDAlreadyExistsException;
import ca.ulaval.glo4003.b6.housematch.services.estate.exceptions.InvalidEstateException;
import ca.ulaval.glo4003.b6.housematch.services.estate.exceptions.PictureAlreadyExistsException;

public class EstatePicturesServiceTest {

   private static final String ADDRESS = "ADDRESS";

   private static final String PICTURENAME = "PICTURENAME";

   @Mock
   private PictureRepository pictureRepository;

   @Mock
   private AlbumPictureFactory albumPictureFactory;

   @Mock
   private PictureSelector pictureSelector;

   @Mock
   private Album album;

   @Mock
   private MultipartFile pictureFile;

   private EstatePicturesService estatesPicturesService;

   @Mock
   private ApprovalPictureRepository approvalPictureRepository;

   @Mock
   private Pictures pictures;

   @Mock
   private List<String> names;

   @Before
   public void setUp() throws EstateNotFoundException, CouldNotAccessDataException {
      MockitoAnnotations.initMocks(this);

      configurePictureRepository();
      configureApprovalPictureRepository();
      configurePictures();
      configureAlbum();
      configureAlbumFactory();

      estatesPicturesService = new EstatePicturesService(pictureRepository, albumPictureFactory,
            approvalPictureRepository);
   }

   @Test
   public void gettingAPicturesShouldCallAlbumPictureRepository()
         throws InvalidEstateException, CouldNotAccessDataException {
      // Given no changes

      // When
      estatesPicturesService.getPicturesOfEstate(ADDRESS);

      // Then
      verify(albumPictureFactory, times(1)).createAlbum(names, ADDRESS);;
   }

   @Test
   public void gettingAPicturesShouldCallGetRelevantPicturesFromSelector()
         throws InvalidEstateException, CouldNotAccessDataException {
      // Given no changes

      // When
      estatesPicturesService.getPicturesOfEstate(ADDRESS);

      // Then
      verify(pictureSelector, times(1)).getRelevantPictures();;
   }

   @Test
   public void addingAPictureShouldCallAlbumPictureRepository()
         throws CouldNotAccessDataException, PictureAlreadyExistsException, UUIDAlreadyExistsException {
      // Given no changes

      // When
      estatesPicturesService.addPicture(ADDRESS, PICTURENAME, pictureFile);

      // Then
      verify(albumPictureFactory, times(1)).createAlbum(ADDRESS);;
   }

   @Test
   public void addingAPictureShouldCallAddPictureFromSelector()
         throws CouldNotAccessDataException, PictureAlreadyExistsException, UUIDAlreadyExistsException {
      // Given no changes

      // When
      estatesPicturesService.addPicture(ADDRESS, PICTURENAME, pictureFile);

      // Then
      verify(pictureSelector, times(1)).addPicture(PICTURENAME, pictureFile);;
   }

   @Test
   public void deletingAPictureShouldCallAlbumPictureRepository()
         throws InvalidEstateException, CouldNotAccessDataException {
      // Given no changes

      // When
      estatesPicturesService.deletePicture(ADDRESS, PICTURENAME);

      // Then
      verify(albumPictureFactory, times(1)).createAlbum(ADDRESS);;
   }

   @Test
   public void deletingAPictureShouldCallDeletePictureFromSelector()
         throws InvalidEstateException, CouldNotAccessDataException {
      // Given no changes

      // When
      estatesPicturesService.deletePicture(ADDRESS, PICTURENAME);;

      // Then
      verify(pictureSelector, times(1)).deletePicture(PICTURENAME);;
   }

   @Test
   public void gettingAPictureShouldCallAlbumPictureRepository()
         throws InvalidEstateException, CouldNotAccessDataException {
      // Given no changes

      // When
      estatesPicturesService.getPicture(ADDRESS, PICTURENAME);

      // Then
      verify(albumPictureFactory, times(1)).createAlbum(ADDRESS);;
   }

   @Test
   public void gettingAPictureShouldCallDeletePictureFromSelector()
         throws InvalidEstateException, CouldNotAccessDataException {
      // Given no changes

      // When
      estatesPicturesService.getPicture(ADDRESS, PICTURENAME);;

      // Then
      verify(pictureSelector, times(1)).getPicture(PICTURENAME);;
   }

   private void configurePictureRepository() {
      when(albumPictureFactory.createAlbum(ADDRESS)).thenReturn(album);
   }

   private void configureAlbum() {

      when(album.createCustomPictureSelector(pictureRepository)).thenReturn(pictureSelector);
   }

   private void configureAlbumFactory() {
      when(albumPictureFactory.createAlbum(names, ADDRESS)).thenReturn(album);

   }

   private void configurePictures() {
      when(pictures.getActiveEstatePicturesNames(ADDRESS)).thenReturn(names);

   }

   private void configureApprovalPictureRepository() throws CouldNotAccessDataException {
      when(approvalPictureRepository.getAllPictures()).thenReturn(pictures);

   }

}
