package ca.ulaval.glo4003.b6.housematch.estates.assembler;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.estates.assembler.EstateAssembler;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.validators.EstateValidator;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.InvalidEstateException;

import org.mockito.Mock;

import static org.mockito.Mockito.doThrow;

public class EstatesAssemblerTest {

   private String VALID_TYPE_ESTATE = "CONDO";

   private String INVALID_TYPE_ESTATE = "INVALID_ESTATE";

   private String VALID_ADDRESS = "1257 av. Jean-michel";

   private String INVALID_ADDRESS = "";

   private Integer VALID_PRICE = 1257;

   private Integer INVALID_PRICE = -10;

   @Mock
   private EstateValidator estateValidator;

   private EstateAssembler estateAssembler;

   @Before
   public void setUp() {
      MockitoAnnotations.initMocks(this);
      estateAssembler = new EstateAssembler(estateValidator);

   }

   @Test(expected = InvalidEstateException.class)
   public void createDtoWithInvalidTypeShouldThrowException() throws InvalidEstateException {
      // Given
      doThrow(new InvalidEstateException()).when(estateValidator).validate(INVALID_TYPE_ESTATE, VALID_ADDRESS,
            VALID_PRICE);
      // When
      this.estateAssembler.createDTO(INVALID_TYPE_ESTATE, VALID_ADDRESS, VALID_PRICE);
      // Then handle by expected exception
   }

   @Test(expected = InvalidEstateException.class)
   public void createDtoWithInvalidAddressShouldThrowException() throws InvalidEstateException {
      // Given
      doThrow(new InvalidEstateException()).when(estateValidator).validate(VALID_TYPE_ESTATE, INVALID_ADDRESS,
            VALID_PRICE);
      // When
      this.estateAssembler.createDTO(VALID_TYPE_ESTATE, INVALID_ADDRESS, VALID_PRICE);
      // Then handle by expected exception
   }

   @Test(expected = InvalidEstateException.class)
   public void createDtoWithInvalidPriceShouldThrowException() throws InvalidEstateException {
      // Given
      doThrow(new InvalidEstateException()).when(estateValidator).validate(VALID_TYPE_ESTATE, VALID_ADDRESS,
            INVALID_PRICE);
      // When
      this.estateAssembler.createDTO(VALID_TYPE_ESTATE, VALID_ADDRESS, INVALID_PRICE);
      // Then handle by expected exception
   }

   @Test
   public void createDtoWithValidInformationReturnsDTO() throws InvalidEstateException {
      // Given

      // When
      Object object = this.estateAssembler.createDTO(VALID_TYPE_ESTATE, VALID_ADDRESS, VALID_PRICE);

      // Then
      assertTrue(object.getClass() == EstateDto.class);
   }
}
