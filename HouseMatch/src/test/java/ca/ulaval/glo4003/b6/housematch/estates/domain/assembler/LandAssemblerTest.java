package ca.ulaval.glo4003.b6.housematch.estates.domain.assembler;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Land;
import ca.ulaval.glo4003.b6.housematch.estates.dto.LandDto;

public class LandAssemblerTest {

   private static final String AQUEDUCT = "Dump";
   private static final String DIMENSION_LOT = "200x200";
   
   @Mock
   private LandDto landDto;
   
   @Mock
   private Land land;
   
   @InjectMocks
   private LandAssembler landAssembler;
   
   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);

      configureLand();
   }
   
   private void configureLand() {
      when(land.getAqueduct()).thenReturn(AQUEDUCT);
      when(land.getDimensionsLot()).thenReturn(DIMENSION_LOT);
   }
   
   @Test
   public void whenAssemblingAnLandDtoFromAnEstateShouldSetCorrectlyAllFieldOfDto() {
      // Given 

      // When
      LandDto returnedLandDto = landAssembler.assembleLandDto(land);

      // Then
      assertEquals(AQUEDUCT, returnedLandDto.getAqueduct());
      assertEquals(DIMENSION_LOT, returnedLandDto.getDimensionsLot());
   }
}
