package ca.ulaval.glo4003.b6.housematch.persistance.exceptions;

public class CouldNotAccessDataException extends RuntimeException {

   private static final long serialVersionUID = 1L;

   public CouldNotAccessDataException(String errorMessage, Exception exception) {
      super(errorMessage, exception);
   }

}
