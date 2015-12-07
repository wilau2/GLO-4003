package ca.ulaval.glo4003.b6.housematch.domain.estate.filters;

public class InconsistentFilterParamaterException extends Exception {

   private static final long serialVersionUID = -6248397611825906626L;

   InconsistentFilterParamaterException(String message) {
      super(message);
   }
}
