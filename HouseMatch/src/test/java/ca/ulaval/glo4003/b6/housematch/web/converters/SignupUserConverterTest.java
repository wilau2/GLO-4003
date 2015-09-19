package ca.ulaval.glo4003.b6.housematch.web.converters;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.b6.housematch.user.dto.UserSignupDto;
import ca.ulaval.glo4003.b6.housematch.web.viewModel.SignupUserModel;

public class SignupUserConverterTest {

   private UserSignupDto userSignupDto;

   private SignupUserModel viewUser;

   private SignupUserConverter converter;

   @Before
   public void setup() {
      userSignupDto = new UserSignupDto();
      userSignupDto.setEmail("an Email");
      userSignupDto.setPassword("a Password");

      viewUser = new SignupUserModel();
      viewUser.setEmail("another Email");
      viewUser.setPassword("another Password");

      converter = new SignupUserConverter();
   }

   @Test
   public void whenConvertingAUserIntoAUserModelItShouldKeepTheSameEmail() {
      SignupUserModel returnedViewUser;
      returnedViewUser = converter.convertToViewModel(userSignupDto);
      assertEquals("an Email", returnedViewUser.getEmail());
   }

   @Test
   public void whenConvertingAUserIntoAUserModelItShouldKeepTheSamePassword() {
      SignupUserModel returnedViewUser;
      returnedViewUser = converter.convertToViewModel(userSignupDto);
      assertEquals("a Password", returnedViewUser.getPassword());
   }

   @Test
   public void whenConvertingAUserModelIntoAUserItShouldKeepTheSameEmail() {
      UserSignupDto returnedUser;
      returnedUser = converter.convertToDto(viewUser);
      assertEquals("another Email", returnedUser.getEmail());
   }

   @Test
   public void whenConvertingAUserModelIntoAUserItShouldKeepTheSamePassword() {
      UserSignupDto returnedUser;
      returnedUser = converter.convertToDto(viewUser);
      assertEquals("another Password", returnedUser.getPassword());
   }
}
