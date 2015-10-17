package ca.ulaval.glo4003.b6.housematch.services.user;

import ca.ulaval.glo4003.b6.housematch.domain.user.User;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.UserNotifyingException;

public interface UserObserver {

   void notifyUserChanged(User user) throws UserNotifyingException;

}
