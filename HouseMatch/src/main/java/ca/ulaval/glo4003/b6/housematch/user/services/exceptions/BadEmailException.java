package ca.ulaval.glo4003.b6.housematch.user.services.exceptions;


public class BadEmailException extends Exception {
   
   private static final long serialVersionUID = 1L;

   public BadEmailException(String message) {
      super(message);
   }

}
