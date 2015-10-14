package ca.ulaval.glo4003.b6.housematch.web.converters;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.user.dto.ContactInformationDto;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserDto;
import ca.ulaval.glo4003.b6.housematch.web.viewModel.SignupUserModel;

public class SignupUserConverterTest {

   private static final String EMAIL = "email";

   private static final String USERNAME = "username";

   private static final String FIRST_NAME = null;

   private static final String LAST_NAME = null;

   private static final String PHONE_NUMBER = null;

   private static final String PASSWORD = "password";

   private static final String ROLE = "role";

   @Mock
   private UserDto userDto;

   @Mock
   private ContactInformationDto contactInformationDto;

   @Mock
   private SignupUserModel viewUser;

   @InjectMocks
   private SignupUserConverter converter;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);
      configureUserDto();
      configureContactInformationDto();
      configureUserViewModelDto();
   }

   private void configureUserViewModelDto() {
      given(viewUser.getFirstName()).willReturn(FIRST_NAME);
      given(viewUser.getLastName()).willReturn(LAST_NAME);
      given(viewUser.getUsername()).willReturn(USERNAME);
      given(viewUser.getPassword()).willReturn(PASSWORD);
      given(viewUser.getRole()).willReturn(ROLE);
      given(viewUser.getPhoneNumber()).willReturn(PHONE_NUMBER);
      given(viewUser.getEmail()).willReturn(EMAIL);
   }

   private void configureContactInformationDto() {
      given(contactInformationDto.getFirstName()).willReturn(FIRST_NAME);
      given(contactInformationDto.getLastName()).willReturn(LAST_NAME);
      given(contactInformationDto.getPhoneNumber()).willReturn(PHONE_NUMBER);
      given(contactInformationDto.getEmail()).willReturn(EMAIL);

   }

   private void configureUserDto() {
      given(userDto.getUsername()).willReturn(USERNAME);
      given(userDto.getPassword()).willReturn(PASSWORD);
      given(userDto.getRole()).willReturn(ROLE);
      given(userDto.getContactInformationDto()).willReturn(contactInformationDto);

   }

   @Test
   public void whenConvertingAUserIntoAUserModelItShouldKeepTheSameUsername() {
      // Given
      SignupUserModel returnedViewUser;

      // When
      returnedViewUser = converter.convertSignupDtoToViewModel(userDto);

      // Then
      assertEquals(USERNAME, returnedViewUser.getUsername());
   }

   @Test
   public void whenConvertingAUserIntoAUserModelItShouldKeepTheSameEmail() {
      // Given
      SignupUserModel returnedViewUser;

      // When
      returnedViewUser = converter.convertSignupDtoToViewModel(userDto);

      // Then
      assertEquals(EMAIL, returnedViewUser.getEmail());
   }

   @Test
   public void whenConvertingAUserIntoAUserModelItShouldKeepTheSamePassword() {
      // Given
      SignupUserModel returnedViewUser;

      // When
      returnedViewUser = converter.convertSignupDtoToViewModel(userDto);

      // Then
      assertEquals(PASSWORD, returnedViewUser.getPassword());
   }

   @Test
   public void whenConvertingAUserModelIntoAUserItShouldKeepTheSameEmail() {
      // Given
      UserDto returnedUser;

      // When
      returnedUser = converter.convertViewModelToSignupDto(viewUser);

      // Then
      assertEquals(EMAIL, returnedUser.getContactInformationDto().getEmail());
   }

   @Test
   public void whenConvertingAUserModelIntoAUserItShouldKeepTheSamePassword() {
      // Given
      UserDto returnedUser;

      // When
      returnedUser = converter.convertViewModelToSignupDto(viewUser);

      // Then
      assertEquals(PASSWORD, returnedUser.getPassword());
   }

}
