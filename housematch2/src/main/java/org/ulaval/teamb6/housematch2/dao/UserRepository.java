package org.ulaval.teamb6.housematch2.dao;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import org.springframework.stereotype.Repository;
import org.ulaval.teamb6.housematch2.model.User;

@Repository
@Singleton
public class UserRepository {

  private Map<String, User> entries = new HashMap<String, User>();

  public Map<String, User> getAll() {
    return entries;
  }

  public User getByEmail(User entry) {
    if (entries.containsKey(entry.email)) {
      return entries.get(entry.email);
    }
    throw new UserNotFoundException();
  }

  public void add(User entry) {
    entries.put(entry.email, entry);
  }

  public void update(User entry) {
    entries.put(entry.email, entry);
  }

  public void delete(int id) {
    entries.remove(id);
  }
}
