package ca.ulaval.glo4003.b6.housematch.user.common;

import javax.mail.Message;

import ca.ulaval.glo4003.b6.housematch.user.domain.User;

public interface MessageBuilder {

   Message build();

   MessageBuilder withRecipient(User user);

   MessageBuilder withMessage() throws MessageBuilderException;

}
