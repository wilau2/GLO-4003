package ca.ulaval.glo4003.b7.housematch.user.repository;

import ca.ulaval.glo4003.b7.housematch.user.model.User;
import ca.ulaval.glo4003.b7.housematch.user.repository.exception.UserNotFoundException;

public interface UserRepository {

  public User findByEmail(String email) throws UserNotFoundException;

  public void add(User user);
}
