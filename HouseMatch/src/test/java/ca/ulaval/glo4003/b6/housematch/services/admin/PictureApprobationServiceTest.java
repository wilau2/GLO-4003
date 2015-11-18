package ca.ulaval.glo4003.b6.housematch.services.admin;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.domain.picture.ApprovalPictureRepository;
import ca.ulaval.glo4003.b6.housematch.domain.picture.Picture;
import ca.ulaval.glo4003.b6.housematch.domain.picture.PictureRepository;
import ca.ulaval.glo4003.b6.housematch.domain.picture.Pictures;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.persistence.picture.UUIDAlreadyExistsException;

public class PictureApprobationServiceTest {

   private final String UID = "uid";

   private final String ADDRESS = "address";

   private final String NAME = "name";

   @Mock
   private ApprovalPictureRepository approvalPictureRepository;

   @Mock
   private PictureRepository pictureRepository;

   @InjectMocks
   PictureApprobationService pictureApprobationService;

   @Mock
   private Pictures pictures;

   @Mock
   private Picture picture;

   @Mock
   private List<String> pictureUids;

   @Mock
   private List<Picture> pictureList;

   private byte[] bytes;

   @Mock
   private Iterator<String> iteratorStr;

   @Mock
   private Iterator<Picture> iterator;

   @Before
   public void setup() throws CouldNotAccessDataException {
      MockitoAnnotations.initMocks(this);
      configureApprovalPictureRepository();
      configurePictures();
      configurePictureRepository();
      configurePicture();
   }

   private void configureApprovalPictureRepository() throws CouldNotAccessDataException {
      given(approvalPictureRepository.getAllPictures()).willReturn(pictures);
      given(approvalPictureRepository.getPictureByUid(UID)).willReturn(picture);
      given(approvalPictureRepository.getPicturesByUids(pictureUids)).willReturn(pictureList);
   }

   private void configurePictureRepository() throws CouldNotAccessDataException {
      given(pictureRepository.getPicture(NAME, ADDRESS)).willReturn(bytes);
   }

   private void configurePicture() {
      given(picture.getName()).willReturn(NAME);
      given(picture.getAddress()).willReturn(ADDRESS);
   }

   private void configurePictures() {
      given(pictures.getInactivePictures()).willReturn(pictureList);
      given(pictures.activatePicturesFromUids(pictureUids)).willReturn(pictureList);
   }

   private void congigureUidListWithOneElement() {
      when(pictureUids.iterator()).thenReturn(iteratorStr);
      when(iteratorStr.hasNext()).thenReturn(true, false);
      when(iteratorStr.next()).thenReturn(UID);
   }

   private void congigurePictueListWithOneElement() {
      when(pictureList.iterator()).thenReturn(iterator);
      when(iterator.hasNext()).thenReturn(true, false);
      when(iterator.next()).thenReturn(picture);
   }

   @Test
   public void givenValidRepositoryWhenGetAllInactivePicturesShouldReturnPictureList()
         throws CouldNotAccessDataException {
      // Given

      // When
      List<Picture> rep = pictureApprobationService.getAllInactivePictures();

      // Then
      assertEquals(pictureList, rep);
   }

   @Test
   public void givenValidRepositoryWhenGetAllInactivePicturesShouldDelegateGettingPictureInformation()
         throws CouldNotAccessDataException {
      // Given

      // When
      pictureApprobationService.getAllInactivePictures();

      // Then
      verify(approvalPictureRepository).getAllPictures();
   }

   @Test
   public void givenValidRepositoryWhenGetAllInactivePicturesShouldDelegateFilteringPictures()
         throws CouldNotAccessDataException {
      // Given

      // When
      pictureApprobationService.getAllInactivePictures();

      // Then
      verify(pictures).getInactivePictures();
   }

   @Test
   public void givenValidRepositoryWhenApprouvePicturesShouldDelegate()
         throws CouldNotAccessDataException, UUIDAlreadyExistsException {
      // Given

      // When
      pictureApprobationService.approuvePictures(pictureUids);

      // Then
      verify(approvalPictureRepository).getAllPictures();
      verify(pictures).activatePicturesFromUids(pictureUids);
      verify(approvalPictureRepository).updatePictures(pictureList);
   }

   @Test
   public void givenValidRepositoryWhenGetInactivePictureContentShouldDelegate()
         throws CouldNotAccessDataException, UUIDAlreadyExistsException {
      // Given

      // When
      pictureApprobationService.getInactivePictureContent(UID);

      // Then
      verify(approvalPictureRepository).getPictureByUid(UID);
      verify(pictureRepository).getPicture(NAME, ADDRESS);
   }

   @Test
   public void whenUnapprouvePicturesShouldDelegate() throws CouldNotAccessDataException, UUIDAlreadyExistsException {
      // Given
      congigureUidListWithOneElement();
      congigurePictueListWithOneElement();

      // When
      pictureApprobationService.unapprouvePictures(pictureUids);

      // Then
      verify(approvalPictureRepository).deletePicture(UID);
      verify(pictureRepository).deletePicture(NAME, ADDRESS);
   }
}
