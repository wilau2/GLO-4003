package ca.ulaval.glo4003.b6.housematch.web.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
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

import ca.ulaval.glo4003.b6.housematch.anticorruption.estate.EstateCorruptionVerificator;
import ca.ulaval.glo4003.b6.housematch.anticorruption.estate.exceptions.InvalidEstateFieldException;
import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.EstateNotFoundException;
import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.SellerNotFoundException;
import ca.ulaval.glo4003.b6.housematch.dto.DescriptionDto;
import ca.ulaval.glo4003.b6.housematch.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.estate.EstateRepositoryFactory;
import ca.ulaval.glo4003.b6.housematch.services.estate.EstatesFetcher;
import ca.ulaval.glo4003.b6.housematch.services.user.UserAuthorizationService;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.InvalidAccessException;

public class SellerEstateControllerTest {

   private static final String USER_ID = "USER_ID";

   private static final String ADDRESS = "ADDRESS";

   @InjectMocks
   private SellerEstateController estateController;

   @Mock
   private HttpServletRequest request;

   @Mock
   private EstateDto estateDto;

   @Mock
   private DescriptionDto descriptionDto;

   @Mock
   private EstatesFetcher estateFetcherService;

   @Mock
   private EstateCorruptionVerificator estateCorruptionVerificator;

   @Mock
   private Model model;

   @Mock
   private UserAuthorizationService userAuthorizationService;

   private List<EstateDto> estatesDto;

   @Mock
   private EstateRepositoryFactory estateRepositoryFactory;

   @Mock
   private EstateAssemblerFactory estateAssemblerFactory;

   @Before
   public void setup()
         throws SellerNotFoundException, CouldNotAccessDataException, EstateNotFoundException, InvalidAccessException {
      MockitoAnnotations.initMocks(this);

      configureFetchingListOfEstateDto();

      configureFetchingEstateByAddress();

   }

   private void configureFetchingEstateByAddress() throws EstateNotFoundException, CouldNotAccessDataException {
      when(estateFetcherService.getEstateByAddress(ADDRESS)).thenReturn(estateDto);
   }

   private void configureFetchingListOfEstateDto() throws SellerNotFoundException, CouldNotAccessDataException {
      estatesDto = new ArrayList<EstateDto>();
      estatesDto.add(estateDto);
      estatesDto.add(estateDto);
      when(estateFetcherService.getEstatesBySeller(USER_ID)).thenReturn(estatesDto);
   }

   @Test
   public void addingEstateFromControllerWhenEstateIsValidShouldCallEstateService()
         throws InvalidEstateFieldException, CouldNotAccessDataException, InvalidAccessException {
      // Given no changes

      // When
      estateController.addEstate(request, estateDto, USER_ID);

      // Then
      verify(estateCorruptionVerificator, times(1)).addEstate(estateDto);
   }

   @Test
   public void addingEstateFromControllerWhenEstateIsValidShouldReturnRedirectToString()
         throws InvalidEstateFieldException, CouldNotAccessDataException, InvalidAccessException {
      // Given no changes
      String expectedRedirectTo = "redirect:/seller/" + USER_ID + "/estates";

      // When
      String returnedView = estateController.addEstate(request, estateDto, USER_ID);

      // Then
      assertEquals(expectedRedirectTo, returnedView);
   }

   @Test(expected = InvalidEstateFieldException.class)
   public void addingEstateWhenEstateIsInvalidAddressShouldThrowException()
         throws InvalidEstateFieldException, CouldNotAccessDataException, InvalidAccessException {
      // Given
      doThrow(new InvalidEstateFieldException("")).when(estateCorruptionVerificator).addEstate(estateDto);

      // When
      estateController.addEstate(null, estateDto, USER_ID);

      // Then an InvalidEstateFieldException is thrown
   }

   @Test
   public void whenGettingSellerPageShouldLinkToCorrectPage() throws InvalidAccessException {
      // Given

      // When
      String redirectLink = estateController.getSellEstatePage(null, model);

      // Then
      assertEquals("sell_estate", redirectLink);
   }

   @Test
   public void whenAddingAnEstateShouldSetSellerIdIntoModel()
         throws InvalidEstateFieldException, CouldNotAccessDataException, InvalidAccessException {
      // Given no changes

      // When
      estateController.addEstate(request, estateDto, USER_ID);

      // Then
      verify(estateDto, times(1)).setSellerId(USER_ID);
   }

   @Test
   public void whenFetchingEstateByTheLoggedSellerShouldReturnModelAndView()
         throws SellerNotFoundException, CouldNotAccessDataException, InvalidAccessException {
      // Given

      // When
      ModelAndView returnedViewModel = estateController.getSellerEstates(USER_ID, request);

      // Then
      assertEquals("seller_estates", returnedViewModel.getViewName());

   }

   @Test
   public void whenFetchingEstateByTheLoggedSellerShouldCallFetchingMethodOfServiceLayer()
         throws SellerNotFoundException, CouldNotAccessDataException, InvalidAccessException {
      // Given

      // When
      estateController.getSellerEstates(USER_ID, request);

      // Then
      verify(estateFetcherService, times(1)).getEstatesBySeller(USER_ID);
   }

   @Test
   public void whenFetchingEstateByAddressShouldCallFetchingMethodOfServiceLayer()
         throws EstateNotFoundException, CouldNotAccessDataException, InvalidAccessException, IOException {
      // Given no changes

      // When
      estateController.getEstateByAddress(ADDRESS, request);

      // Then
      verify(estateFetcherService, times(1)).getEstateByAddress(ADDRESS);
   }

   @Test
   public void whenFetchingEstateByAddressShouldReturnModelAndViewOfWantedEstate()
         throws EstateNotFoundException, CouldNotAccessDataException, InvalidAccessException, IOException {
      // Given
      String expectedViewName = "estate";

      // When
      ModelAndView returnedModelAndView = estateController.getEstateByAddress(ADDRESS, request);

      // Then
      assertEquals(expectedViewName, returnedModelAndView.getViewName());
      Map<String, Object> returnedModel = returnedModelAndView.getModel();
      assertTrue(returnedModel.get("estate") instanceof EstateDto);
   }

}
