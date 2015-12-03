package ca.ulaval.glo4003.b6.housematch.persistence.picture;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.domain.picture.Picture;

public class InactivePicturePersistanceDtoTest {

   private final String ADDRESS = "address";

   private final String UID = "uid";

   private final String NAME = "a new Username";

   private final String ELEMENT_NAME = "inactivePicture";

   @Mock
   private Picture picture;

   private final String ACTIVE = "true";

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);
   }

   @Test
   public void givenValidPictureWhenGetAttributesShouldReturnGoodAttributes() {
      // Given
      configurePicture();
      InactivePicturePersistanceDto inactivePicturePersistanceDto = new InactivePicturePersistanceDto(picture);

      // When
      HashMap<String, String> attributes = inactivePicturePersistanceDto.getAttributes();

      // Then
      assertTrue(attributes.get("active").equals(ACTIVE));
      assertTrue(attributes.get("address").equals(ADDRESS));
      assertTrue(attributes.get("name").equals(NAME));
      assertTrue(attributes.get("uid").equals(UID));
   }

   @Test
   public void givenValidPictureWhenGetElementNameShouldReturnIt() {
      // Given
      configurePicture();
      InactivePicturePersistanceDto inactivePicturePersistanceDto = new InactivePicturePersistanceDto(picture);

      // When
      String elementName = inactivePicturePersistanceDto.getElementName();

      // Then
      assertTrue(elementName.equals(ELEMENT_NAME));

   }

   private void configurePicture() {
      given(picture.isActive()).willReturn(true);
      given(picture.getAddress()).willReturn(ADDRESS);
      given(picture.getName()).willReturn(NAME);
      given(picture.getUid()).willReturn(UID);

   }

}
