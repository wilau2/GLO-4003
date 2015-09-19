package ca.ulaval.glo4003.b6.housematch.user.repository;

import ca.ulaval.glo4003.b6.housematch.user.domain.User;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserAlreadyExistsException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserNotFoundException;

public interface UserDao {

   public User findByEmail(String email) throws UserNotFoundException, CouldNotAccessDataException;

   public void add(User user) throws UserAlreadyExistsException, CouldNotAccessDataException;
}
