package ca.ulaval.glo4003.b6.housematch.user.services.observer;

import ca.ulaval.glo4003.b6.housematch.user.common.MailBuilder;

public class MailBuilderFactory implements MessageBuilderFactory{

   public MailBuilder newInstance() {
      
      return new MailBuilder();
   }

}
