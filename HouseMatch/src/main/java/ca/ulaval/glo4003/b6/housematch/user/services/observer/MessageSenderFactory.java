package ca.ulaval.glo4003.b6.housematch.user.services.observer;

import ca.ulaval.glo4003.b6.housematch.user.common.MailSender;

public interface MessageSenderFactory {
   MailSender newInstance();
}
