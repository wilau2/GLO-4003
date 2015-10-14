package ca.ulaval.glo4003.b6.housematch.user.common;

import javax.mail.MessagingException;

public class MessageCantBeSentException extends Exception {
   private static final long serialVersionUID = -2118708352009688565L;

   public MessageCantBeSentException(String string, MessagingException e) {
     super(string,e);
   }


}
