package ca.ulaval.glo4003.b6.housematch.web.controllers;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.doThrow;
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

import ca.ulaval.glo4003.b6.housematch.domain.user.Role;
import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.admin.AdminStatisticService;
import ca.ulaval.glo4003.b6.housematch.services.user.UserAuthorizationService;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.InvalidAccessException;

public class AdminStatisticControllerTest {

   @Mock
   private UserAuthorizationService userAuthorizationService;

   @Mock
   private HttpServletRequest request;

   @Mock
   private AdminStatisticService adminStatisticService;

   private AdminStatisticController adminStatisticController;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);

      adminStatisticController = new AdminStatisticController(userAuthorizationService, adminStatisticService);

   }

   @Test
   public void whenAskingHowManyActiveBuyersInHousematchShouldVerifyRole()
         throws InvalidAccessException, CouldNotAccessDataException, DocumentException {
      // Given no changes

      // When
      adminStatisticController.getNumberOfActiveBuyer(request);

      // Then
      verify(userAuthorizationService, times(1)).verifySessionIsAllowed(request, Role.ADMIN);
   }

   @Test(expected = InvalidAccessException.class)
   public void askingHowManyActiveBuyersInHousematchWhenUserNoAllowedShouldThrowInvalidAccessException()
         throws InvalidAccessException, CouldNotAccessDataException, DocumentException {
      // Given
      doThrow(new InvalidAccessException("")).when(userAuthorizationService).verifySessionIsAllowed(request,
            Role.ADMIN);

      // When
      adminStatisticController.getNumberOfActiveBuyer(request);

      // Then an Invalid access exception is thrown
   }

   @Test
   public void whenAskingForNumberOfActiveBuyerShouldCallAdminStatisticService()
         throws InvalidAccessException, CouldNotAccessDataException, DocumentException {
      // Given no changes

      // When
      adminStatisticController.getNumberOfActiveBuyer(request);

      // Then
      verify(adminStatisticService, times(1)).getNumberOfActiveBuyer();
   }

   @Test
   public void whenAskingForNumberOfActiveBuyerShouldReturnNumberOfActiveUserInsideVueModel()
         throws InvalidAccessException, CouldNotAccessDataException, DocumentException {
      // Given
      int expectedNumberOfActiveBuyer = 3;
      when(adminStatisticService.getNumberOfActiveBuyer()).thenReturn(expectedNumberOfActiveBuyer);

      // When
      ModelAndView returnedViewModel = adminStatisticController.getNumberOfActiveBuyer(request);

      // Then
      Map<String, Object> model = returnedViewModel.getModel();
      assertEquals(expectedNumberOfActiveBuyer, model.get("numberOfActiveBuyer"));
   }

   @Test
   public void whenAskingForAdminDashboardShouldReturnAdminDashboardView() throws InvalidAccessException {
      // Given

      // When
      String returnedViewName = adminStatisticController.getAdminStatisticDashboard(request);

      // Then
      assertEquals("admin_dashboard", returnedViewName);
   }

   @Test(expected = InvalidAccessException.class)
   public void askingForAdminDashboardWhenUserHasNotAdminRoleShouldThrowException() throws InvalidAccessException {
      // Given
      doThrow(new InvalidAccessException("")).when(userAuthorizationService).verifySessionIsAllowed(request,
            Role.ADMIN);

      // When
      adminStatisticController.getAdminStatisticDashboard(request);

      // Then an invalid access exception is thrown
   }
}
