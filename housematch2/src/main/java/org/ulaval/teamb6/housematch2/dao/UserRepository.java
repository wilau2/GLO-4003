package org.ulaval.teamb6.housematch2.dao;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import org.springframework.stereotype.Repository;
import org.ulaval.teamb6.housematch2.model.User;

@Repository
@Singleton
public class UserRepository {

  private Map<String, User> users = new HashMap<String, User>();

  public Map<String, User> getAll() {
    return users;
  }

  public User getByEmail(User user) {
    if (users.containsKey(user.email)) {
      return users.get(user.email);
    }
    throw new UserNotFoundException();
  }

  public void add(User user) {
    users.put(user.email, user);
  }

  public void update(User user) {
    users.put(user.email, user);
  }

  public void delete(int id) {
    users.remove(id);
  }
}
