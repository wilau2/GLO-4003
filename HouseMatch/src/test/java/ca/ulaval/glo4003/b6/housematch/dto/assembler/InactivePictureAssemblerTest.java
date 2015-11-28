package ca.ulaval.glo4003.b6.housematch.dto.assembler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.domain.picture.Picture;
import ca.ulaval.glo4003.b6.housematch.dto.InformationPictureDto;

public class InactivePictureAssemblerTest {

   private static final String ADDRESS = "address";

   private static final String NAME = "name";

   private static final String UID = "uid";

   private InactivePictureAssembler inactivePictureAssembler;

   @Mock
   private InformationPictureDto pictureDto;

   @Mock
   private Picture picture;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);

      inactivePictureAssembler = new InactivePictureAssembler();
   }

   @Test
   public void whenAssemblingPictureShouldReturnPictureAssembled() {
      // Given
      configureInactivePictureDto();

      // When
      Picture assembleInactivePicture = inactivePictureAssembler.assembleInactivePicture(pictureDto);

      // Then
      assertTrue(assembleInactivePicture.getActive());
      assertEquals(ADDRESS, assembleInactivePicture.getAddress());
      assertEquals(NAME, assembleInactivePicture.getName());
      assertEquals(UID, assembleInactivePicture.getUid());
   }

   private void configureInactivePictureDto() {

      when(pictureDto.getActive()).thenReturn("true");
      when(pictureDto.getAddress()).thenReturn(ADDRESS);
      when(pictureDto.getName()).thenReturn(NAME);
      when(pictureDto.getUid()).thenReturn(UID);
   }

   @Test
   public void whenAssemblingPictureDtoFromPictureShouldAssemblePictureCorrectly() {
      // Given
      configurePicture();

      // When
      InformationPictureDto assembleInactivePictureDto = inactivePictureAssembler.assembleInactivePictureDto(picture);

      // Then
      assertEquals("true", assembleInactivePictureDto.getActive());
      assertEquals(ADDRESS, assembleInactivePictureDto.getAddress());
      assertEquals(NAME, assembleInactivePictureDto.getName());
      assertEquals(UID, assembleInactivePictureDto.getUid());
   }

   private void configurePicture() {
      when(picture.getActive()).thenReturn(true);
      when(picture.getAddress()).thenReturn(ADDRESS);
      when(picture.getName()).thenReturn(NAME);
      when(picture.getUid()).thenReturn(UID);
   }
}
