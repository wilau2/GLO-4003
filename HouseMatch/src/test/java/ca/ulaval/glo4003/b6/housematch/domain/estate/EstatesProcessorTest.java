package ca.ulaval.glo4003.b6.housematch.domain.estate;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.DocumentException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;

public class EstatesProcessorTest {

   private static final String SECOND_SELLER = "SECOND_SELLER";

   private static final String FIRST_SELLER = "FIRST_SELLER";

   private EstatesProcessor estatesProcessor;

   private List<Estate> estates;

   @Mock
   private Estate estate;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);

      estatesProcessor = new EstatesProcessor();

      estates = new ArrayList<Estate>();
   }

   @Test
   public void askingForTheNumberOfUniqueSellerWhenOnlyOneEstateShouldReturnNumberOfEstate()
         throws DocumentException, CouldNotAccessDataException {
      // Given
      int expectedNumberOfUniqueSeller = 1;
      configureEstateUniqueSeller(expectedNumberOfUniqueSeller);

      // When
      List<String> returnedValue = estatesProcessor.retrieveUniqueSellersName(estates);

      // Then
      assertEquals(expectedNumberOfUniqueSeller, returnedValue.size());
   }

   private void configureEstateUniqueSeller(int expectedNumberOfUniqueSeller) {
      for (int i = 0; i < expectedNumberOfUniqueSeller; i++) {
         estates.add(estate);
      }
   }

   @Test
   public void askingForTheNumberOfUniqueSellerWhenMoreThanOneSellerEstateWithAnEstateShouldReturnNumberOfEstate()
         throws DocumentException, CouldNotAccessDataException {
      // Given
      int expectedNumberOfUniqueSeller = 2;

      configureEstateUniqueSeller(expectedNumberOfUniqueSeller + 1);
      when(estate.getSeller()).thenReturn(FIRST_SELLER, SECOND_SELLER, FIRST_SELLER);

      // When
      List<String> returnedValue = estatesProcessor.retrieveUniqueSellersName(estates);

      // Then
      assertEquals(expectedNumberOfUniqueSeller, returnedValue.size());
   }

   @Test
   public void gettingTheNumberOfUniqueSellerWhenNoEstateInRepositoryShouldReturnZero()
         throws CouldNotAccessDataException {
      // Given
      int expectedNumberOfEstate = 0;

      // When
      List<String> returnedValue = estatesProcessor.retrieveUniqueSellersName(estates);

      // Then
      assertEquals(expectedNumberOfEstate, returnedValue.size());
   }

}
