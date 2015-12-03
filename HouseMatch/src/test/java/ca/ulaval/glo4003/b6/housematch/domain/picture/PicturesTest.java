package ca.ulaval.glo4003.b6.housematch.domain.picture;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PicturesTest {

   @Mock
   Picture picture;

   @Mock
   List<Picture> pictureList;

   @InjectMocks
   Pictures pictures;

   @Mock
   Iterator<Picture> iterator;

   private final String ADDRESS = "address";

   private final String NAME = "name";

   private final String UID = "uid";

   private final String INVALID_NAME = "invalid name";

   private final String INVALID_UID = "uid2";

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);
   }

   @Test
   public void givenOneActivePictureInListWhenGetActivePicturesShoudlReturnOneActivePicture() {
      // Given
      configureActivePictureList();

      // When
      List<Picture> rep = pictures.getActivePictures();

      // Then
      assertEquals(1, rep.size());
   }

   @Test
   public void givenOneInactivePictureInListWhenGetActivePicturesShoudlReturnNoActivePicture() {
      // Given
      configureInactivePictureList();

      // When
      List<Picture> rep = pictures.getActivePictures();

      // Then
      assertEquals(0, rep.size());
   }

   @Test
   public void givenEmptyPictureInListWhenGetActivePicturesShouldReturnNoActivePicture() {
      // Given
      configureEmptyPictureList();

      // When
      List<Picture> rep = pictures.getActivePictures();

      // Then
      assertEquals(0, rep.size());
   }

   @Test
   public void givenOneActivePictureInListWhenGetInactivePicturesShouldReturnNoActivePicture() {
      // Given
      configureActivePictureList();

      // When
      List<Picture> rep = pictures.getInactivePictures();

      // Then
      assertEquals(0, rep.size());
   }

   @Test
   public void givenOneInactivePictureInListWhenGetInactivePicturesShouldReturnOneActivePicture() {
      // Given
      configureInactivePictureList();

      // When
      List<Picture> rep = pictures.getInactivePictures();

      // Then
      assertEquals(1, rep.size());
   }

   @Test
   public void givenEmptyPictureInListWhenGetInactivePicturesShouldReturnNoActivePicture() {
      // Given
      configureEmptyPictureList();

      // When
      List<Picture> rep = pictures.getInactivePictures();

      // Then
      assertEquals(0, rep.size());
   }

   @Test
   public void givenEstatePictureInListWhenGetEstatePictureUidShouldReturnValidUid() {
      // Given
      congigureEstatePictureList();

      // When
      String rep = pictures.getEstatePictureUid(ADDRESS, NAME);

      // Then
      assertTrue(UID.equals(rep));
   }

   @Test
   public void givenNotGoodAdressEstatePictureInListWhenGetEstatePictureUidShouldReturnEmptyUid() {
      // Given
      congigureEstatePictureList();
      when(picture.isFromEstate(ADDRESS)).thenReturn(false);

      // When
      String rep = pictures.getEstatePictureUid(ADDRESS, NAME);

      // Then
      assertTrue(rep.isEmpty());
   }

   @Test
   public void givenNotGoodNameEstatePictureInListWhenGetEstatePictureUidShouldReturnEmptyUid() {
      // Given
      congigureEstatePictureList();
      when(picture.getName()).thenReturn(INVALID_NAME);

      // When
      String rep = pictures.getEstatePictureUid(ADDRESS, NAME);

      // Then
      assertTrue(rep.isEmpty());
   }

   @Test
   public void givenEmptyEstatePictureInListWhenGetEstatePictureUidShoudlReturnEmptyUid() {
      // Given
      configureEmptyPictureList();

      // When
      String rep = pictures.getEstatePictureUid(ADDRESS, NAME);

      // Then
      assertTrue(rep.isEmpty());
   }

   @Test
   public void givenEmptyActiveEstatePictureInListWhenGetActiveEstatePicturesNamesShouldReturnEmptyList() {
      // Given
      configureEmptyPictureList();

      // When
      List<String> rep = pictures.getActiveEstatePicturesNames(ADDRESS);

      // Then
      assertTrue(rep.isEmpty());
   }

   @Test
   public void givenActiveEstatePictureWithValidAddressInListWhenGetActiveEstatePicturesNamesShouldReturnValidName() {
      // Given
      configureActivePictureList();
      when(picture.isFromEstate(ADDRESS)).thenReturn(true);
      when(picture.getName()).thenReturn(NAME);

      // When
      List<String> rep = pictures.getActiveEstatePicturesNames(ADDRESS);

      // Then
      assertEquals(NAME, rep.get(0));
   }

   @Test
   public void givenActiveEstatePictureWithValidAddressInListWhenGetInactiveEstatePicturesNamesShouldNoValidName() {
      // Given
      configureActivePictureList();
      when(picture.isFromEstate(ADDRESS)).thenReturn(true);
      when(picture.getName()).thenReturn(NAME);

      // When
      List<String> rep = pictures.getInactiveEstatePicturesNames(ADDRESS);

      // Then
      assertTrue(rep.isEmpty());
   }

   @Test
   public void givenInactiveEstatePictureWithValidAddressInListWhenGetInactiveEstatePicturesNamesShouldReturnName() {
      // Given
      configureInactivePictureList();
      when(picture.isFromEstate(ADDRESS)).thenReturn(true);
      when(picture.getName()).thenReturn(NAME);

      // When
      List<String> rep = pictures.getInactiveEstatePicturesNames(ADDRESS);

      // Then
      assertEquals(NAME, rep.get(0));
   }

   @Test
   public void givenActiveEstatePictureWithInvalidAddressInListWhenGetActiveEstatePicturesNamesShouldReturnNoName() {
      // Given
      configureActivePictureList();
      when(picture.isFromEstate(ADDRESS)).thenReturn(false);

      // When
      List<String> rep = pictures.getActiveEstatePicturesNames(ADDRESS);

      // Then
      assertTrue(rep.isEmpty());
   }

   @Test
   public void givenListThatContaisUidWhenGetPicturesFromUidsShouldReturnPicture() {
      // Given
      congigureEstatePictureList();
      List<String> uidList = new ArrayList<String>();
      uidList.add(UID);

      // When
      List<Picture> rep = pictures.getPicturesFromUids(uidList);

      // Then
      assertFalse(rep.isEmpty());
   }

   @Test
   public void givenListThatDoesNotContaisUidWhenGetPicturesFromUidsShouldReturnPicture() {
      // Given
      congigureEstatePictureList();
      List<String> uidList = new ArrayList<String>();
      uidList.add(INVALID_UID);

      // When
      List<Picture> rep = pictures.getPicturesFromUids(uidList);

      // Then
      assertTrue(rep.isEmpty());
   }

   @Test
   public void givenInactivePictureListWhenActivatePicturesFromUidsShouldReturnActivatedPicture() {
      // Given
      configureInactivePictureList();
      when(picture.getUid()).thenReturn(UID);
      List<String> uidList = new ArrayList<String>();
      uidList.add(UID);

      // When
      List<Picture> rep = pictures.activatePicturesFromUids(uidList);

      // Then
      assertFalse(rep.isEmpty());
   }

   @Test
   public void givenInactivePictureListButWrongUidWhenActivatePicturesFromUidsShouldReturnNothing() {
      // Given
      configureInactivePictureList();
      when(picture.getUid()).thenReturn(UID);
      List<String> uidList = new ArrayList<String>();
      uidList.add(INVALID_UID);

      // When
      List<Picture> rep = pictures.activatePicturesFromUids(uidList);

      // Then
      assertTrue(rep.isEmpty());
   }

   private void congigureEstatePictureList() {
      configureEstatePicture();
      congigurePictureListWithOneElement();
   }

   private void configureEstatePicture() {
      when(picture.isFromEstate(ADDRESS)).thenReturn(true);
      when(picture.getName()).thenReturn(NAME);
      when(picture.getUid()).thenReturn(UID);
   }

   private void configureEmptyPictureList() {
      when(pictureList.iterator()).thenReturn(iterator);
      when(iterator.hasNext()).thenReturn(false);
   }

   private void configureActivePictureList() {
      configureActivePicture();
      congigurePictureListWithOneElement();

   }

   private void configureActivePicture() {
      when(picture.isActive()).thenReturn(true);
   }

   private void configureInactivePictureList() {
      configureInactivePicture();
      congigurePictureListWithOneElement();
   }

   private void configureInactivePicture() {
      when(picture.isActive()).thenReturn(false);
   }

   private void congigurePictureListWithOneElement() {
      when(pictureList.iterator()).thenReturn(iterator);
      when(iterator.hasNext()).thenReturn(true, false);
      when(iterator.next()).thenReturn(picture);
   }

}
