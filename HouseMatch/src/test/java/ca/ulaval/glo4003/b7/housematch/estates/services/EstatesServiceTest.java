package ca.ulaval.glo4003.b7.housematch.estates.services;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b7.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b7.housematch.estates.dto.validators.EstateValidator;
import ca.ulaval.glo4003.b7.housematch.estates.exceptions.InvalidEstateException;

import org.mockito.Mock;

import static org.mockito.Mockito.doThrow;

public class EstatesServiceTest {

   private EstateDto estateDto;
   private String INVALID_TYPE_ESTATE = "invalid";
   private String VALID_ADDRESS = "2-128 rue untel, Quebec";
   private Integer VALID_PRICE = 125000; 

   @Mock
   private EstateValidator estateValidator;

   @InjectMocks
   private EstatesService estatesService;

   @Before
   public void setUp() {
      MockitoAnnotations.initMocks(this);

      // Creating valid EstateDto
      estateDto = new EstateDto();

   }

   @Test
   public void addingAnEstateForSellingWhenEstateIsValidShouldNotThrowException() throws InvalidEstateException {
      // Given no changes

      // When
      estatesService.addEstate(estateDto);

      // Then no exception is thrown
   }

   @Ignore
   @Test(expected = InvalidEstateException.class)
   public void addingAnEstateWhenEstateTypeIsInvalidShouldThrowException() throws InvalidEstateException {
      // Given
      estateDto.setType("Invalid type");
      doThrow(new InvalidEstateException()).when(estateValidator).validate(INVALID_TYPE_ESTATE, VALID_ADDRESS, VALID_PRICE);

      // When
      estatesService.addEstate(estateDto);

      // Then an InvalidEstateException is thrown
   }
}
