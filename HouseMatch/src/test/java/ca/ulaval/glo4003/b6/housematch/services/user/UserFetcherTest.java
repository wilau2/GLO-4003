package ca.ulaval.glo4003.b6.housematch.services.user;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import ca.ulaval.glo4003.b6.housematch.dto.assembler.UserAssembler;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.factory.UserAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;

public class UserFetcherTest {

   private static final String USERNAME = "username";

   private static final String EMAIL = "email";

   private static final String PHONE_NUMBER = "1231231";

   private static final String FIRST_NAME = "firstname";

   private static final String LAST_NAME = "lastname";

   @InjectMocks
   private UserFetcher userFetcher;

   @Mock
   private UserRepository userRepository;

   @Mock
   private UserAssemblerFactory userAssemblerFactory;

   @Mock
   private UserAssembler userAssembler;

   @Mock
   private User user;

   @Mock
   private UserDto userDto;

   @Mock
   private ContactInformation contactInformation;

   @Mock
   private ContactInformationDto contactInformationDto;

   @Before
   public void setup() throws UserNotFoundException, CouldNotAccessDataException {
      MockitoAnnotations.initMocks(this);
      configureUserRepository();
      configureUser();
      configureContactInformation();
      configureUserAssembler();
      configureUserDto();

   }

   private void configureUserDto() {
      when(contactInformationDto.getEmail()).thenReturn(EMAIL);
      when(contactInformationDto.getFirstName()).thenReturn(FIRST_NAME);
      when(contactInformationDto.getLastName()).thenReturn(LAST_NAME);
      when(contactInformationDto.getPhoneNumber()).thenReturn(PHONE_NUMBER);
      when(userDto.getContactInformationDto()).thenReturn(contactInformationDto);

   }

   private void configureUserAssembler() {
      when(userAssemblerFactory.createUserAssembler()).thenReturn(userAssembler);
      when(userAssembler.assembleUserDto(user)).thenReturn(userDto);
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

   private void configureUserRepository() throws UserNotFoundException, CouldNotAccessDataException {
      given(userRepository.getUser(USERNAME)).willReturn(user);

   }

   @Test
   public void givenValidUsernameWhenGetUserByNameShouldDelegateGettingUser()
         throws UserNotFoundException, CouldNotAccessDataException {
      // Given

      // When
      userFetcher.getUserByUsername(USERNAME);

      // Then
      verify(userRepository, times(1)).getUser(USERNAME);
   }

   @Test
   public void givenValidUsernameWhenGetUserByNameShouldReturnDtoWithValidPhoneNumber()
         throws UserNotFoundException, CouldNotAccessDataException {
      // Given

      // When
      UserDto dto = userFetcher.getUserByUsername(USERNAME);

      // Then
      assertEquals(PHONE_NUMBER, dto.getContactInformationDto().getPhoneNumber());
   }

   @Test
   public void givenValidUsernameWhenGetUserByNameShouldReturnDtoWithValidEmail()
         throws UserNotFoundException, CouldNotAccessDataException {
      // Given

      // When
      UserDto dto = userFetcher.getUserByUsername(USERNAME);

      // Then
      assertEquals(EMAIL, dto.getContactInformationDto().getEmail());
   }

   @Test
   public void givenValidUsernameWhenGetUserByNameShouldReturnDtoWithValidFirstName()
         throws UserNotFoundException, CouldNotAccessDataException {
      // Given

      // When
      UserDto dto = userFetcher.getUserByUsername(USERNAME);

      // Then
      assertEquals(FIRST_NAME, dto.getContactInformationDto().getFirstName());
   }

   @Test
   public void givenValidUsernameWhenGetUserByNameShouldReturnDtoWithValidLastName()
         throws UserNotFoundException, CouldNotAccessDataException {
      // Given

      // When
      UserDto dto = userFetcher.getUserByUsername(USERNAME);

      // Then
      assertEquals(LAST_NAME, dto.getContactInformationDto().getLastName());
   }

   @Test(expected = UserNotFoundException.class)
   public void givenInvalidUsernameWhenGetUsetByNameShouldThrowException()
         throws UserNotFoundException, CouldNotAccessDataException {
      // Given
      doThrow(new UserNotFoundException("")).when(userRepository).getUser(USERNAME);

      // When
      userFetcher.getUserByUsername(USERNAME);

      // Then a UserNotFoundException is thrown
   }

   @Test(expected = CouldNotAccessDataException.class)
   public void givenInvalidDataBaseWhenGetUsetByNameShouldThrowException()
         throws UserNotFoundException, CouldNotAccessDataException {
      // Given
      doThrow(new CouldNotAccessDataException("", null)).when(userRepository).getUser(USERNAME);

      // When
      userFetcher.getUserByUsername(USERNAME);

      // Then a CouldNotAccessUserDataException is thrown
   }

}
