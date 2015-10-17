package ca.ulaval.glo4003.b6.housematch.anticorruption.user.exceptions;

public class InvalidUserLoginFieldException extends Exception {

   private static final long serialVersionUID = 1L;

   public InvalidUserLoginFieldException(String message) {
      super(message);
   }

}
