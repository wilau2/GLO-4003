package ca.ulaval.glo4003.b6.housematch.dto;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.b6.housematch.domain.estate.Address;
import ca.ulaval.glo4003.b6.housematch.dto.AddressDto;

public class AddressDtoTest {

   private static final Integer APPARTMENT = 1;

   private static final Integer CIVI_NUMBER = 1;

   private static final String STREET = "STREET";

   private static final String POSTAL_CODE = "POSTAL_CODE";

   private static final String PROVINCE = "PROVINCE";

   private static final String COUNTRY = "COUNTRY";

   private static final Address ADDRESS = new Address(APPARTMENT, CIVI_NUMBER, STREET, POSTAL_CODE, PROVINCE, COUNTRY);

   private AddressDto addressDto;

   @Before
   public void setup() {
      addressDto = new AddressDto();

      setAddressToaddressDto();
   }

   private void setAddressToaddressDto() {
      addressDto.setAppartment(APPARTMENT);
      addressDto.setCivicNumber(CIVI_NUMBER);
      addressDto.setCountry(COUNTRY);
      addressDto.setPostalCode(POSTAL_CODE);
      addressDto.setStreet(STREET);
      addressDto.setState(PROVINCE);
   }

   @Test
   public void whenRequestingAddressFormattingShouldReturnAddressFormattedString() {
      // Given

      // When
      String addressToString = addressDto.addressToString();

      // Then
      String addressObjectAsString = ADDRESS.toString();
      String expectedAddressForModel = addressObjectAsString.replace("-", ", ");
      assertEquals(expectedAddressForModel, addressToString);
   }

   @Test
   public void whenRequestingAddressUrlShouldReturnAddressForUrlString() {
      // Given no changes

      // When
      String addressToUrl = addressDto.addressToUrl();

      // Then
      assertEquals(ADDRESS.toString(), addressToUrl);
   }
}
