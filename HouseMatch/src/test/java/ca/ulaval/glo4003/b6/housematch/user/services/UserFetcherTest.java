package ca.ulaval.glo4003.b6.housematch.user.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.user.domain.ContactInformation;
import ca.ulaval.glo4003.b6.housematch.user.domain.User;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserDetailedDto;
import ca.ulaval.glo4003.b6.housematch.user.repository.UserRepository;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.CouldNotAccessUserDataException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserNotFoundException;

public class UserFetcherTest {

   @InjectMocks
   private UserFetcher userFetcher;

   @Mock
   private UserRepository userRepository;

   private static final String USERNAME = "username";

   @Mock
   private User user;

   @Mock
   private ContactInformation contactInformation;

   private static final String EMAIL = "email";

   private static final String PHONE_NUMBER = "1231231";

   private static final String FIRST_NAME = "firstname";

   private static final String LAST_NAME = "lastname";

   @Before
   public void setup() throws UserNotFoundException, CouldNotAccessUserDataException {
      MockitoAnnotations.initMocks(this);
      configureUserRepository();
      configureUser();
      configureContactInformation();

   }

   private void configureContactInformation() {
      given(contactInformation.getEmail()).willReturn(EMAIL);
      given(contactInformation.getPhoneNumber()).willReturn(PHONE_NUMBER);
      given(contactInformation.getFirstName()).willReturn(FIRST_NAME);
      given(contactInformation.getLastName()).willReturn(LAST_NAME);
   }

   private void configureUser() {
      given(user.getUsername()).willReturn(USERNAME);
      given(user.getContactInformation()).willReturn(contactInformation);
   }

   private void configureUserRepository() throws UserNotFoundException, CouldNotAccessUserDataException {
      given(userRepository.getUser(USERNAME)).willReturn(user);

   }

   @Test
   public void givenValidUsernameWhenGetUserByNameShouldDelegateGettingUser()
         throws UserNotFoundException, CouldNotAccessUserDataException {
      // Given

      // When
      userFetcher.getUserByUsername(USERNAME);

      // Then
      verify(userRepository, times(1)).getUser(USERNAME);
   }

   @Test
   public void givenValidUsernameWhenGetUserByNameShouldReturnDtoWithValidPhoneNumber()
         throws UserNotFoundException, CouldNotAccessUserDataException {
      // Given

      // When
      UserDetailedDto dto = userFetcher.getUserByUsername(USERNAME);

      // Then
      assertEquals(PHONE_NUMBER, dto.getPhoneNumber());
   }

   @Test
   public void givenValidUsernameWhenGetUserByNameShouldReturnDtoWithValidEmail()
         throws UserNotFoundException, CouldNotAccessUserDataException {
      // Given

      // When
      UserDetailedDto dto = userFetcher.getUserByUsername(USERNAME);

      // Then
      assertEquals(EMAIL, dto.getEmail());
   }

   @Test
   public void givenValidUsernameWhenGetUserByNameShouldReturnDtoWithValidFirstName()
         throws UserNotFoundException, CouldNotAccessUserDataException {
      // Given

      // When
      UserDetailedDto dto = userFetcher.getUserByUsername(USERNAME);

      // Then
      assertEquals(FIRST_NAME, dto.getFirstName());
   }

   @Test
   public void givenValidUsernameWhenGetUserByNameShouldReturnDtoWithValidLastName()
         throws UserNotFoundException, CouldNotAccessUserDataException {
      // Given

      // When
      UserDetailedDto dto = userFetcher.getUserByUsername(USERNAME);

      // Then
      assertEquals(LAST_NAME, dto.getLastName());
   }

   @Test(expected = UserNotFoundException.class)
   public void givenInvalidUsernameWhenGetUsetByNameShouldThrowException()
         throws UserNotFoundException, CouldNotAccessUserDataException {
      // Given
      doThrow(new UserNotFoundException("")).when(userRepository).getUser(USERNAME);

      // When
      UserDetailedDto dto = userFetcher.getUserByUsername(USERNAME);

      // Then
   }

   @Test(expected = CouldNotAccessUserDataException.class)
   public void givenInvalidDataBaseWhenGetUsetByNameShouldThrowException()
         throws UserNotFoundException, CouldNotAccessUserDataException {
      // Given
      doThrow(new CouldNotAccessUserDataException("")).when(userRepository).getUser(USERNAME);

      // When
      UserDetailedDto dto = userFetcher.getUserByUsername(USERNAME);

      // Then
   }

}
