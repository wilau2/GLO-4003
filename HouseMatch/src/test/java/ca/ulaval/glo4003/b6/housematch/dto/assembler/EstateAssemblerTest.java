package ca.ulaval.glo4003.b6.housematch.dto.assembler;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.domain.estate.Address;
import ca.ulaval.glo4003.b6.housematch.domain.estate.Description;
import ca.ulaval.glo4003.b6.housematch.domain.estate.Estate;
import ca.ulaval.glo4003.b6.housematch.dto.AddressDto;
import ca.ulaval.glo4003.b6.housematch.dto.DescriptionDto;
import ca.ulaval.glo4003.b6.housematch.dto.EstateDto;

public class EstateAssemblerTest {

   private static final String TYPE = "TYPE";

   private static final Integer PRICE = 1;

   private static final String SELLER = "SELLER";

   @Mock
   private EstateDto estateDto;

   @Mock
   private Estate estate;

   @Mock
   private AddressDto addressDto;

   @Mock
   private DescriptionDto descriptionDto;

   @Mock
   private AddressAssembler addressAssembler;

   @Mock
   private Address address;

   @Mock
   private Description description;

   @Mock
   private DescriptionAssembler descriptionAssembler;

   private EstateAssembler estateAssembler;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);

      when(addressAssembler.assembleAddressDto(address)).thenReturn(addressDto);
      when(descriptionAssembler.assembleDescriptionDto(description)).thenReturn(descriptionDto);

      estateAssembler = new EstateAssembler(addressAssembler, descriptionAssembler);

      configureEstate();
   }

   private void configureEstate() {
      when(estate.getPrice()).thenReturn(PRICE);
      when(estate.getSeller()).thenReturn(SELLER);
      when(estate.getType()).thenReturn(TYPE);
      when(estate.getAddress()).thenReturn(address);
      when(estate.getDescription()).thenReturn(description);
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
      assertEquals(PRICE, returnedEstateDto.getPrice());
   }

   @Test
   public void whenAssemblingAnEstateDtoFromAnEstateShouldCallAddressAssembler() {
      // Given no changes

      // When
      estateAssembler.assembleEstateDto(estate);

      // Then
      verify(addressAssembler, times(1)).assembleAddressDto(address);
   }
}