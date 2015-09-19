package ca.ulaval.glo4003.b6.housematch.estates.anticorruption.exceptions;

public class InvalidEstateFieldException extends Exception {

   private static final long serialVersionUID = -7516805323659511606L;

   public InvalidEstateFieldException(String message, Exception exception) {
      super(message, exception);
   }

   public InvalidEstateFieldException() {
      // TODO Auto-generated constructor stub
   }

}
