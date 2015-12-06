package ca.ulaval.glo4003.b6.housematch.domain.estate;

import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.ChangeIsSignificantException;

public interface ChangeVerificator {

   void verifyingChangeLevel(double percentage, String oldValue, String newValue) throws ChangeIsSignificantException;

   void verifyingChangeLevel(double acceptablePercentage, int oldValue, int newValue)
         throws ChangeIsSignificantException;

}
