package ca.ulaval.glo4003.b6.housematch.services.picture;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.EstateNotFoundException;
import ca.ulaval.glo4003.b6.housematch.domain.picture.Album;
import ca.ulaval.glo4003.b6.housematch.domain.picture.AlbumFactory;
import ca.ulaval.glo4003.b6.housematch.domain.picture.ApprovalPictureRepository;
import ca.ulaval.glo4003.b6.housematch.domain.picture.PictureRepository;
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
   private AlbumFactory albumPictureFactory;

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
   public void gettingAPicturesShouldCallGetRelevantPicturesFromAlbum()
         throws InvalidEstateException, CouldNotAccessDataException {
      // Given no changes

      // When
      estatesPicturesService.getPicturesOfEstate(ADDRESS);

      // Then
      verify(album, times(1)).getRelevantPictures();;
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
   public void addingAPictureShouldCallAddPictureFromRepository()
         throws CouldNotAccessDataException, PictureAlreadyExistsException, UUIDAlreadyExistsException {
      // Given no changes

      // When
      estatesPicturesService.addPicture(ADDRESS, PICTURENAME, pictureFile);

      // Then
      verify(pictureRepository, times(1)).addPicture(PICTURENAME, ADDRESS, pictureFile);;
   }

   @Test(expected = PictureAlreadyExistsException.class)
   public void addingAPictureShouldThrowExceptionIfPicturesAlreadyExists()
         throws CouldNotAccessDataException, PictureAlreadyExistsException, UUIDAlreadyExistsException {
      // Given an album with existingPicture
      configureAlbumWithExistingPicture();

      // When
      estatesPicturesService.addPicture(ADDRESS, PICTURENAME, pictureFile);

      // Then Exception raised
   }

   @Test
   public void deletingAPictureShouldCallDeletePictureRepository()
         throws InvalidEstateException, CouldNotAccessDataException {
      // Given no changes

      // When
      estatesPicturesService.deletePicture(ADDRESS, PICTURENAME);;

      // Then
      verify(pictureRepository, times(1)).deletePicture(PICTURENAME, ADDRESS);;
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
   public void gettingAPictureShouldCallDeletePictureFromAlbum()
         throws InvalidEstateException, CouldNotAccessDataException {
      // Given no changes

      // When
      estatesPicturesService.getPicture(ADDRESS, PICTURENAME);;

      // Then
      verify(album, times(1)).getSpecificPicture(PICTURENAME, pictureRepository);
   }

   private void configurePictureRepository() {
      when(albumPictureFactory.createAlbum(ADDRESS)).thenReturn(album);
   }

   private void configureAlbumWithExistingPicture() {
      List<String> listWithExistingName = new ArrayList<String>();
      listWithExistingName.add(PICTURENAME);
      when(album.getRelevantPictures()).thenReturn(listWithExistingName);
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
