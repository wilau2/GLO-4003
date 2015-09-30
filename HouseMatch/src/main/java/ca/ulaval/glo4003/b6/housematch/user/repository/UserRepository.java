package ca.ulaval.glo4003.b6.housematch.user.repository;

import ca.ulaval.glo4003.b6.housematch.user.domain.User;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.CouldNotAccessUserDataException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UsernameAlreadyExistsException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserNotFoundException;

public interface UserRepository {

   public User getUser(String username) throws UserNotFoundException, CouldNotAccessUserDataException;

   public void addUser(User user) throws UsernameAlreadyExistsException, CouldNotAccessUserDataException;
}
