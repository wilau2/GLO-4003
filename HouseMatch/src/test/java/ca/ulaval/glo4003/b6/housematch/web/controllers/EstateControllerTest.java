package ca.ulaval.glo4003.b6.housematch.web.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.estates.anticorruption.EstateCorruptionVerificator;
import ca.ulaval.glo4003.b6.housematch.estates.anticorruption.exceptions.InvalidEstateFieldException;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.web.converters.EstateConverter;
import ca.ulaval.glo4003.b6.housematch.web.viewModel.EstateModel;

public class EstateControllerTest {

   @InjectMocks
   private EstateController estateController;

   @Mock
   private EstateModel estateModel;

   @Mock
   private HttpServletRequest request;

   @Mock
   private EstateConverter estateConverter;

   @Mock
   private EstateDto estateDto;

   @Mock
   private EstateCorruptionVerificator estateCorruptionVerificator;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);

      when(estateConverter.convertToDto(any(EstateModel.class))).thenReturn(estateDto);
   }

   @Test
   public void addingEstateFromControllerWhenEstateIsValidShouldCallEstateService() throws InvalidEstateFieldException {
      // Given no changes

      // When
      estateController.addEstate(request, estateModel);

      // Then
      verify(estateCorruptionVerificator, times(1)).addEstate(estateDto);
   }

   @Test
   public void addingEstateFromControllerWhenEstateIsValidShouldReturnRedirectToString()
         throws InvalidEstateFieldException {
      // Given no changes
      String expectedRedirectTo = "redirect:/";

      // When
      String returnedView = estateController.addEstate(request, estateModel);

      // Then
      assertEquals(expectedRedirectTo, returnedView);
   }

   @Test(expected = InvalidEstateFieldException.class)
   public void addingEstateWhenEstateIsInvalidAddressShouldThrowException() throws InvalidEstateFieldException {
      // Given
      doThrow(new InvalidEstateFieldException()).when(estateCorruptionVerificator).addEstate(estateDto);

      // When
      estateController.addEstate(request, estateModel);

      // Then an InvalidEstateFieldException is thrown
   }
}
