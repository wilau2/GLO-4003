package ca.ulaval.glo4003.b7.housematch.user.repository;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import org.springframework.stereotype.Repository;

import ca.ulaval.glo4003.b7.housematch.user.model.User;
import ca.ulaval.glo4003.b7.housematch.user.repository.exception.UserNotFoundException;

@Repository
@Singleton
public class InMemoryUserRepository implements UserRepository {

  private Map<String, User> users = new HashMap<String, User>();

  public User getByEmail(User user) {
    if (users.containsKey(user.email)) {
      return users.get(user.email);
    }
    throw new UserNotFoundException();
  }

  public void add(User user) {
    users.put(user.email, user);
  }
}
