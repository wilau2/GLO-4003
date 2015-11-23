package ca.ulaval.glo4003.b6.housematch.dto.assembler;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
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

   private LocalDateTime dateRegistered;

   @InjectMocks
   private EstateAssembler estateAssembler;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);

      dateRegistered = LocalDateTime.of(2000, 12, 12, 12, 12);

      when(addressAssembler.assembleAddressDto(address)).thenReturn(addressDto);
      when(descriptionAssembler.assembleDescriptionDto(description)).thenReturn(descriptionDto);
      configureEstate();
   }

   private void configureEstate() {
      when(estate.getPrice()).thenReturn(PRICE);
      when(estate.getSeller()).thenReturn(SELLER);
      when(estate.getType()).thenReturn(TYPE);
      when(estate.getAddress()).thenReturn(address);
      when(estate.getDescription()).thenReturn(description);
      when(estate.hasBeenBought()).thenReturn(true);
      when(estate.getDateRegistered()).thenReturn(dateRegistered);

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
      verify(estateDto, times(1)).getDateRegistered();
      verify(estateDto, times(1)).isBought();
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

   @Test
   public void whenAssemblingAnEstateDtoFromAnEstateShouldCreateValidEstateDto() {
      // Given

      // When
      estateAssembler.assembleEstateDto(estate);

      // Then
      verify(estate, times(1)).getAddress();
      verify(estate, times(1)).getPrice();
      verify(estate, times(1)).getType();
      verify(estate, times(1)).getSeller();
      verify(estate, times(1)).getDateRegistered();
      verify(estate, times(1)).hasBeenBought();
   }

}
