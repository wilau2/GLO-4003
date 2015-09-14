package ca.ulaval.glo4003.b7.housematch.repository;

import ca.ulaval.glo4003.b7.housematch.domain.User;

public interface UserRepository {

  public User getByEmail(User user);

  public void add(User user);
}
