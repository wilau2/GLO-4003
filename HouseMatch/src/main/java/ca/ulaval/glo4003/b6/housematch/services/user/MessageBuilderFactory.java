package ca.ulaval.glo4003.b6.housematch.services.user;

import ca.ulaval.glo4003.b6.housematch.messaging.MailBuilder;

public interface MessageBuilderFactory {
   MailBuilder newInstance();
}
