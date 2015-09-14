package org.ulaval.teamb6.housematch2.repository;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import org.springframework.stereotype.Repository;
import org.ulaval.teamb6.housematch2.domain.User;

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
