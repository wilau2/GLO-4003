package ca.ulaval.glo4003.b6.housematch.persistence.exceptions;

public class CouldNotAccessDataException extends Exception {

   private static final long serialVersionUID = 8071057726104826858L;

   public CouldNotAccessDataException(String errorMessage, Exception exception) {
      super(errorMessage, exception);
   }

   public CouldNotAccessDataException(String errorMessage) {
   }

}
