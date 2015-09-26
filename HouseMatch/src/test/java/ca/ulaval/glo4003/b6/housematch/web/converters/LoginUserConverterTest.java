package ca.ulaval.glo4003.b6.housematch.web.converters;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.b6.housematch.user.dto.UserLoginDto;
import ca.ulaval.glo4003.b6.housematch.web.viewModel.LoginUserViewModel;

public class LoginUserConverterTest {

   private UserLoginDto userDto;

   private LoginUserViewModel viewModelUser;

   private LoginUserConverter converter;

   private String USERNAME = "username";

   private String PASSWORD = "password";

   @Before
   public void setup() {
      userDto = new UserLoginDto();
      userDto.setUsername(USERNAME);
      userDto.setPassword(PASSWORD);

      viewModelUser = new LoginUserViewModel();
      viewModelUser.setUsername(USERNAME);
      viewModelUser.setPassword(PASSWORD);

      converter = new LoginUserConverter();
   }

   @Test
   public void whenConvertingAUserIntoAUserModelItShouldKeepTheSameUsername() {
      LoginUserViewModel returnedViewUser;
      returnedViewUser = converter.convertToViewModel(userDto);
      assertEquals(USERNAME, returnedViewUser.getUsername());
   }

   @Test
   public void whenConvertingAUserIntoAUserModelItShouldKeepTheSamePassword() {
      LoginUserViewModel returnedViewUser;
      returnedViewUser = converter.convertToViewModel(userDto);
      assertEquals(PASSWORD, returnedViewUser.getPassword());
   }

   @Test
   public void whenConvertingAUserModelIntoAUserItShouldKeepTheSameUsername() {
      UserLoginDto returnedUser;
      returnedUser = converter.convertViewModelToDto(viewModelUser);
      assertEquals(USERNAME, returnedUser.getUsername());
   }

   @Test
   public void whenConvertingAUserModelIntoAUserItShouldKeepTheSamePassword() {
      UserLoginDto returnedUser;
      returnedUser = converter.convertViewModelToDto(viewModelUser);
      assertEquals(PASSWORD, returnedUser.getPassword());
   }
}
