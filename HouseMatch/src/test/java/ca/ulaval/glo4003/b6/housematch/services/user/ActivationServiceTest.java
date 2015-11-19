package ca.ulaval.glo4003.b6.housematch.services.user;

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
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;

public class ActivationServiceTest {

   private static final String CORRECT_USERNAME = "USERNAME";

   @Mock
   private User user;

   @Mock
   private UserRepository userRepository;

   @InjectMocks
   private ActivationService activationService;

   @Before
   public void setup() throws UserNotFoundException, CouldNotAccessDataException {
      MockitoAnnotations.initMocks(this);
      given(userRepository.getUser(CORRECT_USERNAME)).willReturn(user);
   }

   @Test
   public void givenAValidUsernameWhenActivateAccountShouldDelegateUpdatingUser()
         throws UserNotFoundException, CouldNotAccessDataException {

      // Given

      // When
      activationService.activateAccount(CORRECT_USERNAME);

      // Then
      verify(userRepository).update(user);

   }

   @Test
   public void givenAValidUsernameWhenActivateAccountShouldDelegateUserGetting()
         throws UserNotFoundException, CouldNotAccessDataException {

      // Given

      // When
      activationService.activateAccount(CORRECT_USERNAME);

      // Then
      verify(userRepository).getUser(CORRECT_USERNAME);

   }

   @Test
   public void whenActivateAccountShouldActivateUser() throws UserNotFoundException, CouldNotAccessDataException {
      // Given

      // When
      activationService.activateAccount(CORRECT_USERNAME);

      // Then
      verify(user).setActive(true);
   }

}
