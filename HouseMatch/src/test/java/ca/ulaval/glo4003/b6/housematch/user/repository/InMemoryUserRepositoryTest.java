package ca.ulaval.glo4003.b6.housematch.user.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.b6.housematch.user.model.User;
import ca.ulaval.glo4003.b6.housematch.user.repository.InMemoryUserRepository;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserNotFoundException;

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
    repository.findByEmail("Random Email");
  }

  @Test
  public void shouldBeAbleToAddANewUserAndGetByEmail() {
    repository.add(user);

    assertEquals(user, repository.findByEmail("An email"));
  }

}