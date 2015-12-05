package ca.ulaval.glo4003.b6.housematch.services.user;

import javax.mail.Message;

import ca.ulaval.glo4003.b6.housematch.domain.user.MessageBuilder;
import ca.ulaval.glo4003.b6.housematch.domain.user.User;
import ca.ulaval.glo4003.b6.housematch.messaging.MessageSender;
import ca.ulaval.glo4003.b6.housematch.messaging.exceptions.MessageBuilderException;
import ca.ulaval.glo4003.b6.housematch.messaging.exceptions.MessageCantBeSentException;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.UserNotifyingException;

public class MailSenderObserver implements UserObserver {

   private MessageBuilder messageBuilder;

   private MessageSender messageSender;

   public MailSenderObserver(MessageBuilder messageBuilder, MessageSender messageSender) {
      this.messageBuilder = messageBuilder;
      this.messageSender = messageSender;
   }

   public synchronized void notifyUserChanged(User user) throws UserNotifyingException {
      try {
         Message message = messageBuilder.withRecipient(user).withMessage().build();
         messageSender.sendMessage(message);
      } catch (MessageBuilderException e) {
         throw new UserNotifyingException(e.toString(), e);
      } catch (MessageCantBeSentException e) {
         throw new UserNotifyingException(e.toString(), e);
      }

   }

}
