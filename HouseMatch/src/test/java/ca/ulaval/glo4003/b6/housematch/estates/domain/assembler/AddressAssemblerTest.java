package ca.ulaval.glo4003.b6.housematch.estates.domain.assembler;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.domain.estate.Address;
import ca.ulaval.glo4003.b6.housematch.dto.AddressDto;

public class AddressAssemblerTest {

   private static final String COUNTRY = "COUNTRY";

   private static final String STATE = "STATE";

   private static final Integer APPARTMENT = 1;

   private static final Integer CIVIC_NUMBER = 1;

   private static final String POSTAL_CODE = "POSTAL_CODE";

   private static final String STREET = "STREET";

   @Mock
   private AddressDto addressDto;

   @Mock
   private Address address;

   private AddressAssembler addressAssembler;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);

      addressAssembler = new AddressAssembler();

      configureAddressDto();

      configureAddress();
   }

   private void configureAddress() {
      when(address.getAppartment()).thenReturn(APPARTMENT);
      when(address.getCivicNumber()).thenReturn(CIVIC_NUMBER);
      when(address.getCountry()).thenReturn(COUNTRY);
      when(address.getPostalCode()).thenReturn(POSTAL_CODE);
      when(address.getState()).thenReturn(STATE);
      when(address.getStreet()).thenReturn(STREET);
   }

   private void configureAddressDto() {
      when(addressDto.getAppartment()).thenReturn(APPARTMENT);
      when(addressDto.getCivicNumber()).thenReturn(CIVIC_NUMBER);
      when(addressDto.getCountry()).thenReturn(COUNTRY);
      when(addressDto.getPostalCode()).thenReturn(POSTAL_CODE);
      when(addressDto.getState()).thenReturn(STATE);
      when(addressDto.getStreet()).thenReturn(STREET);

   }

   @Test
   public void whenAssemblingAddressFromDtoShouldReturnSetAllFieldsCorrectly() {
      // Given no changes

      // When
      Address returnedAddress = addressAssembler.assembleAddress(addressDto);

      // Then
      assertEquals(COUNTRY, returnedAddress.getCountry());
      assertEquals(STATE, returnedAddress.getState());
      assertEquals(POSTAL_CODE, returnedAddress.getPostalCode());
      assertEquals(STREET, returnedAddress.getStreet());
      assertEquals(CIVIC_NUMBER, returnedAddress.getCivicNumber());
      assertEquals(APPARTMENT, returnedAddress.getAppartment());
   }

   @Test
   public void whenAssemblingAddressDtoFromAddressShouldSetAllFieldsCorrectly() {
      // Given no changes

      // When
      AddressDto returnedAddressDto = addressAssembler.assembleAddressDto(address);

      // Then
      assertEquals(COUNTRY, returnedAddressDto.getCountry());
      assertEquals(STATE, returnedAddressDto.getState());
      assertEquals(POSTAL_CODE, returnedAddressDto.getPostalCode());
      assertEquals(STREET, returnedAddressDto.getStreet());
      assertEquals(CIVIC_NUMBER, returnedAddressDto.getCivicNumber());
      assertEquals(APPARTMENT, returnedAddressDto.getAppartment());
   }

}
