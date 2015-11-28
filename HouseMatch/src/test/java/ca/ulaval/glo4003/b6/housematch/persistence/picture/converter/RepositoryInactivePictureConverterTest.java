package ca.ulaval.glo4003.b6.housematch.persistence.picture.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static org.mockito.Mockito.when;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.domain.picture.Picture;

public class RepositoryInactivePictureConverterTest {

   private static final String UID = "uid";

   private static final String NAME = "name";

   private static final String ADDRESS = "address";

   private RepositoryInactivePictureConverter repositoryPictureConverter;

   @Mock
   private HashMap<String, String> attributes;

   @Before
   public void setup() {
      repositoryPictureConverter = new RepositoryInactivePictureConverter();

      MockitoAnnotations.initMocks(this);

      configureAttributes();
   }

   private void configureAttributes() {
      when(attributes.get("address")).thenReturn(ADDRESS);
      when(attributes.get("name")).thenReturn(NAME);
      when(attributes.get("uid")).thenReturn(UID);
      when(attributes.get("active")).thenReturn("true");

   }

   @Test
   public void whenConvertingAttributesToPictureShouldSetPictureFieldCorrectly() {
      // Given no changes

      // When
      Picture assembledPicture = repositoryPictureConverter.assembleInactivePictureFromAttributes(attributes);

      // Then
      assertTrue(assembledPicture.getActive());
      assertEquals(ADDRESS, assembledPicture.getAddress());
      assertEquals(NAME, assembledPicture.getName());
      assertEquals(UID, assembledPicture.getUid());
   }
}
