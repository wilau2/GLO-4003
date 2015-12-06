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
   private AlbumFactory albumFactory;

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

      estatesPicturesService = new EstatePicturesService(pictureRepository, albumFactory,
            approvalPictureRepository);
   }

   @Test
   public void gettingPublicPicturesShouldCallAlbumPictureRepository()
         throws InvalidEstateException, CouldNotAccessDataException {
      // Given no changes

      // When
      estatesPicturesService.getPublicPicturesOfEstate(ADDRESS);

      // Then
      verify(albumFactory, times(1)).createAlbum(names, ADDRESS);;
   }

   @Test
   public void gettingPublicPicturesShouldCallGetRelevantPicturesFromAlbum()
         throws InvalidEstateException, CouldNotAccessDataException {
      // Given no changes

      // When
      estatesPicturesService.getPublicPicturesOfEstate(ADDRESS);

      // Then
      verify(album, times(1)).getRelevantPictures();;
   }

   @Test
   public void gettingPrivatePicturesShouldCallGetInactiveEstatePicturesNames()
         throws InvalidEstateException, CouldNotAccessDataException {
      // Given no changes

      // When
      estatesPicturesService.getPrivatePicturesOfEstate(ADDRESS);

      // Then
      verify(pictures, times(1)).getInactiveEstatePicturesNames(ADDRESS);
   }

   @Test
   public void gettingPrivatePicturesShouldCallGetRelevantPicturesFromAlbum()
         throws InvalidEstateException, CouldNotAccessDataException {
      // Given no changes

      // When
      estatesPicturesService.getPublicPicturesOfEstate(ADDRESS);

      // Then
      verify(album, times(1)).getRelevantPictures();;
   }

   @Test
   public void addingAPictureShouldCallAlbumPictureRepository() throws CouldNotAccessDataException,
         PictureAlreadyExistsException, UUIDAlreadyExistsException, EstateNotFoundException {
      // Given no changes

      // When
      estatesPicturesService.addPicture(ADDRESS, PICTURENAME, pictureFile);

      // Then
      verify(albumFactory, times(1)).createAlbum(ADDRESS);;
   }

   @Test
   public void addingAPictureShouldCallAddPictureFromRepository() throws CouldNotAccessDataException,
         PictureAlreadyExistsException, UUIDAlreadyExistsException, EstateNotFoundException {

      // Given no changes

      // When
      estatesPicturesService.addPicture(ADDRESS, PICTURENAME, pictureFile);

      // Then
      verify(pictureRepository, times(1)).addPicture(PICTURENAME, ADDRESS, pictureFile);;
   }

   @Test(expected = PictureAlreadyExistsException.class)
   public void addingAPictureShouldThrowExceptionIfPicturesAlreadyExists() throws CouldNotAccessDataException,
         PictureAlreadyExistsException, UUIDAlreadyExistsException, EstateNotFoundException {
      // Given an album with existingPicture
      configureAlbumWithExistingPicture();

      // When
      estatesPicturesService.addPicture(ADDRESS, PICTURENAME, pictureFile);

      // Then Exception raised
   }

   @Test
   public void deletingAPictureShouldCallDeletePictureFromRepository()
         throws InvalidEstateException, CouldNotAccessDataException {
      // Given no changes

      // When
      estatesPicturesService.deletePicture(ADDRESS, PICTURENAME);;

      // Then
      verify(pictureRepository, times(1)).deletePicture(PICTURENAME, ADDRESS);;
   }

   @Test
   public void gettingAPictureShouldCallAlbumPictureFactory()
         throws InvalidEstateException, CouldNotAccessDataException {
      // Given no changes

      // When
      estatesPicturesService.getPicture(ADDRESS, PICTURENAME);

      // Then
      verify(albumFactory, times(1)).createAlbum(ADDRESS);;
   }

   @Test
   public void gettingAPictureShouldCallGetSpecificPictureFromAlbum()
         throws InvalidEstateException, CouldNotAccessDataException {
      // Given no changes

      // When
      estatesPicturesService.getPicture(ADDRESS, PICTURENAME);;

      // Then
      verify(album, times(1)).getSpecificPicture(PICTURENAME, pictureRepository);
   }

   private void configurePictureRepository() {
      when(albumFactory.createAlbum(ADDRESS)).thenReturn(album);
   }

   private void configureAlbumWithExistingPicture() {
      List<String> listWithExistingName = new ArrayList<String>();
      listWithExistingName.add(PICTURENAME);
      when(album.getRelevantPictures()).thenReturn(listWithExistingName);
   }

   private void configureAlbumFactory() {
      when(albumFactory.createAlbum(names, ADDRESS)).thenReturn(album);
   }

   private void configurePictures() {
      when(pictures.getActiveEstatePicturesNames(ADDRESS)).thenReturn(names);

   }

   private void configureApprovalPictureRepository() throws CouldNotAccessDataException {
      when(approvalPictureRepository.getAllPictures()).thenReturn(pictures);

   }

}
