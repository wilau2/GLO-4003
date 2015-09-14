package org.ulaval.teamb6.housematch2.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.ulaval.teamb6.housematch2.domain.User;

public class InMemoryUserRepositoryTest {

  private User user;

  private InMemoryUserRepository repository;

  @Before
  public void setup() {
    repository = new InMemoryUserRepository();

    user = new User();
    user.setEmail("An email");
    user.setPassword("a password");
  }

  @Test(expected = UserNotFoundException.class)
  public void givenAUnknownUserShouldReturnException() {
    repository.getByEmail(user);
  }

  @Test
  public void shouldBeAbleToAddANewUserAndGetByEmail() {
    repository.add(user);

    assertEquals(user, repository.getByEmail(user));
  }

}
