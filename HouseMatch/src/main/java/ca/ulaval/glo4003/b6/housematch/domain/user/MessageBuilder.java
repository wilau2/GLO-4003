package ca.ulaval.glo4003.b6.housematch.domain.user;

import javax.mail.Message;

import ca.ulaval.glo4003.b6.housematch.messaging.exceptions.MessageBuilderException;

public interface MessageBuilder {

   Message build();

   MessageBuilder withRecipient(User user);

   MessageBuilder withMessage() throws MessageBuilderException;

}
