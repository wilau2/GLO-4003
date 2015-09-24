package ca.ulaval.glo4003.b6.housematch.web.converters;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.web.viewModel.EstateModel;

public class EstateConverterTest {

   private static final String ADDRESS = "ADDRESS";

   private static final String TYPE = "TYPE";

   private static final Integer PRICE = 1;

   private static final String USER_ID = "USER_ID";

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
      when(estateModel.getAddress()).thenReturn(ADDRESS);
      when(estateModel.getPrice()).thenReturn(PRICE);
      when(estateModel.getSeller()).thenReturn(USER_ID);
   }

   @Test
   public void whenConvertingEstateModelToEstateDtoShouldReturnDtoWithAllFieldSet() {
      // Given no changes

      // When
      EstateDto returnedEstateDto = estateConverter.convertToDto(estateModel);

      // Then
      assertEquals(ADDRESS, returnedEstateDto.getAddress());
      assertEquals(TYPE, returnedEstateDto.getType());
      assertEquals(PRICE, returnedEstateDto.getPrice());
      assertEquals(USER_ID, returnedEstateDto.getSeller());
   }
}
