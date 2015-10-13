package ca.ulaval.glo4003.b6.housematch.user.services.observer;

import javax.mail.Message;

import ca.ulaval.glo4003.b6.housematch.user.common.MailBuilder;
import ca.ulaval.glo4003.b6.housematch.user.common.MailSender;
import ca.ulaval.glo4003.b6.housematch.user.common.MessageBuilder;
import ca.ulaval.glo4003.b6.housematch.user.common.MessageBuilderException;
import ca.ulaval.glo4003.b6.housematch.user.common.MessageCantBeSentException;
import ca.ulaval.glo4003.b6.housematch.user.common.MessageSender;
import ca.ulaval.glo4003.b6.housematch.user.domain.User;
import ca.ulaval.glo4003.b6.housematch.user.services.exceptions.UserNotifyingException;

public class MailSenderObserver implements UserObserver{
   

   private MessageBuilderFactory messageBuilderFactory;
   private MessageSenderFactory messageSenderFactory;
   
   public MailSenderObserver(MessageBuilderFactory messageBuilderFactory, MessageSenderFactory messageSenderFactory) {
      this.messageBuilderFactory = messageBuilderFactory;
      this.messageSenderFactory = messageSenderFactory;
   }

   public synchronized void notifyUserChanged(User user) throws UserNotifyingException{
        MessageBuilder messageBuilder = messageBuilderFactory.newInstance();
        MessageSender messageSender = messageSenderFactory.newInstance();
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
