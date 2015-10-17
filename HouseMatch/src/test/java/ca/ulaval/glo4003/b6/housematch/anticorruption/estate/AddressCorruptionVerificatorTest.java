package ca.ulaval.glo4003.b6.housematch.anticorruption.estate;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.anticorruption.estate.AddressCorruptionVerificator;
import ca.ulaval.glo4003.b6.housematch.anticorruption.estate.exceptions.AddressFieldInvalidException;
import ca.ulaval.glo4003.b6.housematch.dto.AddressDto;

public class AddressCorruptionVerificatorTest {

   private static final int VALID_INT = 1;

   private static final String VALID_STRING = "VALID_STRING";

   private static final String EMPTY_STRING = "";

   @Mock
   private AddressDto addressDto;

   @InjectMocks
   private AddressCorruptionVerificator addressCorruptionVerificator;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);

      configureAddressDto();
   }

   private void configureAddressDto() {
      when(addressDto.getCivicNumber()).thenReturn(VALID_INT);
      when(addressDto.getStreet()).thenReturn(VALID_STRING);
      when(addressDto.getAppartment()).thenReturn(VALID_INT);
      when(addressDto.getCountry()).thenReturn(VALID_STRING);
      when(addressDto.getPostalCode()).thenReturn(VALID_STRING);
      when(addressDto.getState()).thenReturn(VALID_STRING);
   }

   @Test
   public void verifyingAddressCorruptionWhenAllFieldsInAddressAreSetShouldReturnTrue()
         throws AddressFieldInvalidException {
      // Given no changes

      // When
      addressCorruptionVerificator.validate(addressDto);

      // Then no exception is thrown
   }

   @Test
   public void verifyingAddressCorruptionWhenAppartmentNoIsNotSetShouldNotThrowException()
         throws AddressFieldInvalidException {
      // Given
      when(addressDto.getAppartment()).thenReturn(null);

      // When
      addressCorruptionVerificator.validate(addressDto);

      // Then no exception is thrown

   }

   @Test(expected = AddressFieldInvalidException.class)
   public void verifyingAddressWhenStreetIsEmptyShouldThrowAnException() throws AddressFieldInvalidException {
      // Given
      when(addressDto.getStreet()).thenReturn(EMPTY_STRING);

      // When
      addressCorruptionVerificator.validate(addressDto);

      // Then an addressFieldInvalidException is thrown
   }

   @Test(expected = AddressFieldInvalidException.class)
   public void verifyingAddressWhenStreetIsNullShouldThrowAnException() throws AddressFieldInvalidException {
      // Given
      when(addressDto.getStreet()).thenReturn(null);

      // When
      addressCorruptionVerificator.validate(addressDto);

      // Then an AddressFieldInvalidException is thrown
   }

   @Test(expected = AddressFieldInvalidException.class)
   public void verifyingAddressWhenCivicNumberIsNegatifShouldThrowAnException() throws AddressFieldInvalidException {
      // Given
      int negativeCivicNumber = -1;
      when(addressDto.getCivicNumber()).thenReturn(negativeCivicNumber);

      // When
      addressCorruptionVerificator.validate(addressDto);

      // Then an AddressFieldInvalidExcetpion is thrown

   }

   @Test(expected = AddressFieldInvalidException.class)
   public void verifyingAddressWhenCountryIsEmptyShouldThrowException() throws AddressFieldInvalidException {
      // Given
      when(addressDto.getCountry()).thenReturn(EMPTY_STRING);

      // When
      addressCorruptionVerificator.validate(addressDto);

      // Then an AddressFieldException is thrown
   }

   @Test(expected = AddressFieldInvalidException.class)
   public void verifyingAddressWhenCountryIsNullShouldThrowException() throws AddressFieldInvalidException {
      // Given
      when(addressDto.getCountry()).thenReturn(null);

      // When
      addressCorruptionVerificator.validate(addressDto);

      // Then an AddressFieldInvalidException is thrown
   }

   @Test(expected = AddressFieldInvalidException.class)
   public void verifyingAddressWhenPostalCodeIsEmptyShouldThrowException() throws AddressFieldInvalidException {
      // Given
      when(addressDto.getPostalCode()).thenReturn(EMPTY_STRING);

      // When
      addressCorruptionVerificator.validate(addressDto);

      // Then an AddressFieldInvalidException is thrown
   }

   @Test(expected = AddressFieldInvalidException.class)
   public void verifyingAddresWhenPostalCodeIsNullShouldThrowException() throws AddressFieldInvalidException {
      // Given
      when(addressDto.getPostalCode()).thenReturn(null);
      // When
      addressCorruptionVerificator.validate(addressDto);

      // Then an AddressFieldInvalidException is thrown
   }

   @Test(expected = AddressFieldInvalidException.class)
   public void verifyingAddressWhenStateIsEmptyShouldThrowException() throws AddressFieldInvalidException {
      // Given
      when(addressDto.getState()).thenReturn(EMPTY_STRING);

      // When
      addressCorruptionVerificator.validate(addressDto);

      // Then an AddressFieldInvalidException is thrown
   }

   @Test(expected = AddressFieldInvalidException.class)
   public void verifyingAddressWhenStateIsNullShouldThrowException() throws AddressFieldInvalidException {
      // Given
      when(addressDto.getState()).thenReturn(null);

      // When
      addressCorruptionVerificator.validate(addressDto);

      // Then an AddressFieldInvalidException is thrown
   }

   @Test
   public void verifyingAddressWhenAppartmentNumberIsNullShouldSetItToZero() throws AddressFieldInvalidException {
      // Given
      when(addressDto.getAppartment()).thenReturn(null);

      // When
      addressCorruptionVerificator.validate(addressDto);

      // Then
      verify(addressDto, times(1)).setAppartment(0);
   }

   @Test(expected = AddressFieldInvalidException.class)
   public void verifyingAddressWhenAppartmentNumberIsNegativeShouldThrowException()
         throws AddressFieldInvalidException {
      // Given
      int negativeAppartmentNumber = -1;
      when(addressDto.getAppartment()).thenReturn(negativeAppartmentNumber);

      // When
      addressCorruptionVerificator.validate(addressDto);

      // Then an AddressFieldInvalidException is throw
   }
}
