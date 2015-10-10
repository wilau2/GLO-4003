package ca.ulaval.glo4003.b6.housematch.user.common;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import ca.ulaval.glo4003.b6.housematch.user.domain.User;

public interface MessageBuilder {

   public Message build();
   public MessageBuilder withRecipient(User user);
   public MessageBuilder withMessage() throws  MessageBuilderException;

}
