package ca.ulaval.glo4003.b6.housematch.web.controllers;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.domain.user.exceptions.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.user.ActivationService;

public class ActivationControllerTest {

   private static final String CONFIRMATION = "confirmation";

   private static final String REDIRECT = "redirect:/";

   private static final String USERNAME = "username";

   @InjectMocks
   private ActivationController activationController;

   @Mock
   private ActivationService activationService;

   @Mock
   private HttpServletRequest request;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);
   }

   @Test
   public void confirmActivationShouldCallActivationService()
         throws UserNotFoundException, CouldNotAccessDataException {
      // Given

      // When
      activationController.confirmation(request, USERNAME);

      // Then
      verify(activationService, times(1)).activateAccount(USERNAME);
   }

   @Test
   public void confirmActivationShouldReturnRedirectIndex() throws UserNotFoundException, CouldNotAccessDataException {
      // Given

      // When
      String received = activationController.confirmation(request, USERNAME);

      // Then
      assertEquals(received, REDIRECT);
   }

   @Test(expected = UserNotFoundException.class)
   public void confirmActivationWithBadUserNAmeShouldThrowUserNotFoundException()
         throws UserNotFoundException, CouldNotAccessDataException {
      // Given
      doThrow(new UserNotFoundException("")).when(activationService).activateAccount(USERNAME);

      // When
      activationController.confirmation(request, USERNAME);

      // Then throw UserNotFoundException
   }

   @Test(expected = CouldNotAccessDataException.class)
   public void confirmActivationWithBadUserNAmeShouldThrowCouldNotAccessUserDataException()
         throws UserNotFoundException, CouldNotAccessDataException {
      // Given
      doThrow(new CouldNotAccessDataException("", null)).when(activationService).activateAccount(USERNAME);

      // When
      activationController.confirmation(request, USERNAME);

      // Then throw CouldNotAccessUserDataException
   }

   @Test
   public void confirmActivationWithPathVariableUsernameShouldReturnConfirmation()
         throws UserNotFoundException, CouldNotAccessDataException {
      // Given

      // When
      String received = activationController.confirmation(USERNAME);

      // Then
      assertEquals(received, CONFIRMATION);
   }

}
