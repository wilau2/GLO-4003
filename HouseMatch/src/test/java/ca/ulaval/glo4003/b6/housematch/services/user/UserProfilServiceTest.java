package ca.ulaval.glo4003.b6.housematch.services.user;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.domain.user.ContactInformation;
import ca.ulaval.glo4003.b6.housematch.domain.user.User;
import ca.ulaval.glo4003.b6.housematch.domain.user.UserRepository;
import ca.ulaval.glo4003.b6.housematch.domain.user.exceptions.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.dto.ContactInformationDto;
import ca.ulaval.glo4003.b6.housematch.dto.UserDto;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.ContactInformationAssembler;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.UserNotifyingException;
import ca.ulaval.glo4003.b6.housematch.services.user.validator.UserValidator;

public class UserProfilServiceTest {

   private static final String USERNAME = "username";

   private static final String OLD_EMAIL = "old email";

   @Mock
   private UserRepository userRepository;

   @InjectMocks
   private UserProfilService userProfilService;

   @Mock
   private ContactInformationAssembler contactInformationAssembler;

   @Mock
   private UserValidator userValidator;

   @Mock
   private ContactInformationDto contactInformationDto;

   @Mock
   private User user;

   @Mock
   private ContactInformation contactInformation;

   @Mock
   private ContactInformation contactInformationNewEmail;

   @Mock
   private UserDto userDto;

   @Mock
   private UserObserver userObserver;

   private List<UserObserver> observers;

   @Before
   public void setup() throws UserNotFoundException, CouldNotAccessDataException {
      MockitoAnnotations.initMocks(this);
      configureUserAssembler();
      configureUserRepository();
      configureUserDetailedDto();
      configureUser();
      configureContactInformation();
      observers = new ArrayList<>();
      observers.add(userObserver);

      userProfilService = new UserProfilService(userRepository, contactInformationAssembler, observers);
   }

   private void configureContactInformation() {
      given(contactInformation.getEmail()).willReturn(OLD_EMAIL);
   }

   private void configureUser() {
      given(user.getContactInformation()).willReturn(contactInformation);
   }

   private void configureUserDetailedDto() {

      given(userDto.getContactInformationDto()).willReturn(contactInformationDto);
      given(userDto.getUsername()).willReturn(USERNAME);

   }

   private void configureUserRepository() throws UserNotFoundException, CouldNotAccessDataException {
      given(userRepository.getUser(USERNAME)).willReturn(user);
   }

   private void configureUserAssembler() {

      given(contactInformationAssembler.assembleContactInformation(contactInformationDto))
            .willReturn(contactInformation);
   }

   @Test
   public void givenValidUserDtoWhenUpdateThenShouldDelegateToUserRepository()
         throws CouldNotAccessDataException, UserNotFoundException, UserNotifyingException {
      // Given

      // When
      userProfilService.update(userDto);

      // Then
      verify(userRepository, times(1)).updateUser(user);
   }

   @Test
   public void givenValidUserDtoWhenUpdateThenShouldDelegateAssembling()
         throws CouldNotAccessDataException, UserNotFoundException, UserNotifyingException {
      // Given

      // When
      userProfilService.update(userDto);

      // Then
      verify(contactInformationAssembler).assembleContactInformation(contactInformationDto);
   }

   @Test
   public void givenValidUserDtoWithNewEmailShouldCallSetUserInactive()
         throws UserNotifyingException, CouldNotAccessDataException, UserNotFoundException {
      // Given
      configureUpdateWithNewEmail();

      // When
      userProfilService.update(userDto);

      // Then
      verify(user).setActive(false);
   }

   @Test
   public void givenValidUserDtoWithNewEmailShouldCallUpdateOnObserver()
         throws UserNotifyingException, CouldNotAccessDataException, UserNotFoundException {
      // Given
      configureUpdateWithNewEmail();

      // When
      userProfilService.update(userDto);

      // Then
      verify(userObserver).notifyUserChanged(user);
   }

   private void configureUpdateWithNewEmail() {
      given(contactInformationNewEmail.getEmail()).willReturn("new Email");
      given(contactInformationAssembler.assembleContactInformation(contactInformationDto))
            .willReturn(contactInformationNewEmail);
   }

   @Test(expected = CouldNotAccessDataException.class)
   public void givenInvalidDataAccessWhenGettingUserShouldReturnException()
         throws CouldNotAccessDataException, UserNotFoundException, UserNotifyingException {
      // Given
      doThrow(new CouldNotAccessDataException("", null)).when(userRepository).getUser(USERNAME);

      // When
      userProfilService.update(userDto);

      // Then Throws exception
   }

   @Test(expected = UserNotFoundException.class)
   public void givenUsernameWhenGettingUserShouldReturnException()
         throws CouldNotAccessDataException, UserNotFoundException, UserNotifyingException {
      // Given
      doThrow(new UserNotFoundException("")).when(userRepository).getUser(USERNAME);

      // When
      userProfilService.update(userDto);

      // Then Throws exception
   }

   @Test(expected = CouldNotAccessDataException.class)
   public void givenInvalidDataAccessWhenUpdatingUserShouldReturnException()
         throws CouldNotAccessDataException, UserNotFoundException, UserNotifyingException {
      // Given
      doThrow(new CouldNotAccessDataException("", null)).when(userRepository).updateUser(user);

      // When
      userProfilService.update(userDto);

      // Then Throws exception
   }

}
