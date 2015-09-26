package ca.ulaval.glo4003.b6.housematch.estates.persistences;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.when;

import java.util.HashMap;

import org.dom4j.Element;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Address;
import ca.ulaval.glo4003.b6.housematch.estates.domain.Estate;
import ca.ulaval.glo4003.b6.housematch.estates.dto.AddressDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;

public class EstateElementAssemblerTest {

   private static final String COUNTRY = "TEST_COUNTRY";

   private static final String PROVINCE = "Test";

   private static final String POSTAL_CODE = "123453";

   private static final String STREET = "Test, Street";

   private static final Integer CIVI_NUMBER = 2;

   private static final Integer APPARTMENT = 2;

   private static final String USER_ID = "USER_ID";

   private static final String TYPE = "TYPE";

   private static final Address ADDRESS = new Address(APPARTMENT, CIVI_NUMBER, STREET, POSTAL_CODE, PROVINCE, COUNTRY);

   private static final Integer PRICE = 1;

   @Mock
   private Element element;

   @Mock
   private Estate estate;

   @InjectMocks
   private EstateElementAssembler estateElementAssembler;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);

      configureElement();

      configureEstate();
   }

   private void configureEstate() {
      when(estate.getSeller()).thenReturn(USER_ID);
      when(estate.getPrice()).thenReturn(PRICE);
      when(estate.getType()).thenReturn(TYPE);
      when(estate.getAddress()).thenReturn(ADDRESS);
   }

   private void configureElement() {
      when(element.attributeValue("type")).thenReturn(TYPE);
      when(element.attributeValue("price")).thenReturn(PRICE.toString());
      when(element.attributeValue("seller")).thenReturn(USER_ID);
      when(element.attributeValue("address")).thenReturn(ADDRESS.toString());
   }

   @Test
   public void assemblingEstateDtoWhenElementHasAllElementShouldCreateDtoWithAllFieldsSet() {
      // Given no changes

      // When
      EstateDto estateDto = estateElementAssembler.convertToDto(element);

      // Then
      assertEquals(TYPE, estateDto.getType());

      assertEquals(PRICE, estateDto.getPrice());
      assertEquals(USER_ID, estateDto.getSeller());
   }

   @Test
   public void assemblingAddressDtoWhenAssemblingEstateDtoShouldCreateAddressDtoWithAllFieldsSet() {
      // Given no changes

      // When
      EstateDto estateDto = estateElementAssembler.convertToDto(element);
      AddressDto addressDto = estateDto.getAddress();

      // Then
      // assertEquals(ADDRESS.toString(), addressDto.toString());
   }

   @Test
   public void assemblingEstateAttributesWhenEstateHasAllElementShouldCreateHashMapWithAllEstateFields() {
      // Given

      // When
      HashMap<String, String> returnedAttributes = estateElementAssembler.convertToAttributes(estate);

      // Then
      assertEquals(USER_ID, returnedAttributes.get("seller"));
      assertEquals(PRICE.toString(), returnedAttributes.get("price"));
      assertEquals(TYPE, returnedAttributes.get("type"));
   }

   @Test
   public void assemblingEstateAttributesWhenEstateHasAllElementsShouldCreateHashMapWithAddressElement() {
      // Given no changes

      // When
      HashMap<String, String> returnedAttributes = estateElementAssembler.convertToAttributes(estate);

      // Then
      assertEquals(ADDRESS.toString(), returnedAttributes.get("address"));
   }
}
