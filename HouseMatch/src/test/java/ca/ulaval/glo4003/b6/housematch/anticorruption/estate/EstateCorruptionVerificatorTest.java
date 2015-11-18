package ca.ulaval.glo4003.b6.housematch.anticorruption.estate;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.anticorruption.estate.exceptions.AddressFieldInvalidException;
import ca.ulaval.glo4003.b6.housematch.anticorruption.estate.exceptions.InvalidEstateFieldException;
import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.EstateNotFoundException;
import ca.ulaval.glo4003.b6.housematch.dto.AddressDto;
import ca.ulaval.glo4003.b6.housematch.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.dto.EstateEditDto;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.estate.EstatesService;
import ca.ulaval.glo4003.b6.housematch.services.estate.exceptions.InvalidEstateException;

public class EstateCorruptionVerificatorTest {

   private static final String USER_ID = "USER_ID";

   private static final String EMPTY_FIELD = "";

   private static final String TYPE = "TYPE";

   private static final Integer PRICE = 1;
   
   private static final String ADDRESS_KEY = "ADDRESS_KEY";

   @Mock
   private EstateDto estateDto;
   
   @Mock
   private EstateEditDto estateEditDto;

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
      
      when(estateEditDto.getType()).thenReturn(TYPE);
      when(estateEditDto.getPrice()).thenReturn(PRICE);
   }

   @Test
   public void verificatingEstateCorruptionWhenEstateHasNoCorruptionShouldCallAddEstateFromService()
         throws InvalidEstateFieldException, InvalidEstateException, CouldNotAccessDataException {
      // Given no changes

      // When
      estateCorruptionVerificator.addEstate(estateDto);

      // Then
      verify(estateService, times(1)).addEstate(estateDto);
   }

   @Test(expected = InvalidEstateFieldException.class)
   public void addingEstateFromCorruptionVerificatorWhenEstateServiceThrowExceptionShouldThrowException()
         throws InvalidEstateFieldException, CouldNotAccessDataException, InvalidEstateException {
      // Given
      doThrow(new InvalidEstateException("")).when(estateService).addEstate(estateDto);

      // When
      estateCorruptionVerificator.addEstate(estateDto);

      // Then an InvalidEstateFieldException is thrown
   }

   @Test(expected = InvalidEstateFieldException.class)
   public void verificationCorruptionWhenEstateAddressIsEmptyShouldThrowAnException()
         throws InvalidEstateFieldException, AddressFieldInvalidException, CouldNotAccessDataException {
      // Given
      doThrow(new AddressFieldInvalidException("")).when(addressCorruptionVerificator).validate(addressDto);

      // When
      estateCorruptionVerificator.addEstate(estateDto);

      // Then an InvalidEstateFieldException is thrown
   }

   @Test(expected = InvalidEstateFieldException.class)
   public void verifyingCorruptionWhenEstateAddressIsNullShouldThrowAnException()
         throws InvalidEstateFieldException, AddressFieldInvalidException, CouldNotAccessDataException {
      // Given
      when(estateDto.getAddress()).thenReturn(null);

      // When
      estateCorruptionVerificator.addEstate(estateDto);

      // Then an InvalidEstateFieldException is thrown
   }

   @Test(expected = InvalidEstateFieldException.class)
   public void verifyingCorruptionWhenEstatePriceIsNegativeShouldThrowAnException()
         throws InvalidEstateFieldException, CouldNotAccessDataException {
      // Given
      when(estateDto.getPrice()).thenReturn(-1);

      // When
      estateCorruptionVerificator.addEstate(estateDto);

      // Then an InvalidEstateFieldException is thrown
   }

   @Test(expected = InvalidEstateFieldException.class)
   public void verifyingCorruptionWhenEstateTypeIsEmptyShouldThrowAnException()
         throws InvalidEstateFieldException, CouldNotAccessDataException {
      // Given
      when(estateDto.getType()).thenReturn(EMPTY_FIELD);

      // When
      estateCorruptionVerificator.addEstate(estateDto);

      // Then an InvalidEstateFieldException is thrown
   }

   @Test(expected = InvalidEstateFieldException.class)
   public void addEstateFromCorruptionVerificatorWhenEstateTypeIsNullShouldThrowAnException()
         throws InvalidEstateFieldException, CouldNotAccessDataException {
      // Given
      when(estateDto.getType()).thenReturn(null);

      // When
      estateCorruptionVerificator.addEstate(estateDto);

      // Then an InvalidEstateFieldException is thrown
   }

   @Test(expected = InvalidEstateFieldException.class)
   public void addingEstateFromCorruptionVerificatorWhenUserIdIsEmptyShouldThrowAnException()
         throws InvalidEstateFieldException, CouldNotAccessDataException {
      // Given
      when(estateDto.getSeller()).thenReturn(EMPTY_FIELD);

      // When
      estateCorruptionVerificator.addEstate(estateDto);

      // Then an InvalidEstateFieldException is thrown
   }

   @Test(expected = InvalidEstateFieldException.class)
   public void addingEstateFromCorruptionVerificatorWhenSellerIdIsNullShouldThrowException()
         throws InvalidEstateFieldException, CouldNotAccessDataException {
      // Given
      when(estateDto.getSeller()).thenReturn(null);

      // When
      estateCorruptionVerificator.addEstate(estateDto);

      // Then an InvalidEstateFieldException is thrown
   }

   @Test
   public void whenAddingEstateFromCorruptionVerificatorShouldCallAddressCorruptionVerificator()
         throws InvalidEstateFieldException, AddressFieldInvalidException, CouldNotAccessDataException {
      // Given

      // When
      estateCorruptionVerificator.addEstate(estateDto);

      // Then
      verify(addressCorruptionVerificator, times(1)).validate(addressDto);
   }
   
   @Test
   public void verificatingEditEstateCorruptionWhenEditEstateHasNoCorruptionShouldCallEditEstateFromService()
         throws InvalidEstateFieldException, CouldNotAccessDataException, EstateNotFoundException {
      // Given

      // When
      estateCorruptionVerificator.editEstate(ADDRESS_KEY, estateEditDto);

      // Then
      verify(estateService, times(1)).editEstate(ADDRESS_KEY, estateEditDto);
   }
   
   @Test(expected = InvalidEstateFieldException.class)
   public void editingEstateFromCorruptionVerificatorWhenEstateTypeIsNullShouldThrowAnException()
         throws InvalidEstateFieldException, CouldNotAccessDataException, EstateNotFoundException {
      // Given
      when(estateEditDto.getType()).thenReturn(null);

      // When
      estateCorruptionVerificator.editEstate(ADDRESS_KEY, estateEditDto);

      // Then an InvalidEstateFieldException is thrown
   }

   @Test(expected = InvalidEstateFieldException.class)
   public void editingEstateFromCorruptionVerificatorWhenEstateTypeIsEmptyShouldThrowAnException()
         throws InvalidEstateFieldException, CouldNotAccessDataException, EstateNotFoundException {
      // Given
      when(estateEditDto.getType()).thenReturn(EMPTY_FIELD);

      // When
      estateCorruptionVerificator.editEstate(ADDRESS_KEY, estateEditDto);

      // Then an InvalidEstateFieldException is thrown
   }
   
   @Test(expected = InvalidEstateFieldException.class)
   public void editingEstateFromCorruptionVerificatorWhenPriceIsNegativeShouldThrowException()
         throws InvalidEstateFieldException, CouldNotAccessDataException, EstateNotFoundException {
      // Given
      when(estateEditDto.getPrice()).thenReturn(-1);

      // When
      estateCorruptionVerificator.editEstate(ADDRESS_KEY, estateEditDto);

      // Then an InvalidEstateFieldException is thrown
   }

}
