package ca.ulaval.glo4003.b6.housematch.web.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static org.mockito.Matchers.any;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import ca.ulaval.glo4003.b6.housematch.estates.anticorruption.EstateCorruptionVerificator;
import ca.ulaval.glo4003.b6.housematch.estates.anticorruption.exceptions.InvalidEstateFieldException;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.EstateNotFoundException;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.SellerNotFoundException;
import ca.ulaval.glo4003.b6.housematch.estates.services.EstatesFetcher;
import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.web.converters.EstateConverter;
import ca.ulaval.glo4003.b6.housematch.web.viewModel.EstateModel;

public class SellerEstateControllerTest {

   private static final String USER_ID = "USER_ID";

   private static final String ADDRESS = "ADDRESS";

   @InjectMocks
   private SellerEstateController estateController;

   @Mock
   private EstateModel estateModel;

   @Mock
   private HttpServletRequest request;

   @Mock
   private EstateConverter estateConverter;

   @Mock
   private EstateDto estateDto;

   @Mock
   private EstatesFetcher estateFetcherService;

   @Mock
   private EstateCorruptionVerificator estateCorruptionVerificator;

   @Mock
   private Model model;

   private List<EstateDto> estatesDto;

   @Before
   public void setup() throws SellerNotFoundException, CouldNotAccessDataException, EstateNotFoundException {
      MockitoAnnotations.initMocks(this);

      when(estateConverter.convertToDto(any(EstateModel.class))).thenReturn(estateDto);

      configureFetchingListOfEstateDto();

      configureFetchingEstateByAddress();
   }

   private void configureFetchingEstateByAddress() throws EstateNotFoundException, CouldNotAccessDataException {
      when(estateFetcherService.getEstateByAddress(ADDRESS)).thenReturn(estateDto);
      when(estateConverter.convertToModel(estateDto)).thenReturn(estateModel);
   }

   private void configureFetchingListOfEstateDto() throws SellerNotFoundException, CouldNotAccessDataException {
      estatesDto = new ArrayList<EstateDto>();
      estatesDto.add(estateDto);
      estatesDto.add(estateDto);
      when(estateFetcherService.getEstatesBySeller(USER_ID)).thenReturn(estatesDto);
   }

   @Test
   public void addingEstateFromControllerWhenEstateIsValidShouldCallEstateService()
         throws InvalidEstateFieldException, CouldNotAccessDataException {
      // Given no changes

      // When
      estateController.addEstate(estateModel, USER_ID);

      // Then
      verify(estateCorruptionVerificator, times(1)).addEstate(estateDto);
   }

   @Test
   public void addingEstateFromControllerWhenEstateIsValidShouldReturnRedirectToString()
         throws InvalidEstateFieldException, CouldNotAccessDataException {
      // Given no changes
      String expectedRedirectTo = "redirect:/";

      // When
      String returnedView = estateController.addEstate(estateModel, USER_ID);

      // Then
      assertEquals(expectedRedirectTo, returnedView);
   }

   @Test(expected = InvalidEstateFieldException.class)
   public void addingEstateWhenEstateIsInvalidAddressShouldThrowException()
         throws InvalidEstateFieldException, CouldNotAccessDataException {
      // Given
      doThrow(new InvalidEstateFieldException("")).when(estateCorruptionVerificator).addEstate(estateDto);

      // When
      estateController.addEstate(estateModel, USER_ID);

      // Then an InvalidEstateFieldException is thrown
   }

   @Test
   public void whenGettingSellerPageShouldLinkToCorrectPage() {
      // Given

      // When
      String redirectLink = estateController.getSellEstatePage(model);

      // Then
      assertEquals("sell_estate", redirectLink);
   }

   @Test
   public void whenAddingAnEstateShouldSetSellerIdIntoModel()
         throws InvalidEstateFieldException, CouldNotAccessDataException {
      // Given no changes

      // When
      estateController.addEstate(estateModel, USER_ID);

      // Then
      verify(estateModel, times(1)).setSeller(USER_ID);
   }

   @Test
   public void whenFetchingEstateByTheLoggedSellerShouldReturnModelAndView()
         throws SellerNotFoundException, CouldNotAccessDataException {
      // Given

      // When
      ModelAndView returnedViewModel = estateController.getSellerEstates(USER_ID);

      // Then
      assertEquals("seller_estates", returnedViewModel.getViewName());

   }

   @Test
   public void whenFetchingEstateByTheLoggedSellerShouldCallFetchingMethodOfServiceLayer()
         throws SellerNotFoundException, CouldNotAccessDataException {
      // Given

      // When
      estateController.getSellerEstates(USER_ID);

      // Then
      verify(estateFetcherService, times(1)).getEstatesBySeller(USER_ID);
   }

   @Test
   public void whenFetchingEstateByLoggedSellerShouldCallEstateConverterToModelForAllReturnedEstateDto()
         throws SellerNotFoundException, CouldNotAccessDataException {
      // Given
      int numberOfReturnedEstateDto = estatesDto.size();
      // When
      estateController.getSellerEstates(USER_ID);

      // Then
      verify(estateConverter, times(numberOfReturnedEstateDto)).convertToModel(estateDto);
   }

   @Test
   public void whenFetchingEstateByAddressShouldCallFetchingMethodOfServiceLayer()
         throws EstateNotFoundException, CouldNotAccessDataException {
      // Given no changes

      // When
      estateController.getEstateByAddress(ADDRESS);

      // Then
      verify(estateFetcherService, times(1)).getEstateByAddress(ADDRESS);
   }

   @Test
   public void whenFetchingEstateByAddressShouldReturnModelAndViewOfWantedEstate()
         throws EstateNotFoundException, CouldNotAccessDataException {
      // Given
      String expectedViewName = "estate";

      // When
      ModelAndView returnedModelAndView = estateController.getEstateByAddress(ADDRESS);

      // Then
      assertEquals(expectedViewName, returnedModelAndView.getViewName());
      Map<String, Object> returnedModel = returnedModelAndView.getModel();
      assertTrue(returnedModel.get("estate") instanceof EstateModel);
   }

   @Test
   public void whenFetchingEstateByAddressShouldConvertEstateDtoToModel()
         throws EstateNotFoundException, CouldNotAccessDataException {
      // Given no changes

      // When
      estateController.getEstateByAddress(ADDRESS);

      // Then
      verify(estateConverter, times(1)).convertToModel(estateDto);
   }
}
