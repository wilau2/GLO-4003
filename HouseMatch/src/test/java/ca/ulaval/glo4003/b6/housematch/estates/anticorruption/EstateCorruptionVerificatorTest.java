package ca.ulaval.glo4003.b6.housematch.estates.anticorruption;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.estates.anticorruption.exceptions.AddressFieldInvalidException;
import ca.ulaval.glo4003.b6.housematch.estates.anticorruption.exceptions.InvalidEstateFieldException;
import ca.ulaval.glo4003.b6.housematch.estates.dto.AddressDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.InvalidEstateException;
import ca.ulaval.glo4003.b6.housematch.estates.services.EstatesService;

public class EstateCorruptionVerificatorTest {

   private static final String USER_ID = "USER_ID";

   private static final String EMPTY_FIELD = "";

   private static final String ADDRESS = "ADDRESS";

   private static final String TYPE = "TYPE";

   private static final Integer PRICE = 1;

   @Mock
   private EstateDto estateDto;

   @Mock
   private EstatesService estateService;

   @Mock
   private AddressDto addressDto;

   @Mock
   private AddressCorruptionVerificator addressCorruptionVerificator;

   @InjectMocks
   private EstateCorruptionVerificator estateCorruptionVerificator;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);

      configureValidEstateModel();
   }

   private void configureValidEstateModel() {
      when(estateDto.getType()).thenReturn(TYPE);
      when(estateDto.getPrice()).thenReturn(PRICE);
      when(estateDto.getSeller()).thenReturn(USER_ID);

      when(estateDto.getAddress()).thenReturn(addressDto);
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
         throws InvalidEstateFieldException, AddressFieldInvalidException {
      // Given
      doThrow(new AddressFieldInvalidException("")).when(addressCorruptionVerificator).validate(addressDto);

      // When
      estateCorruptionVerificator.addEstate(estateDto);

      // Then an InvalidEstateFieldException is thrown
   }

   @Test(expected = InvalidEstateFieldException.class)
   public void verifyingCorruptionWhenEstateAddressIsNullShouldThrowAnException()
         throws InvalidEstateFieldException, AddressFieldInvalidException {
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

   @Test(expected = InvalidEstateFieldException.class)
   public void addingEstateFromCorruptionVerificatorWhenUserIdIsEmptyShouldThrowAnException()
         throws InvalidEstateFieldException {
      // Given
      when(estateDto.getSeller()).thenReturn(EMPTY_FIELD);

      // When
      estateCorruptionVerificator.addEstate(estateDto);

      // Then an InvalidEstateFieldException is thrown
   }

   @Test(expected = InvalidEstateFieldException.class)
   public void addingEstateFromCorruptionVerificatorWhenSellerIdIsNullShouldThrowException()
         throws InvalidEstateFieldException {
      // Given
      when(estateDto.getSeller()).thenReturn(null);

      // When
      estateCorruptionVerificator.addEstate(estateDto);

      // Then an InvalidEstateFieldException is thrown
   }

   @Test
   public void whenAddingEstateFromCorruptionVerificatorShouldCallAddressCorruptionVerificator()
         throws InvalidEstateFieldException, AddressFieldInvalidException {
      // Given

      // When
      estateCorruptionVerificator.addEstate(estateDto);

      // Then
      verify(addressCorruptionVerificator, times(1)).validate(addressDto);
   }

}
