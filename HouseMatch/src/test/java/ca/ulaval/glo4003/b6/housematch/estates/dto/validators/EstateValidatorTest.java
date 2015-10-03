package ca.ulaval.glo4003.b6.housematch.estates.dto.validators;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.InvalidEstateException;

public class EstateValidatorTest {

   private static final String INVALID_TYPE_ESTATE = "invalid";

   private static final String VALID_TYPE_ESTATE = "CONDO";

   private static final Integer VALID_PRICE = 125000;

   private EstateValidator estateValidator;

   @Mock
   private EstateDto estateDto;

   @Before
   public void setUp() {
      MockitoAnnotations.initMocks(this);

      estateValidator = new EstateValidator();

      configureValidEstateDto();
   }

   private void configureValidEstateDto() {

      // when(estateDto.getAddress()).thenReturn(VALID_ADDRESS);
      when(estateDto.getPrice()).thenReturn(VALID_PRICE);
      when(estateDto.getType()).thenReturn(VALID_TYPE_ESTATE);

   }

   @Test
   public void validatingEstateWhenEstateIsValidShouldNotThrowException() throws InvalidEstateException {

      // Given no changes

      // When
      estateValidator.validate(estateDto);

      // Then no exception is thrown
   }

   @Test(expected = InvalidEstateException.class)
   public void validatingEstateWhenTypeIsInvalidShouldThrowException() throws InvalidEstateException {
      // Given
      when(estateDto.getType()).thenReturn(INVALID_TYPE_ESTATE);

      // When
      estateValidator.validate(estateDto);

      // Then an InvalidEstateException is thrown
   }

}
