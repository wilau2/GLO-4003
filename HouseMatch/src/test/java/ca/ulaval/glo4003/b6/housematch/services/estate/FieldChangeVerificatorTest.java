package ca.ulaval.glo4003.b6.housematch.services.estate;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.ChangeIsSignificantException;
import ca.ulaval.glo4003.b6.housematch.services.estate.FieldChangeVerificator;

public class FieldChangeVerificatorTest {

   private static final double ACCEPTABLE_PERCENTAGE = 25.00;

   private FieldChangeVerificator fieldChangeVerificator;

   private String oldValue = "oldValue";

   private int oldIntegerValue;

   @Before
   public void setup() {
      fieldChangeVerificator = new FieldChangeVerificator();

   }

   @Test
   public void verifyingIfTwoStringAreChangeAboveAcceptablePercentageWhenChangePercentageIsBelowShouldNotThrowException()
         throws ChangeIsSignificantException {
      // Given
      String newValue = oldValue + "a";

      // When
      fieldChangeVerificator.verifyingChangeLevel(ACCEPTABLE_PERCENTAGE, oldValue, newValue);

      // Then no exception is thrown
   }

   @Test(expected = ChangeIsSignificantException.class)
   public void verifyingIfTwoStringAreChangeAboveAcceptablePercentageWhenChangePercentageIsAboveShouldThrowException()
         throws ChangeIsSignificantException {
      // Given
      String newValue = oldValue + "aa";

      // When
      fieldChangeVerificator.verifyingChangeLevel(ACCEPTABLE_PERCENTAGE, oldValue, newValue);

      // Then a change is significant exception is thrown
   }

   @Test
   public void verifyingIfTwoIntegerAreChangeAboveAcceptablePercentageWhenChangePercentageIsBelowShouldNotThrowException()
         throws ChangeIsSignificantException {
      // Given
      oldIntegerValue = 10;
      int newIntegerValue = oldIntegerValue + oldIntegerValue / 4;

      // When
      fieldChangeVerificator.verifyingChangeLevel(ACCEPTABLE_PERCENTAGE, oldIntegerValue, newIntegerValue);

      // Then no exception is thrown
   }

   @Test(expected = ChangeIsSignificantException.class)
   public void verifyingIfTwoIntegerAreChangeAboveAcceptablePercentageWhenOldValueIs0ShouldThrowException()
         throws ChangeIsSignificantException {
      // Given
      oldIntegerValue = 0;
      int newIntegerValue = oldIntegerValue + oldIntegerValue / 4 + 1;

      // When
      fieldChangeVerificator.verifyingChangeLevel(ACCEPTABLE_PERCENTAGE, oldIntegerValue, newIntegerValue);

      // Then a change is significant exception is thrown
   }

   @Test
   public void verifyingIfTwoIntegerAreChangeAboveAcceptablePercentageWhenNewValueIsZeroShouldNotThrowException()
         throws ChangeIsSignificantException {
      // Given
      oldIntegerValue = 0;
      int newIntegerValue = 0;

      // When
      fieldChangeVerificator.verifyingChangeLevel(ACCEPTABLE_PERCENTAGE, oldIntegerValue, newIntegerValue);

      // Then a change is significant exception is thrown
   }

   @Test(expected = ChangeIsSignificantException.class)
   public void verifyingIfTwoIntegerAreChangeAboveAcceptablePercentageWhenChangePercentageIsAboveShouldThrowException()
         throws ChangeIsSignificantException {
      // Given
      oldIntegerValue = 4;
      int newIntegerValue = oldIntegerValue + oldIntegerValue / 4 + 1;

      // When
      fieldChangeVerificator.verifyingChangeLevel(ACCEPTABLE_PERCENTAGE, oldIntegerValue, newIntegerValue);

      // Then a change is significant exception is thrown
   }
}
