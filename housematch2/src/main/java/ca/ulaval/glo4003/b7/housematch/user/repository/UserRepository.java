package ca.ulaval.glo4003.b7.housematch.user.repository;

import ca.ulaval.glo4003.b7.housematch.user.model.User;

public interface UserRepository {

  public User getByEmail(User user);

  public void add(User user);
}
