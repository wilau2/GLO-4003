package ca.ulaval.glo4003.b6.housematch.persistence.estate.converter;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;

import org.dom4j.Element;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.domain.estate.Address;
import ca.ulaval.glo4003.b6.housematch.domain.estate.Estate;
import ca.ulaval.glo4003.b6.housematch.dto.AddressDto;
import ca.ulaval.glo4003.b6.housematch.dto.EstateDto;

public class EstateElementConverterTest {

   private static final String ADDRESS_KEY = "address";

   private static final String SELLER_KEY = "seller";

   private static final String PRICE_KEY = "price";

   private static final String TYPE_KEY = "type";
   
   private static final String PRICE_HISTORY_KEY = "price_history";

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
   
   private static ArrayList<Integer> PRICE_HISTORY = new ArrayList<Integer>();
   
   private static final String PRICE_HISTORY_STRING = "100-200";

   @Mock
   private Element element;

   @Mock
   private Estate estate;

   @Mock
   private HashMap<String, String> attributes;

   @InjectMocks
   private EstateElementConverter estateElementAssembler;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);

      configureElement();

      configureEstate();

      configureAttributes();
   }

   private void configureAttributes() {
      when(attributes.get(TYPE_KEY)).thenReturn(TYPE);
      when(attributes.get(PRICE_KEY)).thenReturn(PRICE.toString());
      when(attributes.get(SELLER_KEY)).thenReturn(USER_ID);
      when(attributes.get(ADDRESS_KEY)).thenReturn(ADDRESS.toString());
      when(attributes.get(PRICE_HISTORY_KEY)).thenReturn(PRICE_HISTORY_STRING);
   }

   private void configureEstate() {
      when(estate.getSeller()).thenReturn(USER_ID);
      when(estate.getPrice()).thenReturn(PRICE);
      when(estate.getType()).thenReturn(TYPE);
      when(estate.getAddress()).thenReturn(ADDRESS);
      when(estate.getPriceHistory()).thenReturn(PRICE_HISTORY);
   }

   private void configureElement() {
      when(element.elementText(TYPE_KEY)).thenReturn(TYPE);
      when(element.elementText(PRICE_KEY)).thenReturn(PRICE.toString());
      when(element.elementText(SELLER_KEY)).thenReturn(USER_ID);
      when(element.elementText(ADDRESS_KEY)).thenReturn(ADDRESS.toString());
      when(element.elementText(PRICE_HISTORY_KEY)).thenReturn(PRICE_HISTORY_STRING);
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
      assertEquals(ADDRESS.getAppartment(), addressDto.getAppartment());
      assertEquals(ADDRESS.getCivicNumber(), addressDto.getCivicNumber());
      assertEquals(ADDRESS.getStreet(), addressDto.getStreet());
      assertEquals(ADDRESS.getState(), addressDto.getState());
      assertEquals(ADDRESS.getCountry(), addressDto.getCountry());
      assertEquals(ADDRESS.getPostalCode(), addressDto.getPostalCode());
   }

   @Test
   public void assemblingEstateAttributesWhenEstateHasAllElementShouldCreateHashMapWithAllEstateFields() {
      // Given

      // When
      HashMap<String, String> returnedAttributes = estateElementAssembler.convertToAttributes(estate);

      // Then
      assertEquals(USER_ID, returnedAttributes.get(SELLER_KEY));
      assertEquals(PRICE.toString(), returnedAttributes.get(PRICE_KEY));
      assertEquals(TYPE, returnedAttributes.get(TYPE_KEY));
   }

   @Test
   public void assemblingEstateAttributesWhenEstateHasAllElementsShouldCreateHashMapWithAddressElement() {
      // Given no changes

      // When
      HashMap<String, String> returnedAttributes = estateElementAssembler.convertToAttributes(estate);

      // Then
      assertEquals(ADDRESS.toString(), returnedAttributes.get(ADDRESS_KEY));
   }

   @Test
   public void assemblingEstateDtoFromAttributesWhenAllAttributesArePresentShouldSetAllEstateDtoFields() {
      // Given no changes

      // When
      EstateDto assembledEstateDto = estateElementAssembler.convertAttributesToDto(attributes);

      // Then
      assertEquals(USER_ID, assembledEstateDto.getSeller());
      assertEquals(PRICE, assembledEstateDto.getPrice());
      assertEquals(TYPE, assembledEstateDto.getType());
   }

   @Test
   public void assemblingEstateDtoFromAttributesWhenAttributesContainsAddressShouldSetAllAddressDtoFields() {
      // Given

      // When
      EstateDto convertedEstateDto = estateElementAssembler.convertAttributesToDto(attributes);
      AddressDto addressDto = convertedEstateDto.getAddress();

      // Then
      assertEquals(APPARTMENT, addressDto.getAppartment());
      assertEquals(CIVI_NUMBER, addressDto.getCivicNumber());
      assertEquals(STREET, addressDto.getStreet());
      assertEquals(PROVINCE, addressDto.getState());
      assertEquals(COUNTRY, addressDto.getCountry());
      assertEquals(POSTAL_CODE, addressDto.getPostalCode());
   }
}
