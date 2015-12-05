package ca.ulaval.glo4003.b6.housematch.services.estate;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.domain.estate.filters.EstateFilter;
import ca.ulaval.glo4003.b6.housematch.domain.estate.filters.EstatesPriceFilter;
import ca.ulaval.glo4003.b6.housematch.services.estate.EstateFilterFactory;
import ca.ulaval.glo4003.b6.housematch.services.estate.exceptions.WrongFilterTypeException;

public class EstateFilterFactoryTest {

   private static final String WRONG_FILTER = "WRONG_FILTER";

   private static final String PRICE = "PRICE";

   private EstateFilterFactory estateFilterFactory;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);

      estateFilterFactory = new EstateFilterFactory();
   }

   @Test
   public void whenAskingForPriceFilterShouldReturnCorrectInstance() throws WrongFilterTypeException {
      // Given no changes

      // When
      EstateFilter filter = estateFilterFactory.getFilter(PRICE);

      // Then
      assertTrue(filter instanceof EstatesPriceFilter);
   }

   @Test(expected = WrongFilterTypeException.class)
   public void askingForFilterWhenFilterIsEmptyShouldThrowWrongFilterTypeException() throws WrongFilterTypeException {
      // Given no changes

      // When
      estateFilterFactory.getFilter(null);

      // Then a wrong filter type exception is thrown
   }

   @Test(expected = WrongFilterTypeException.class)
   public void askingForAnEstateFilterWhenFilterDoesNotExistsShouldThrowWrontFilterTypeException()
         throws WrongFilterTypeException {
      // Given no changes

      // When
      estateFilterFactory.getFilter(WRONG_FILTER);

      // Then a wrong filter type exception is thrown
   }
}
