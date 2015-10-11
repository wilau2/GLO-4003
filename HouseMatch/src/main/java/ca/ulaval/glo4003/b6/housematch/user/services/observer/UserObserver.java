package ca.ulaval.glo4003.b6.housematch.user.services.observer;

import ca.ulaval.glo4003.b6.housematch.user.domain.User;
import ca.ulaval.glo4003.b6.housematch.user.services.UserSignupService;
import ca.ulaval.glo4003.b6.housematch.user.services.exceptions.UserNotifyingException;

public interface UserObserver {
   public void update(User user) throws UserNotifyingException;

}
