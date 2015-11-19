package ca.ulaval.glo4003.b6.housematch.domain.user;

import java.util.List;

import org.dom4j.DocumentException;

import ca.ulaval.glo4003.b6.housematch.domain.user.exceptions.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.domain.user.exceptions.UsernameAlreadyExistsException;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;

public interface UserRepository {

   User getUser(String username) throws UserNotFoundException, CouldNotAccessDataException;

   void add(User user) throws UsernameAlreadyExistsException, CouldNotAccessDataException;

   void update(User user) throws CouldNotAccessDataException;

   List<User> getAllUsers() throws DocumentException;

}
