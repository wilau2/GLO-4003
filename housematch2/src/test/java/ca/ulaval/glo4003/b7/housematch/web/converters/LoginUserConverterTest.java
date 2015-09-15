package ca.ulaval.glo4003.b7.housematch.web.converters;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.b7.housematch.user.model.User;
import ca.ulaval.glo4003.b7.housematch.web.converters.LoginUserConverter;
import ca.ulaval.glo4003.b7.housematch.web.viewModel.LoginUserModel;

public class LoginUserConverterTest {

  private User user;

  private LoginUserModel viewUser;

  private LoginUserConverter converter;

  @Before
  public void setup() {
    user = new User();
    user.setEmail("an Email");
    user.setPassword("a Password");

    viewUser = new LoginUserModel();
    viewUser.setEmail("another Email");
    viewUser.setPassword("another Password");

    converter = new LoginUserConverter();
  }

  @Test
  public void whenConvertingAUserIntoAUserModelItShouldKeepTheSameEmail() {
    LoginUserModel returnedViewUser;
    returnedViewUser = converter.convert(user);
    assertEquals("an Email", returnedViewUser.getEmail());
  }

  @Test
  public void whenConvertingAUserIntoAUserModelItShouldKeepTheSamePassword() {
    LoginUserModel returnedViewUser;
    returnedViewUser = converter.convert(user);
    assertEquals("a Password", returnedViewUser.getPassword());
  }

  @Test
  public void whenConvertingAUserModelIntoAUserItShouldKeepTheSameEmail() {
    User returnedUser;
    returnedUser = converter.convert(viewUser);
    assertEquals("another Email", returnedUser.getEmail());
  }

  @Test
  public void whenConvertingAUserModelIntoAUserItShouldKeepTheSamePassword() {
    User returnedUser;
    returnedUser = converter.convert(viewUser);
    assertEquals("another Password", returnedUser.getPassword());
  }
}
