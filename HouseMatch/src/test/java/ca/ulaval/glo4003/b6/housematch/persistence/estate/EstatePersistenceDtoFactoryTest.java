package ca.ulaval.glo4003.b6.housematch.persistence.estate;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class EstatePersistenceDtoFactoryTest {

   @InjectMocks
   private EstatePersistenceDtoFactory estatePersistenceDtoFactory;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);
   }

   @Test
   public void whenCreatingNewDtoInstanceShouldReturnDtoWithAttributesInside() {
      // Given
      HashMap<String, String> expectedAttributes = new HashMap<String, String>();

      // When
      EstatePersistenceDto estatePersistenceDto = estatePersistenceDtoFactory.newInstanceEstate(expectedAttributes);

      // Then
      assertEquals(expectedAttributes, estatePersistenceDto.getAttributes());
   }
}
