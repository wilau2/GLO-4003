package ca.ulaval.glo4003.b6.housematch.user.repository.exception;

public class CouldNotAccessUserDataException extends Exception {

   private static final long serialVersionUID = 1L;

   public CouldNotAccessUserDataException(String message) {
      super(message);
   }

}
