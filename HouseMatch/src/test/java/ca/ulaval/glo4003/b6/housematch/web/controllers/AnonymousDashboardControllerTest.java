package ca.ulaval.glo4003.b6.housematch.web.controllers;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.DocumentException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.statistic.StatisticService;
import ca.ulaval.glo4003.b6.housematch.services.user.UserAuthorizationService;

public class AnonymousDashboardControllerTest {

   private AnonymousDashboardController anonymousController;

   @Mock
   private HttpServletRequest request;

   @Mock
   private StatisticService statisticService;

   @Mock
   private UserAuthorizationService userAuthorizationSerive;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);

      anonymousController = new AnonymousDashboardController(userAuthorizationSerive, statisticService);
   }

   @Test
   public void gettingTheHomePageWhenUserIsNotLoggedShouldCallStatisticService()
         throws CouldNotAccessDataException, DocumentException {
      // Given

      // When
      anonymousController.getHomePage(request);

      // Then
      verify(statisticService, times(1)).getNumberOfActiveBuyers();
   }

   @Test
   public void whenGettingTheHomePageShouldVerifyIfUserIsLogged()
         throws CouldNotAccessDataException, DocumentException {
      // Given

      // When
      anonymousController.getHomePage(request);

      // Then
      verify(userAuthorizationSerive, times(1)).isUserLogged(request);
   }

   @Test
   public void whenGettingTheHomePageShouldReturnViewModelWithViewName()
         throws CouldNotAccessDataException, DocumentException {
      // Given
      String expectedViewName = "home";

      // When
      ModelAndView returnedViewModel = anonymousController.getHomePage(request);

      // Then
      assertEquals(expectedViewName, returnedViewModel.getViewName());
   }

   @Test
   public void whenGettingTheHomePageShouldReturnTheNumberOfActiveBuyerInsideViewModel()
         throws CouldNotAccessDataException, DocumentException {
      // Given
      int expectedNumberOfActiveBuyer = 3;
      when(statisticService.getNumberOfActiveBuyers()).thenReturn(expectedNumberOfActiveBuyer);

      // When
      ModelAndView homePage = anonymousController.getHomePage(request);

      // Then
      Map<String, Object> model = homePage.getModel();
      assertEquals(expectedNumberOfActiveBuyer, model.get("numberOfActiveBuyers"));
   }

   @Test
   public void whenGettingTheAnonymeHomePageShouldCallNumberOfActiveSellersFromStatisticService()
         throws CouldNotAccessDataException, DocumentException {
      // Given no changes

      // When
      anonymousController.getHomePage(request);

      // Then
      verify(statisticService, times(1)).getNumberOfActiveSellers();
   }

   @Test
   public void whenGettingTheHomePageShouldReturnTheNumberOfActiveSellersInsideViewModel()
         throws CouldNotAccessDataException, DocumentException {
      // Given
      int expectedNumberOfActiveSellers = 3;
      when(statisticService.getNumberOfActiveSellers()).thenReturn(expectedNumberOfActiveSellers);

      // When
      ModelAndView homePageModel = anonymousController.getHomePage(request);

      // Then
      Map<String, Object> model = homePageModel.getModel();
      assertEquals(expectedNumberOfActiveSellers, model.get("numberOfActiveSellers"));
   }

   @Test
   public void whenGettingTheHomePageShouldReturnTheNumberOfEstatesSoldInTheLastYearInsideViewModel()
         throws CouldNotAccessDataException, DocumentException {
      // Given
      int expectedNumberOfEstatesSoldLastYear = 3;
      when(statisticService.getNumberOfEstatesSoldLastYear()).thenReturn(expectedNumberOfEstatesSoldLastYear);

      // When
      ModelAndView homePageModel = anonymousController.getHomePage(request);

      // Then
      Map<String, Object> model = homePageModel.getModel();
      assertEquals(expectedNumberOfEstatesSoldLastYear, model.get("numberOfEstatesSoldLastYear"));
   }

   @Test
   public void whenGettingTheHomePageShouldReturnGetTheNumberOfEstatesSoldLastYearByTheService()
         throws CouldNotAccessDataException, DocumentException {
      // Given no changes

      // When
      anonymousController.getHomePage(request);

      // Then
      verify(statisticService, times(1)).getNumberOfEstatesSoldLastYear();
   }
}
