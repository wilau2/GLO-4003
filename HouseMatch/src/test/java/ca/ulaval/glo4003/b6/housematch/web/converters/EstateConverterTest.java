package ca.ulaval.glo4003.b6.housematch.web.converters;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.estates.dto.AddressDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.web.viewModel.EstateModel;

public class EstateConverterTest {

   private static final String ADDRESS = "ADDRESS";

   private static final String TYPE = "TYPE";

   private static final Integer PRICE = 1;

   private static final String USER_ID = "USER_ID";

   private static final String POSTAL_CODE = "POSTAL_CODE";

   private static final Integer APPARTMENT = 1;

   private static final String COUNTRY = "COUNTRY";

   private static final String STATE = "STATE";

   private static final String STREET = "STREET";

   private static final Integer CIVIC_NUMBER = 1;

   @Mock
   public EstateModel estateModel;

   private EstateConverter estateConverter;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);

      estateConverter = new EstateConverter();

      configureEstateModel();
   }

   private void configureEstateModel() {
      when(estateModel.getType()).thenReturn(TYPE);
      when(estateModel.getPrice()).thenReturn(PRICE);
      when(estateModel.getSeller()).thenReturn(USER_ID);

      when(estateModel.getPostalCode()).thenReturn(POSTAL_CODE);
      when(estateModel.getCivicNumber()).thenReturn(CIVIC_NUMBER);
      when(estateModel.getCountry()).thenReturn(COUNTRY);
      when(estateModel.getState()).thenReturn(STATE);
      // when(estateModel)
   }

   @Test
   public void whenConvertingEstateModelToEstateDtoShouldReturnDtoWithAllFieldSet() {
      // Given no changes

      // When
      EstateDto returnedEstateDto = estateConverter.convertToDto(estateModel);

      // Then
      assertEquals(TYPE, returnedEstateDto.getType());
      assertEquals(PRICE, returnedEstateDto.getPrice());
      assertEquals(USER_ID, returnedEstateDto.getSeller());
   }

   @Test
   public void whenConvertingEstateModelToAnEstateDtoShouldConstructAddressDto() {
      // Given no changes

      // When
      EstateDto estateDto = estateConverter.convertToDto(estateModel);
      AddressDto addressDto = estateDto.getAddress();

      // Then
      assertEquals(POSTAL_CODE, addressDto.getPostalCode());
      assertEquals(COUNTRY, addressDto.getCountry());
      assertEquals(STATE, addressDto.getState());
      assertEquals(STREET, addressDto.getStreet());
      assertEquals(CIVIC_NUMBER, addressDto.getCivicNumber());
      assertEquals(APPARTMENT, addressDto.getAppartment());
   }
}
