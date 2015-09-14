package ca.ulaval.glo4003.b7.housematch.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.b7.housematch.domain.User;
import ca.ulaval.glo4003.b7.housematch.repository.InMemoryUserRepository;
import ca.ulaval.glo4003.b7.housematch.repository.UserNotFoundException;

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
