package ca.ulaval.glo4003.b6.housematch.services.user.exceptions;

public class UserNotifyingException extends Exception {
   
   private static final long serialVersionUID = 1L;

   public UserNotifyingException(String message, Exception e) {
      super(message,e);
   }

}
