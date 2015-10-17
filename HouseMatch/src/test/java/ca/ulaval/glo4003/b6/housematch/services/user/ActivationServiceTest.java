package ca.ulaval.glo4003.b6.housematch.services.user;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

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

   private static final String CORRECT_USERNAME = "USERNAME";

   @Mock
   private UserRepository userRepository;

   @InjectMocks
   private ActivationService activationService;

   @Mock
   private User user;

   @Before
   public void setup() throws UserNotFoundException, CouldNotAccessDataException {
      MockitoAnnotations.initMocks(this);
      configureUserRepository();

   }

   private void configureUserRepository() throws UserNotFoundException, CouldNotAccessDataException {

      given(userRepository.getUser(CORRECT_USERNAME)).willReturn(user);
   }

   @Test
   public void whenActivateUserIsActiveShouldBeTrue() throws UserNotFoundException, CouldNotAccessDataException {
      // Given
      given(user.isActive()).willReturn(true);
      // When
      activationService.activateAccount(CORRECT_USERNAME);

      // Then
      assertEquals(user.isActive(), true);
   }

   @Test
   public void whenActivateAccountShouldCallUpdateUser() throws UserNotFoundException, CouldNotAccessDataException {
      // Given
      given(user.isActive()).willReturn(true);
      // When
      activationService.activateAccount(CORRECT_USERNAME);

      // Then
      verify(userRepository).updateUser(user);
   }

}
