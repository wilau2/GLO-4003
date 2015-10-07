package ca.ulaval.glo4003.b6.housematch.web.converters;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.b6.housematch.user.dto.ContactInformationDto;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserLoginDto;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserSignupDto;
import ca.ulaval.glo4003.b6.housematch.web.viewModel.SignupUserModel;

public class SignupUserConverterTest {

   private static final String EMAIL = "email";

   private static final String USERNAME = "username";

   private UserSignupDto userSignupDto;

   private ContactInformationDto contactInformationDto;

   private SignupUserModel viewUser;

   private SignupUserConverter converter;

   @Before
   public void setup() {
      userSignupDto = new UserSignupDto(USERNAME);
      contactInformationDto = new ContactInformationDto("firstName", "lastName", "phoneNumber", EMAIL);
      userSignupDto.setContactInformationDto(contactInformationDto);
      userSignupDto.setPassword("a Password");

      viewUser = new SignupUserModel();
      viewUser.setEmail("another Email");
      viewUser.setPassword("another Password");

      converter = new SignupUserConverter();
   }

   @Test
   public void whenConvertingAUserIntoAUserModelItShouldKeepTheSameUsername() {
      // Given
      SignupUserModel returnedViewUser;

      // When
      returnedViewUser = converter.convertSignupDtoToViewModel(userSignupDto);

      // Then
      assertEquals(USERNAME, returnedViewUser.getUsername());
   }

   @Test
   public void whenConvertingAUserIntoAUserModelItShouldKeepTheSameEmail() {
      // Given
      SignupUserModel returnedViewUser;

      // When
      returnedViewUser = converter.convertSignupDtoToViewModel(userSignupDto);

      // Then
      assertEquals(EMAIL, returnedViewUser.getEmail());
   }

   @Test
   public void whenConvertingAUserIntoAUserModelItShouldKeepTheSamePassword() {
      // Given
      SignupUserModel returnedViewUser;

      // When
      returnedViewUser = converter.convertSignupDtoToViewModel(userSignupDto);

      // Then
      assertEquals("a Password", returnedViewUser.getPassword());
   }

   @Test
   public void whenConvertingAUserModelIntoAUserItShouldKeepTheSameEmail() {
      // Given
      UserSignupDto returnedUser;

      // When
      returnedUser = converter.convertViewModelToSignupDto(viewUser);

      // Then
      assertEquals("another Email", returnedUser.getContactInformationDto().getEmail());
   }

   @Test
   public void whenConvertingAUserModelIntoAUserItShouldKeepTheSamePassword() {
      // Given
      UserSignupDto returnedUser;

      // When
      returnedUser = converter.convertViewModelToSignupDto(viewUser);

      // Then
      assertEquals("another Password", returnedUser.getPassword());
   }

   @Test
   public void shouldBetAbleToConvertSingupDtoToLoginDtoAndKeepTheUsername() {
      // Given
      UserLoginDto returnedUserLoginDto;

      // When
      returnedUserLoginDto = converter.convertSignupDtoToLoginDto(userSignupDto);

      // Then
      assertEquals(userSignupDto.getUsername(), returnedUserLoginDto.getUsername());
   }

   @Test
   public void shouldBetAbleToConvertSingupDtoToLoginDtoAndKeepThePassword() {
      // Given
      UserLoginDto returnedUserLoginDto;

      // When
      returnedUserLoginDto = converter.convertSignupDtoToLoginDto(userSignupDto);

      // Then
      assertEquals(userSignupDto.getPassword(), returnedUserLoginDto.getPassword());
   }
}
