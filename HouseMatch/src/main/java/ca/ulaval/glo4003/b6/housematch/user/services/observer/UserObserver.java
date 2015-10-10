package ca.ulaval.glo4003.b6.housematch.user.services.observer;

import ca.ulaval.glo4003.b6.housematch.user.domain.User;
import ca.ulaval.glo4003.b6.housematch.user.services.UserSignupService;
import ca.ulaval.glo4003.b6.housematch.user.services.exceptions.UserNotifyingException;

public abstract class UserObserver {
   protected UserSignupService userSignupService;
   public abstract void update(User user) throws UserNotifyingException;

}
