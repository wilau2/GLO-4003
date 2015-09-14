package org.ulaval.teamb6.housematch2.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class UserTest {

  private User user;

  @Test
  public void newUserHasNoEmail() {
    user = new User();

    assertNull(user.getEmail());
  }

  @Test
  public void newUserHasNoPassword() {
    user = new User();

    assertNull(user.getPassword());
  }

  @Test
  public void emailSetterAndGetter() {
    user = new User();
    String testEmail = "anEmail";

    user.setEmail(testEmail);

    assertEquals(testEmail, user.getEmail());
  }

  @Test
  public void passwordSetterAndGetter() {
    user = new User();
    String testPassword = "a password";

    user.setPassword(testPassword);

    assertEquals(testPassword, user.getPassword());
  }

}
