package ca.ulaval.glo4003.b6.housematch.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;

import ca.ulaval.glo4003.b6.housematch.user.repository.exception.CouldNotAccessUserDataException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.user.services.ActivationService;

public class ActivationControllerTest {
   
   private static final String CONFIRMATION = "confirmation";

   private static final String REDIRECT = "redirect:/";

   @InjectMocks
   private ActivationController activationController;

   @Mock
   private ActivationService activationService;
   
   @Mock
   private HttpServletRequest request;

   private String USERNAME = "username";
   
   @Before
   public void setup(){
      MockitoAnnotations.initMocks(this);
   }
   
   @Test
   public void confirmActivationShouldCallActivationService() throws UserNotFoundException, CouldNotAccessUserDataException {
      // Given

      // When
      activationController.confirmation(request, USERNAME);
      
      // Then
      verify(activationService, times(1)).activateAccount(USERNAME);
   }
   
   @Test
   public void confirmActivationShouldReturnRedirectIndex() throws UserNotFoundException, CouldNotAccessUserDataException {
      // Given

      // When
      String received = activationController.confirmation(request, USERNAME);
      
      // Then
      assertEquals(received, REDIRECT);
   }
   
   @Test(expected = UserNotFoundException.class)
   public void confirmActivationWithBadUserNAmeShouldThrowUserNotFoundException() throws UserNotFoundException, CouldNotAccessUserDataException {
      // Given
      doThrow(new UserNotFoundException("")).when(activationService).activateAccount(USERNAME);
      
      // When
      activationController.confirmation(request, USERNAME);
      
      // Then throw UserNotFoundException
   }
   
   @Test(expected = CouldNotAccessUserDataException.class)
   public void confirmActivationWithBadUserNAmeShouldThrowCouldNotAccessUserDataException() throws UserNotFoundException, CouldNotAccessUserDataException {
      // Given
      doThrow(new CouldNotAccessUserDataException("")).when(activationService).activateAccount(USERNAME);
      
      // When
      activationController.confirmation(request, USERNAME);
      
      // Then throw CouldNotAccessUserDataException
   }
   
   @Test
   public void confirmActivationWithPathVariableUsernameShouldReturnConfirmation() throws UserNotFoundException, CouldNotAccessUserDataException {
      // Given

      // When
      String received = activationController.confirmation(USERNAME);
      
      // Then
      assertEquals(received, CONFIRMATION);
   }
  
}
