package org.ulaval.teamb6.housematch2.service;

import org.ulaval.teamb6.housematch2.domain.User;

public interface UserManager {

  public User signInByEmail(User user);

  public void addNewUser(User user);
}
