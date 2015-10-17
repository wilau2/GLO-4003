package ca.ulaval.glo4003.b6.housematch.user.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.user.domain.User;
import ca.ulaval.glo4003.b6.housematch.user.repository.UserRepository;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.CouldNotAccessUserDataException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserNotFoundException;

public class ActivationServiceTest {

   private static final String CORRECT_USERNAME = "USERNAME";

   @Mock
   private UserRepository userRepository;

   @InjectMocks
   private ActivationService activationService;

   @Mock
   private User user;

   @Before
   public void setup() throws UserNotFoundException, CouldNotAccessUserDataException {
      MockitoAnnotations.initMocks(this);
      configureUserRepository();

   }

   private void configureUserRepository() throws UserNotFoundException, CouldNotAccessUserDataException {

      given(userRepository.getUser(CORRECT_USERNAME)).willReturn(user);
   }

   @Test
   public void whenActivateUserIsActiveShouldBeTrue() throws UserNotFoundException, CouldNotAccessUserDataException {
      // Given
      given(user.isActive()).willReturn(true);
      // When
      activationService.activateAccount(CORRECT_USERNAME);

      // Then
      assertEquals(user.isActive(), true);
   }

   @Test
   public void whenActivateAccountShouldCallUpdateUser() throws UserNotFoundException, CouldNotAccessUserDataException {
      // Given
      given(user.isActive()).willReturn(true);
      // When
      activationService.activateAccount(CORRECT_USERNAME);

      // Then
      verify(userRepository).updateUser(user);
   }

}
