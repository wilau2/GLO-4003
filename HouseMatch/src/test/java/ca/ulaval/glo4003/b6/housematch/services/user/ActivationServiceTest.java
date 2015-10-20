package ca.ulaval.glo4003.b6.housematch.services.user;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.domain.user.User;
import ca.ulaval.glo4003.b6.housematch.domain.user.UserRepository;
import ca.ulaval.glo4003.b6.housematch.domain.user.exceptions.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;

public class ActivationServiceTest {

   private String CORRECT_USERNAME;

   @Mock
   private UserRepository userRepository;

   @InjectMocks
   private ActivationService activationService;

   @Mock
   private User user;

   @Before
   public void setup() throws UserNotFoundException, CouldNotAccessDataException {
      MockitoAnnotations.initMocks(this);
   }

   @Test
   public void whenActivateAccountShouldCallUpdateUser() throws UserNotFoundException, CouldNotAccessDataException {
      // Given

      // When
      activationService.activateAccount(CORRECT_USERNAME);

      // Then
      verify(userRepository).setUserActivity(CORRECT_USERNAME, true);
   }

}
