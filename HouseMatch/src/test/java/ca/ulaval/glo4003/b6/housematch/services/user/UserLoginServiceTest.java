package ca.ulaval.glo4003.b6.housematch.services.user;

import static org.mockito.BDDMockito.given;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.domain.user.User;
import ca.ulaval.glo4003.b6.housematch.domain.user.UserRepository;
import ca.ulaval.glo4003.b6.housematch.domain.user.exceptions.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.dto.UserDto;
import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.InvalidPasswordException;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.UserActivationException;

public class UserLoginServiceTest {

   private static String USERNAME = "username";

   private static String PASSWORD = "password";

   private static String INVALID_PASSWORD = "not";

   @Mock
   private User user;

   @Mock
   private UserRepository userRepository;

   @Mock
   private UserAuthorizationService userAuthorizationService;

   @InjectMocks
   private UserLoginService userLoginService;

   @Mock
   private HttpServletRequest request;

   @Mock
   private UserDto userDto;

   @Before
   public void setup() throws UserNotFoundException, CouldNotAccessDataException {
      MockitoAnnotations.initMocks(this);
      configureUserRepository();
      configureUserAuthorisationService();
      configureValidUsername();
      configureValidPassword();
      configureUserIsValidate();

   }

   @Test
   public void givenValidUsernameWhenLoginShouldDelateGettingExistingUser()
         throws UserNotFoundException, CouldNotAccessDataException, InvalidPasswordException, UserActivationException {
      // Given

      // When
      userLoginService.login(request, userDto);

      // Then
      verify(userRepository).getUser(USERNAME);
   }

   @Test
   public void givenValidUsernameWhenLoginShouldDelateSessionAuthentification()
         throws UserNotFoundException, CouldNotAccessDataException, InvalidPasswordException, UserActivationException {
      // Given

      // When
      userLoginService.login(request, userDto);

      // Then
      verify(userAuthorizationService).setSessionUserAuthorisation(request, user);
   }

   @Test
   public void givenValidScenarioWhenLoginShouldNotThrowException()
         throws UserNotFoundException, CouldNotAccessDataException, InvalidPasswordException, UserActivationException {
      // Given

      // When
      userLoginService.login(request, userDto);

      // Then does not throw exception

   }

   @Test(expected = InvalidPasswordException.class)
   public void givenInvalidPasswordWhenLoginShouldThrowException()
         throws UserNotFoundException, CouldNotAccessDataException, InvalidPasswordException, UserActivationException {
      // Given
      configureInvalidPassword();

      // When
      userLoginService.login(request, userDto);

      // Then Throws InvalidPasswordException

   }

   @Test(expected = CouldNotAccessDataException.class)
   public void givenInvalidDataAccesWhenLoginShouldThrowException()
         throws UserNotFoundException, CouldNotAccessDataException, InvalidPasswordException, UserActivationException {
      // Given
      doThrow(new CouldNotAccessDataException(null, null)).when(userRepository).getUser(USERNAME);

      // When
      userLoginService.login(request, userDto);

      // Then throws CouldNotAccessUserDataException
   }

   @Test(expected = UserNotFoundException.class)
   public void givenNotExistingUserWhenLoginShouldThrowException()
         throws UserNotFoundException, CouldNotAccessDataException, InvalidPasswordException, UserActivationException {
      // Given

      // When
      doThrow(new UserNotFoundException(null)).when(userRepository).getUser(USERNAME);
      userLoginService.login(request, userDto);

      // Then throws UserNotFoundException
   }

   @Test(expected = UserActivationException.class)
   public void whenExistingUserIsNotActiveShouldThrowException()
         throws UserNotFoundException, CouldNotAccessDataException, InvalidPasswordException, UserActivationException {
      // Given
      configureUserIsNotValidate();

      // When
      userLoginService.login(request, userDto);

      // Then exception is thrown
   }

   @Test
   public void whenExistingUserIsActiveShouldUpdateTheUserDateOfLastActivity()
         throws UserNotFoundException, CouldNotAccessDataException, InvalidPasswordException, UserActivationException {
      // Given
      configureUserIsValidate();

      // When
      userLoginService.login(request, userDto);

      // Then
      verify(user, times(1)).updateLastActivity();
      verify(userRepository, times(1)).updateUser(user);
   }

   private void configureValidPassword() {
      given(user.getPassword()).willReturn(PASSWORD);
      given(userDto.getPassword()).willReturn(PASSWORD);
   }

   private void configureInvalidPassword() {
      given(user.getPassword()).willReturn(PASSWORD);
      given(userDto.getPassword()).willReturn(INVALID_PASSWORD);
   }

   private void configureValidUsername() {
      given(userDto.getUsername()).willReturn(USERNAME);

   }

   private void configureUserRepository() throws UserNotFoundException, CouldNotAccessDataException {
      given(userRepository.getUser(USERNAME)).willReturn(user);
   }

   private void configureUserAuthorisationService() {
      given(userAuthorizationService.setSessionUserAuthorisation(request, user)).willReturn(request);
   }

   private void configureUserIsValidate() {
      given(user.isActive()).willReturn(true);

   }

   private void configureUserIsNotValidate() {
      given(user.isActive()).willReturn(false);
   }

}
