package ca.ulaval.glo4003.b6.housematch.web.controllers;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.estates.repository.factory.EstateRepositoryFactory;
import ca.ulaval.glo4003.b6.housematch.estates.services.EstatesFetcher;
import ca.ulaval.glo4003.b6.housematch.estates.services.EstatesFetcherFactory;

public class BuyerSearchEstatesControllerTest {

   private BuyerSearchEstatesController buyerSearchEstatesController;

   private List<EstateDto> expectedEstates;

   @Mock
   private EstatesFetcherFactory estatesFetcherFactory;

   @Mock
   private EstatesFetcher estatesFetcherService;

   @Mock
   private EstateRepositoryFactory estateRepositoryFactory;

   @Mock
   private EstateAssemblerFactory estateAssemblerFactory;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);

      configureEstatesFetcher();

      buyerSearchEstatesController = new BuyerSearchEstatesController(estatesFetcherFactory, estateAssemblerFactory,
            estateRepositoryFactory);
   }

   private void configureEstatesFetcher() {
      when(estatesFetcherFactory.newInstance(estateAssemblerFactory, estateRepositoryFactory))
            .thenReturn(estatesFetcherService);

      expectedEstates = new ArrayList<>();
      when(estatesFetcherService.getAllEstates()).thenReturn(expectedEstates);
   }

   @Test
   public void whenSearchingForAllEstatesForSaleShouldReturnAViewAndModel() {
      // Given no changes
      String expectedView = "buyer_search";

      // When
      ModelAndView returnedViewAndModel = buyerSearchEstatesController.searchAllEstates();

      // Then
      assertEquals(expectedView, returnedViewAndModel.getViewName());
   }

   @Test
   public void whenSearchingForAllEstatesForSaleShouldReturnListOfEstates() {
      // Given

      // When
      ModelAndView modelAndView = buyerSearchEstatesController.searchAllEstates();

      // Then
      Map<String, Object> model = modelAndView.getModel();
      assertEquals(expectedEstates, model.get("estates"));
   }

   @Test
   public void whenSearchingForAllEstatesShouldCallEstatesFetcherService() {
      // Given no changes

      // When
      buyerSearchEstatesController.searchAllEstates();

      // Then
      verify(estatesFetcherFactory, times(1)).newInstance(estateAssemblerFactory, estateRepositoryFactory);
      verify(estatesFetcherService, times(1)).getAllEstates();
   }
}
