package ca.ulaval.glo4003.b6.housematch.messaging;

import ca.ulaval.glo4003.b6.housematch.services.user.MessageBuilderFactory;

public class MailBuilderFactory implements MessageBuilderFactory{

   public MailBuilder newInstance() {
      
      return new MailBuilder();
   }

}
