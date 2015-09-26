package ca.ulaval.glo4003.b6.housematch.estates.anticorruption.exceptions;


public class InvalidRoomFieldException extends Exception {

   private static final long serialVersionUID = 1L;

   public InvalidRoomFieldException(String message, Exception exception) {
      super(message, exception);
   }

   public InvalidRoomFieldException(String message) {
      super(message);
   }
}
