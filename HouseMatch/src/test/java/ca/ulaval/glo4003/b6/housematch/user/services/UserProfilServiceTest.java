package ca.ulaval.glo4003.b6.housematch.user.services;

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
import ca.ulaval.glo4003.b6.housematch.user.domain.assembler.ContactInformationAssembler;
import ca.ulaval.glo4003.b6.housematch.user.domain.assembler.factory.ContactInformationAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.user.dto.ContactInformationDto;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserDto;
import ca.ulaval.glo4003.b6.housematch.user.dto.validators.UserValidator;
import ca.ulaval.glo4003.b6.housematch.user.repository.UserRepository;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.CouldNotAccessUserDataException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserNotFoundException;

public class UserProfilServiceTest {

   private static final String USERNAME = "username";

   private static final String OLD_EMAIL = "old email";

   @Mock
   private UserRepository userRepository;

   @Mock
   private ContactInformationAssemblerFactory contactInformationAssemblerFactory;

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
   private UserDto userDto;

   @Before
   public void setup() throws UserNotFoundException, CouldNotAccessUserDataException {
      MockitoAnnotations.initMocks(this);
      configureUserAssembler();
      configureUserRepository();
      configureUserDetailedDto();
      configureUser();
      configureContactInformation();
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

   private void configureUserRepository() throws UserNotFoundException, CouldNotAccessUserDataException {
      given(userRepository.getUser(USERNAME)).willReturn(user);

   }

   private void configureUserAssembler() {
      given(contactInformationAssemblerFactory.createContactInformationAssembler())
            .willReturn(contactInformationAssembler);
      given(contactInformationAssembler.assembleContactInformation(contactInformationDto))
            .willReturn(contactInformation);
   }

   @Test
   public void givenValidUserDtoWhenUpdateThenShouldDelegateToUserRepository()
         throws CouldNotAccessUserDataException, UserNotFoundException {
      // Given

      // When
      userProfilService.update(userDto);

      // Then
      verify(userRepository, times(1)).updateUser(user);
   }

   @Test
   public void givenValidUserDtoWhenUpdateThenShouldDelegateAssembling()
         throws CouldNotAccessUserDataException, UserNotFoundException {
      // Given

      // When
      userProfilService.update(userDto);

      // Then
      verify(contactInformationAssembler).assembleContactInformation(contactInformationDto);
   }

   @Test(expected = CouldNotAccessUserDataException.class)
   public void givenInvalidDataAccessWhenGettingUserShouldReturnException()
         throws CouldNotAccessUserDataException, UserNotFoundException {
      // Given
      doThrow(new CouldNotAccessUserDataException("")).when(userRepository).getUser(USERNAME);

      // When
      userProfilService.update(userDto);

      // Then Throws exception
   }

   @Test(expected = UserNotFoundException.class)
   public void givenUsernameWhenGettingUserShouldReturnException()
         throws CouldNotAccessUserDataException, UserNotFoundException {
      // Given
      doThrow(new UserNotFoundException("")).when(userRepository).getUser(USERNAME);

      // When
      userProfilService.update(userDto);

      // Then Throws exception
   }

   @Test(expected = CouldNotAccessUserDataException.class)
   public void givenInvalidDataAccessWhenUpdatingUserShouldReturnException()
         throws CouldNotAccessUserDataException, UserNotFoundException {
      // Given
      doThrow(new CouldNotAccessUserDataException("")).when(userRepository).updateUser(user);

      // When
      userProfilService.update(userDto);

      // Then Throws exception
   }

}
