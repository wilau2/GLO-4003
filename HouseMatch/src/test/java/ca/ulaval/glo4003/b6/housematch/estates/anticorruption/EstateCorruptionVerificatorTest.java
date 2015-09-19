package ca.ulaval.glo4003.b6.housematch.estates.anticorruption;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.estates.anticorruption.exceptions.InvalidEstateFieldException;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.InvalidEstateException;
import ca.ulaval.glo4003.b6.housematch.estates.services.EstatesService;

public class EstateCorruptionVerificatorTest {

   private static final String EMPTY_FIELD = "";

   private static final String ADDRESS = "ADDRESS";

   private static final String TYPE = "TYPE";

   private static final Integer PRICE = 1;

   @Mock
   private EstateDto estateDto;

   @Mock
   private EstatesService estateService;

   @InjectMocks
   private EstateCorruptionVerificator estateCorruptionVerificator;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);

      configureValidEstateModel();
   }

   private void configureValidEstateModel() {
      when(estateDto.getAddress()).thenReturn(ADDRESS);
      when(estateDto.getType()).thenReturn(TYPE);
      when(estateDto.getPrice()).thenReturn(PRICE);
   }

   @Test
   public void verificatingEstateCorruptionWhenEstateHasNoCorruptionShouldCallAddEstateFromService()
         throws InvalidEstateFieldException, InvalidEstateException {
      // Given no changes

      // When
      estateCorruptionVerificator.addEstate(estateDto);

      // Then
      verify(estateService, times(1)).addEstate(estateDto);
   }

   @Test(expected = InvalidEstateFieldException.class)
   public void verificationCorruptionWhenEstateAddressIsEmptyShouldThrowAnException()
         throws InvalidEstateFieldException {
      // Given
      when(estateDto.getAddress()).thenReturn(EMPTY_FIELD);

      // When
      estateCorruptionVerificator.addEstate(estateDto);

      // Then an InvalidEstateFieldException is thrown
   }

   @Test(expected = InvalidEstateFieldException.class)
   public void verifyingCorruptionWhenEstateAddressIsNullShouldThrowAnException() throws InvalidEstateFieldException {
      // Given
      when(estateDto.getAddress()).thenReturn(null);

      // When
      estateCorruptionVerificator.addEstate(estateDto);

      // Then an InvalidEstateFieldException is thrown
   }

   @Test(expected = InvalidEstateFieldException.class)
   public void verifyingCorruptionWhenEstatePriceIsNegativeShouldThrowAnException() throws InvalidEstateFieldException {
      // Given
      when(estateDto.getPrice()).thenReturn(-1);

      // When
      estateCorruptionVerificator.addEstate(estateDto);

      // Then an InvalidEstateFieldException is thrown
   }

   @Test(expected = InvalidEstateFieldException.class)
   public void verifyingCorruptionWhenEstateTypeIsEmptyShouldThrowAnException() throws InvalidEstateFieldException {
      // Given
      when(estateDto.getType()).thenReturn(EMPTY_FIELD);

      // When
      estateCorruptionVerificator.addEstate(estateDto);

      // Then an InvalidEstateFieldException is thrown
   }

   @Test(expected = InvalidEstateFieldException.class)
   public void addEstateFromCorruptionVerificatorWhenEstateTypeIsNullShouldThrowAnException()
         throws InvalidEstateFieldException {
      // Given
      when(estateDto.getType()).thenReturn(null);

      // When
      estateCorruptionVerificator.addEstate(estateDto);

      // Then an InvalidEstateFieldException is thrown
   }
}
