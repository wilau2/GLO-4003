package ca.ulaval.glo4003.b6.housematch.user.services;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.user.domain.User;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserDto;
import ca.ulaval.glo4003.b6.housematch.user.repository.UserRepository;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.CouldNotAccessUserDataException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.user.services.exceptions.InvalidPasswordException;

public class UserLoginServiceTest {

   private static String USERNAME = "username";

   private static String PASSWORD = "password";

   private static String INVALID_PASSWORD = "not";

   @Mock
   User user;

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
   public void setup() throws UserNotFoundException, CouldNotAccessUserDataException {
      MockitoAnnotations.initMocks(this);
      configureUserRepository();
      configureUserAuthorisationService();

   }

   @Test
   public void givenValidUsernameWhenLoginShouldDelateGettingExistingUser()
         throws UserNotFoundException, CouldNotAccessUserDataException, InvalidPasswordException {
      // Given
      configureValidUsername();
      configureValidPassword();
      // When
      userLoginService.login(request, userDto);

      // Then
      verify(userRepository).getUser(USERNAME);
   }

   @Test
   public void givenValidUsernameWhenLoginShouldDelateSessionAuthentification()
         throws UserNotFoundException, CouldNotAccessUserDataException, InvalidPasswordException {
      // Given
      configureValidUsername();
      configureValidPassword();
      // When
      userLoginService.login(request, userDto);

      // Then
      verify(userAuthorizationService).setSessionUserAuthorisation(request, user);
   }

   @Test
   public void givenValidScenarioWhenLoginShouldNotThrowException()
         throws UserNotFoundException, CouldNotAccessUserDataException, InvalidPasswordException {
      // Given
      configureValidUsername();
      configureValidPassword();

      // When
      userLoginService.login(request, userDto);

      // Then does not throw exception

   }

   @Test(expected = InvalidPasswordException.class)
   public void givenInvalidPasswordWhenLoginShouldThrowException()
         throws UserNotFoundException, CouldNotAccessUserDataException, InvalidPasswordException {
      // Given
      configureValidUsername();
      configureInvalidPassword();

      // When
      userLoginService.login(request, userDto);

      // Then Throws InvalidPasswordException

   }

   @Test(expected = CouldNotAccessUserDataException.class)
   public void givenInvalidDataAccesWhenLoginShouldThrowException()
         throws UserNotFoundException, CouldNotAccessUserDataException, InvalidPasswordException {
      // Given
      configureValidUsername();

      // When
      doThrow(new CouldNotAccessUserDataException(null)).when(userRepository).getUser(USERNAME);
      userLoginService.login(request, userDto);

      // Then throws CouldNotAccessUserDataException
   }

   @Test(expected = UserNotFoundException.class)
   public void givenNotExistingUserWhenLoginShouldThrowException()
         throws UserNotFoundException, CouldNotAccessUserDataException, InvalidPasswordException {
      // Given
      configureValidUsername();

      // When
      doThrow(new UserNotFoundException(null)).when(userRepository).getUser(USERNAME);
      userLoginService.login(request, userDto);

      // Then throws UserNotFoundException
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

   private void configureUserRepository() throws UserNotFoundException, CouldNotAccessUserDataException {
      given(userRepository.getUser(USERNAME)).willReturn(user);
   }

   private void configureUserAuthorisationService() {
      given(userAuthorizationService.setSessionUserAuthorisation(request, user)).willReturn(request);
   }

}
