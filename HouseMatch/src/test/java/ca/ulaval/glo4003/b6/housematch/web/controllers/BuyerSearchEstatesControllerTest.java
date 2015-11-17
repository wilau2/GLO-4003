package ca.ulaval.glo4003.b6.housematch.web.controllers;

import static org.junit.Assert.assertEquals;
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
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.EstateNotFoundException;
import ca.ulaval.glo4003.b6.housematch.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.estate.EstatePicturesService;
import ca.ulaval.glo4003.b6.housematch.services.estate.EstateRepositoryFactory;
import ca.ulaval.glo4003.b6.housematch.services.estate.EstatesFetcher;
import ca.ulaval.glo4003.b6.housematch.services.user.UserAuthorizationService;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.InvalidAccessException;

public class BuyerSearchEstatesControllerTest {

   private static final String ADDRESS = "ADDRESS";

   private static final String EXPECTED_ROLE = "buyer";

   private BuyerSearchEstatesController buyerSearchEstatesController;

   private List<EstateDto> expectedEstates;

   @Mock
   private EstatesFetcher estatesFetcherService;

   @Mock
   private EstateRepositoryFactory estateRepositoryFactory;

   @Mock
   private EstateAssemblerFactory estateAssemblerFactory;

   @Mock
   private EstatePicturesService estatePicturesService;

   @Mock
   private EstateDto expectedReturnedEstate;

   @Mock
   private HttpServletRequest request;

   @Mock
   private UserAuthorizationService userAuthorizationService;

   @Before
   public void setup() throws CouldNotAccessDataException, EstateNotFoundException {
      MockitoAnnotations.initMocks(this);

      configureEstatesFetcher();

      buyerSearchEstatesController = new BuyerSearchEstatesController(estatesFetcherService, userAuthorizationService,
            estatePicturesService);
   }

   private void configureEstatesFetcher() throws CouldNotAccessDataException, EstateNotFoundException {

      expectedEstates = new ArrayList<>();
      when(estatesFetcherService.getAllEstates()).thenReturn(expectedEstates);

      when(estatesFetcherService.getEstateByAddress(ADDRESS)).thenReturn(expectedReturnedEstate);

   }

   @Test
   public void whenSearchingForAllEstatesForSaleShouldReturnAViewAndModel()
         throws CouldNotAccessDataException, InvalidAccessException {
      // Given no changes
      String expectedView = "buyer_search";

      // When
      ModelAndView returnedViewAndModel = buyerSearchEstatesController.searchAllEstates(request);

      // Then
      assertEquals(expectedView, returnedViewAndModel.getViewName());
   }

   @Test
   public void whenSearchingForAllEstatesForSaleShouldReturnListOfEstates()
         throws CouldNotAccessDataException, InvalidAccessException {
      // Given

      // When
      ModelAndView modelAndView = buyerSearchEstatesController.searchAllEstates(request);

      // Then
      Map<String, Object> model = modelAndView.getModel();
      assertEquals(expectedEstates, model.get("estates"));
   }

   @Test
   public void whenSearchingForAllEstatesShouldCallEstatesFetcherService()
         throws CouldNotAccessDataException, InvalidAccessException {
      // Given no changes

      // When
      buyerSearchEstatesController.searchAllEstates(request);

      // Then
      verify(estatesFetcherService, times(1)).getAllEstates();
   }

   @Test
   public void whenSelectingAnEstateShouldFetchEstateByAddress()
         throws EstateNotFoundException, CouldNotAccessDataException, InvalidAccessException {
      // Given no changes

      // When
      buyerSearchEstatesController.getEstateByAddress(ADDRESS, request);

      // Then
      verify(estatesFetcherService, times(1)).getEstateByAddress(ADDRESS);
   }

   @Test
   public void whenSelectingAnEstateByItsAddressShouldReturnAModelAndView()
         throws EstateNotFoundException, CouldNotAccessDataException, InvalidAccessException {
      // Given no changes
      String expectedViewName = "estate";

      // When
      ModelAndView returnedModelAndView = buyerSearchEstatesController.getEstateByAddress(ADDRESS, request);

      // Then
      assertEquals(expectedViewName, returnedModelAndView.getViewName());
      Map<String, Object> model = returnedModelAndView.getModel();
      assertEquals(expectedReturnedEstate, model.get("estate"));
   }

   @Test
   public void whenSelectingAnEstateShouldVerifyUserPermission()
         throws EstateNotFoundException, CouldNotAccessDataException, InvalidAccessException {
      // Given no changes

      // When
      buyerSearchEstatesController.getEstateByAddress(ADDRESS, request);

      // Then
      verify(userAuthorizationService, times(1)).verifySessionIsAllowed(request, EXPECTED_ROLE);
   }

   @Test(expected = InvalidAccessException.class)
   public void selectingAnEstateWhenUserIsNotAllowedShouldThrowAnException()
         throws EstateNotFoundException, CouldNotAccessDataException, InvalidAccessException {
      // Given
      doThrow(new InvalidAccessException("")).when(userAuthorizationService).verifySessionIsAllowed(request,
            EXPECTED_ROLE);

      // When
      buyerSearchEstatesController.getEstateByAddress(ADDRESS, request);

      // Then an InvalidAccessException is thrown
   }

   @Test
   public void whenSearchForAllEstateShouldVerifyUserPermission()
         throws InvalidAccessException, CouldNotAccessDataException {
      // Given no changes

      // When
      buyerSearchEstatesController.searchAllEstates(request);

      // Then
      verify(userAuthorizationService, times(1)).verifySessionIsAllowed(request, EXPECTED_ROLE);
   }

   @Test(expected = InvalidAccessException.class)
   public void searchingForAllEstatesWhenUserIsNotAllowedShouldThrowException()
         throws InvalidAccessException, CouldNotAccessDataException {
      // Given
      doThrow(new InvalidAccessException("")).when(userAuthorizationService).verifySessionIsAllowed(request,
            EXPECTED_ROLE);

      // When
      buyerSearchEstatesController.searchAllEstates(request);

      // Then
   }
}
