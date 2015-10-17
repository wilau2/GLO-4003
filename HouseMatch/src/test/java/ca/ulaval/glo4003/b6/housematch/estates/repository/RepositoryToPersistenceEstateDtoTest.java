package ca.ulaval.glo4003.b6.housematch.estates.repository;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.domain.estate.Address;
import ca.ulaval.glo4003.b6.housematch.domain.estate.Estate;

public class RepositoryToPersistenceEstateDtoTest {

   @Mock
   private Estate estate;

   @Mock
   private Address address;

   private RepositoryToPersistenceEstateDto dto;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);

      configureEstate();

      dto = new RepositoryToPersistenceEstateDto(estate);

   }

   @Test
   public void shouldReturnTheCorrespondingHasMap() {
      // Given
      HashMap<String, String> returnedMap;

      // When
      returnedMap = dto.getAttributes();

      // Then
      assertEquals(correspondingHashMap(), returnedMap);
   }

   @Test
   public void shouldReturnTheCorrespondingElementName() {
      // Given
      String elementName;

      // When
      elementName = dto.getElementName();

      // Then
      assertEquals("estate", elementName);
   }

   private void configureEstate() {
      given(estate.getAddress()).willReturn(address);
      given(address.toString()).willReturn("an address");

      given(estate.getPrice()).willReturn(10);
      given(estate.getType()).willReturn("a type");
   }

   private HashMap<String, String> correspondingHashMap() {

      HashMap<String, String> attributes = new HashMap<String, String>();

      attributes.put("type", "a type");
      attributes.put("address", "an address");
      attributes.put("price", "10");

      return attributes;
   }

}
