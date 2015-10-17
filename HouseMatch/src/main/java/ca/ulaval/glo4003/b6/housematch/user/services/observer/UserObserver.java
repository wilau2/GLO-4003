package ca.ulaval.glo4003.b6.housematch.user.services.observer;

import ca.ulaval.glo4003.b6.housematch.user.domain.User;
import ca.ulaval.glo4003.b6.housematch.user.services.exceptions.UserNotifyingException;

public interface UserObserver {

   void notifyUserChanged(User user) throws UserNotifyingException;

}
