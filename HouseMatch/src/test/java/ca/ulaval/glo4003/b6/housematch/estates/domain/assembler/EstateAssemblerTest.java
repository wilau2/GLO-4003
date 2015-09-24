package ca.ulaval.glo4003.b6.housematch.estates.domain.assembler;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Estate;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;

public class EstateAssemblerTest {

   private static final String ADDRESS = "ADDRESS";

   private static final String TYPE = "TYPE";

   private static final Integer PRICE = 1;

   private static final String SELLER = "SELLER";

   @InjectMocks
   private EstateAssembler estateAssembler;

   @Mock
   private EstateDto estateDto;

   @Mock
   private Estate estate;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);

      configureEstate();
   }

   private void configureEstate() {
      when(estate.getAddress()).thenReturn(ADDRESS);
      when(estate.getPrice()).thenReturn(PRICE);
      when(estate.getSeller()).thenReturn(SELLER);
      when(estate.getType()).thenReturn(TYPE);
   }

   @Test
   public void whenAssemblingAnEstateFromAnEstateDtoShouldCreateValidEstate() {
      // Given

      // When
      estateAssembler.assembleEstate(estateDto);

      // Then
      verify(estateDto, times(1)).getAddress();
      verify(estateDto, times(1)).getPrice();
      verify(estateDto, times(1)).getType();
      verify(estateDto, times(1)).getSeller();
   }

   @Test
   public void whenAssemblingAnEstateDtoFromAnEstateShouldSetCorrectlyAllFieldOfDto() {
      // Given no changes

      // When
      EstateDto returnedEstateDto = estateAssembler.assembleEstateDto(estate);

      // Then
      assertEquals(SELLER, returnedEstateDto.getSeller());
      assertEquals(TYPE, returnedEstateDto.getType());
      assertEquals(ADDRESS, returnedEstateDto.getAddress());
      assertEquals(PRICE, returnedEstateDto.getPrice());
   }
}
