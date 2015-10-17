package ca.ulaval.glo4003.b6.housematch.domain.estate;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.b6.housematch.domain.estate.Address;

public class AddressTest {

   private static final String UNION = "-";

   private static final String PROVINCE = "PROVINCE";

   private static final String COUNTRY = "COUNTRY";

   private static final String POSTAL_CODE = "POSTAL_CODE";

   private static final String STREET = "STREET";

   private static final Integer CIVIC_NUMBER = 1;

   private static final Integer APPARTMENT = 1;

   private Address address;

   @Before
   public void setup() {
      address = new Address(APPARTMENT, CIVIC_NUMBER, STREET, POSTAL_CODE, PROVINCE, COUNTRY);

   }

   @Test
   public void whenAskingForAddressToStringShouldReturnFormattedAddress() {
      // Given
      String expectedFormattedAddress = APPARTMENT.toString() + UNION + CIVIC_NUMBER.toString() + UNION + STREET + UNION
                                        + PROVINCE + UNION + COUNTRY + UNION + POSTAL_CODE;

      // When
      String formattedAddress = address.toString();

      // Then
      assertEquals(expectedFormattedAddress, formattedAddress);
   }
}
