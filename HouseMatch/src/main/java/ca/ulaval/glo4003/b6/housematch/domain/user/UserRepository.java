package ca.ulaval.glo4003.b6.housematch.domain.user;

import ca.ulaval.glo4003.b6.housematch.domain.user.exceptions.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.domain.user.exceptions.UsernameAlreadyExistsException;
import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;

public interface UserRepository {

   User getUser(String username) throws UserNotFoundException, CouldNotAccessDataException;

   void addUser(User user) throws UsernameAlreadyExistsException, CouldNotAccessDataException;

   void updateUser(User user) throws CouldNotAccessDataException;

   void setUserActivity(String username, boolean value) throws CouldNotAccessDataException, UserNotFoundException;

   int getNumberOfActiveBuyer() throws CouldNotAccessDataException;
}
