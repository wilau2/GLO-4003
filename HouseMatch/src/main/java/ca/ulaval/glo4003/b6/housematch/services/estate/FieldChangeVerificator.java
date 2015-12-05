package ca.ulaval.glo4003.b6.housematch.services.estate;

import org.apache.commons.lang.StringUtils;

import ca.ulaval.glo4003.b6.housematch.domain.estate.ChangeVerificator;
import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.ChangeIsSignificantException;

public class FieldChangeVerificator implements ChangeVerificator {

   public void verifyingChangeLevel(double percentage, String oldValue, String newValue)
         throws ChangeIsSignificantException {
      double levenshteinDistance = StringUtils.getLevenshteinDistance(oldValue, newValue);

      double differencePercentage = levenshteinDistance / oldValue.length() * 100;

      if (differencePercentage >= percentage) {
         throw new ChangeIsSignificantException();
      }
   }

   public void verifyingChangeLevel(double acceptablePercentage, int oldValue, int newValue)
         throws ChangeIsSignificantException {
      validatingValues(oldValue, newValue);

      double differenceBetweenOldAndNewValue = oldValue - newValue;
      double changePercentage = Math.abs(differenceBetweenOldAndNewValue / oldValue * 100);

      if (changePercentage >= acceptablePercentage) {
         throw new ChangeIsSignificantException();
      }

   }

   private void validatingValues(int oldValue, int newValue) throws ChangeIsSignificantException {
      if (oldValue == 0) {
         if (newValue != 0) {
            throw new ChangeIsSignificantException();
         }
         return;
      }
   }

}
