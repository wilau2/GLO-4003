package org.ulaval.teamb6.housematch2.service;

import javax.inject.Singleton;

import org.ulaval.teamb6.housematch2.domain.User;
import org.ulaval.teamb6.housematch2.repository.UserRepository;

@Singleton
public class InMemoryUserManager implements UserManager {

  private UserRepository userRepository;

  public User signInByEmail(User user) {
    return userRepository.getByEmail(user);
  }

  public void addNewUser(User user) {
    userRepository.add(user);
  }
}
