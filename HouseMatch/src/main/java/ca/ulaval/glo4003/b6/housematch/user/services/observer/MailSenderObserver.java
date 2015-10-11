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
   
   private MessageBuilder messageBuilder;
   private Message message;
   private MessageSender messageSender;
   
   public MailSenderObserver() {
      this.messageBuilder = new MailBuilder();
      this.messageSender = new MailSender();
   }

   public void update(User user) throws UserNotifyingException{
         
      try {
        message = messageBuilder.withRecipient(user).withMessage().build();
        messageSender.sendMessage(message);
      } catch (MessageBuilderException e) {
         throw new UserNotifyingException(e.toString(), e);
      } catch (MessageCantBeSentException e) {
         throw new UserNotifyingException(e.toString(), e);
      }
       
   }

}
