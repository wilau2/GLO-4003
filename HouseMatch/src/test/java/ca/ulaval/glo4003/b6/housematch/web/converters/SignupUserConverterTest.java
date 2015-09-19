package ca.ulaval.glo4003.b6.housematch.web.converters;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.b6.housematch.user.model.User;
import ca.ulaval.glo4003.b6.housematch.web.converters.SignupUserConverter;
import ca.ulaval.glo4003.b6.housematch.web.viewModel.SignupUserModel;

public class SignupUserConverterTest {

  private User user;

  private SignupUserModel viewUser;

  private SignupUserConverter converter;

  @Before
  public void setup() {
    user = new User();
    user.setEmail("an Email");
    user.setPassword("a Password");

    viewUser = new SignupUserModel();
    viewUser.setEmail("another Email");
    viewUser.setPassword("another Password");

    converter = new SignupUserConverter();
  }

  @Test
  public void whenConvertingAUserIntoAUserModelItShouldKeepTheSameEmail() {
    SignupUserModel returnedViewUser;
    returnedViewUser = converter.convertToViewModel(user);
    assertEquals("an Email", returnedViewUser.getEmail());
  }

  @Test
  public void whenConvertingAUserIntoAUserModelItShouldKeepTheSamePassword() {
    SignupUserModel returnedViewUser;
    returnedViewUser = converter.convertToViewModel(user);
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
