package org.ulaval.teamb6.housematch2.repository;

import org.ulaval.teamb6.housematch2.domain.User;

public interface UserRepository {

  public User getByEmail(User user);

  public void add(User user);
}
