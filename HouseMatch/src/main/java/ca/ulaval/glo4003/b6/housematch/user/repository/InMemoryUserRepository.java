package ca.ulaval.glo4003.b6.housematch.user.repository;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import org.springframework.stereotype.Repository;

import ca.ulaval.glo4003.b6.housematch.user.model.User;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserNotFoundException;

@Repository
@Singleton
public class InMemoryUserRepository implements UserRepository {

  private Map<String, User> users = new HashMap<String, User>();

  public User findByEmail(String email) {
    if (users.containsKey(email)) {
      return users.get(email);
    }
    throw new UserNotFoundException();
  }

  public void add(User user) {
    users.put(user.email, user);
  }
}
