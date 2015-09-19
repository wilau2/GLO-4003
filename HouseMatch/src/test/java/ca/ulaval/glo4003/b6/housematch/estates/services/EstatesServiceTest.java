package ca.ulaval.glo4003.b6.housematch.estates.services;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.estates.dto.validators.EstateValidator;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.InvalidEstateException;

import org.mockito.Mock;

import static org.mockito.Mockito.verify;

public class EstatesServiceTest {

   private String VALID_TYPE_ESTATE = "CONDO";

   private String INVALID_TYPE_ESTATE = "invalid";

   private String VALID_ADDRESS = "2-128 rue untel, Quebec";

   private Integer VALID_PRICE = 125000;

   @Mock
   private EstateValidator estateValidator;

   private EstatesService estatesService;

   @Before
   public void setUp() {
      MockitoAnnotations.initMocks(this);
      estatesService = new EstatesService(estateValidator);
   }

   @Test
   public void addingAnEstateForSellingWhenEstateIsValidShouldNotThrowException() throws InvalidEstateException {
      // Given no changes

      // When
      estatesService.addEstate(VALID_TYPE_ESTATE, INVALID_TYPE_ESTATE, VALID_PRICE);

      // Then no exception is thrown
   }

   @Test
   public void addingAnEstateShouldCallTheFunctionValidateOnValidator() throws InvalidEstateException {
      // Given

      // When
      estatesService.addEstate(INVALID_TYPE_ESTATE, VALID_ADDRESS, VALID_PRICE);

      // Then
      verify(estateValidator).validate(INVALID_TYPE_ESTATE, VALID_ADDRESS, VALID_PRICE);
   }

   @Ignore
   @Test
   public void addingAnEstateShouldCallTheEstateControllerAddEstateFunction() {

   }
}
