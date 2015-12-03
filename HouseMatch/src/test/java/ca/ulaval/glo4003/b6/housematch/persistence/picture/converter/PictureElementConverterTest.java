package ca.ulaval.glo4003.b6.housematch.persistence.picture.converter;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.when;

import org.dom4j.Element;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.dto.InformationPictureDto;

public class PictureElementConverterTest {

   private static final String NAME = "name";

   private static final String ADDRESS = "address";

   private static final String UUID = "UUID";

   private static final String ACTIVE = "active";

   private PictureElementConverter inactivePictureElementConverter;

   @Mock
   private Element element;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);

      inactivePictureElementConverter = new PictureElementConverter();
      configureElement();
   }

   private void configureElement() {
      when(element.elementText("uid")).thenReturn(UUID);
      when(element.elementText("address")).thenReturn(ADDRESS);
      when(element.elementText("name")).thenReturn(NAME);
      when(element.elementText("active")).thenReturn(ACTIVE);
   }

   @Test
   public void whenConvertingInactivePictureElementShouldReturnPictureDto() {
      // Given

      // When
      InformationPictureDto informationPictureDto = inactivePictureElementConverter.convertToDto(element);

      // Then
      assertEquals(UUID, informationPictureDto.getUid());
      assertEquals(ADDRESS, informationPictureDto.getAddress());
      assertEquals(NAME, informationPictureDto.getName());
      assertEquals(ACTIVE, informationPictureDto.getActive());
   }
}
