package ca.ulaval.glo4003.b6.housematch.estates.domain.assembler;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.EstateAssembler;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;

public class EstateAssemblerTest {

   private static final String VALID_TYPE_ESTATE = "CONDO";

   private static final String INVALID_TYPE_ESTATE = "INVALID_ESTATE";

   private static final String VALID_ADDRESS = "1257 av. Jean-michel";

   private static final String INVALID_ADDRESS = "";

   private static final Integer VALID_PRICE = 1257;

   private static final Integer INVALID_PRICE = -10;

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
