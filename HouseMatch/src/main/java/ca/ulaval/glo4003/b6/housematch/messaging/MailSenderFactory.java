package ca.ulaval.glo4003.b6.housematch.messaging;

import ca.ulaval.glo4003.b6.housematch.services.user.MessageSenderFactory;

public class MailSenderFactory implements MessageSenderFactory {

   public MailSender newInstance() {

      return new MailSender();
   }

}
