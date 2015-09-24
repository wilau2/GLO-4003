package ca.ulaval.glo4003.b6.housematch.estates.domain.assembler;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;

public class EstateAssemblerTest {

   private EstateAssembler estateAssembler;

   @Mock
   private EstateDto estateDto;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);

      estateAssembler = new EstateAssembler();
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
   }
}
