package ca.ulaval.glo4003.b6.housematch.user.common;

import javax.mail.MessagingException;

public class MessageBuilderException extends Exception {
   
   private static final long serialVersionUID = -335898993029641406L;

   public MessageBuilderException(String string, MessagingException e) {
      super(string,e);
   }


}
