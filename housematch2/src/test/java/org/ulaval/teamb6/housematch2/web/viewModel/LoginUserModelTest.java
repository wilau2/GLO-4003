package org.ulaval.teamb6.housematch2.web.viewModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class LoginUserModelTest {

  private LoginUserModel viewUser;

  @Before
  public void setup() {
    viewUser = new LoginUserModel();
  }

  @Test
  public void newUserHasNoEmail() {
    assertNull(viewUser.getEmail());
  }

  @Test
  public void newUserHasNoPassword() {
    assertNull(viewUser.getPassword());
  }

  @Test
  public void emailSetterAndGetter() {
    String testEmail = "anEmail";

    viewUser.setEmail(testEmail);

    assertEquals(testEmail, viewUser.getEmail());
  }

  @Test
  public void passwordSetterAndGetter() {
    String testPassword = "a password";

    viewUser.setPassword(testPassword);

    assertEquals(testPassword, viewUser.getPassword());
  }
}