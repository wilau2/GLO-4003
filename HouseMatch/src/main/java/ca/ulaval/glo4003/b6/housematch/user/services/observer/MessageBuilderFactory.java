package ca.ulaval.glo4003.b6.housematch.user.services.observer;

import ca.ulaval.glo4003.b6.housematch.user.common.MailBuilder;

public interface MessageBuilderFactory {
   MailBuilder newInstance();
}
