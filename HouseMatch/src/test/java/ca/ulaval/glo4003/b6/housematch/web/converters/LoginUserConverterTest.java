package ca.ulaval.glo4003.b6.housematch.web.converters;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.b6.housematch.user.dto.UserDto;
import ca.ulaval.glo4003.b6.housematch.web.viewModel.LoginUserViewModel;

public class LoginUserConverterTest {

   private UserDto userDto;

   private LoginUserViewModel viewModelUser;

   private LoginUserConverter converter;

   private String USERNAME = "username";

   private String PASSWORD = "password";

   @Before
   public void setup() {
      userDto = new UserDto();
      userDto.setUsername(USERNAME);
      userDto.setPassword(PASSWORD);

      viewModelUser = new LoginUserViewModel();
      viewModelUser.setUsername("another Email");
      viewModelUser.setPassword("another Password");

      converter = new LoginUserConverter();
   }

   @Test
   public void whenConvertingAUserIntoAUserModelItShouldKeepTheSameUsername() {
      LoginUserViewModel returnedViewUser;
      returnedViewUser = converter.convertToViewModel(userDto);
      assertEquals("an Email", returnedViewUser.getUsername());
   }

   @Test
   public void whenConvertingAUserIntoAUserModelItShouldKeepTheSamePassword() {
      LoginUserViewModel returnedViewUser;
      returnedViewUser = converter.convertToViewModel(userDto);
      assertEquals("a Password", returnedViewUser.getPassword());
   }

   @Test
   public void whenConvertingAUserModelIntoAUserItShouldKeepTheSameEmail() {
      UserDto returnedUser;
      returnedUser = converter.convertToDto(viewModelUser);
      assertEquals("another Email", returnedUser.getEmail());
   }

   @Test
   public void whenConvertingAUserModelIntoAUserItShouldKeepTheSamePassword() {
      UserDto returnedUser;
      returnedUser = converter.convertToDto(viewModelUser);
      assertEquals("another Password", returnedUser.getPassword());
   }
}
