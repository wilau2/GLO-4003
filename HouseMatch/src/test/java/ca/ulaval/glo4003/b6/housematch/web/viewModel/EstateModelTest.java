package ca.ulaval.glo4003.b6.housematch.web.viewModel;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Address;

public class EstateModelTest {

   private static final Integer APPARTMENT = 1;

   private static final Integer CIVI_NUMBER = 1;

   private static final String STREET = "STREET";

   private static final String POSTAL_CODE = "POSTAL_CODE";

   private static final String PROVINCE = "PROVINCE";

   private static final String COUNTRY = "COUNTRY";

   private static final Address ADDRESS = new Address(APPARTMENT, CIVI_NUMBER, STREET, POSTAL_CODE, PROVINCE, COUNTRY);

   private EstateModel estateModel;

   @Before
   public void setup() {
      estateModel = new EstateModel();

      setAddressToEstateModel();
   }

   private void setAddressToEstateModel() {
      estateModel.setAppartment(APPARTMENT);
      estateModel.setCivicNumber(CIVI_NUMBER);
      estateModel.setCountry(COUNTRY);
      estateModel.setPostalCode(POSTAL_CODE);
      estateModel.setStreet(STREET);
      estateModel.setState(PROVINCE);
   }

   @Test
   public void whenRequestingAddressFormattingShouldReturnAddressFormattedString() {
      // Given

      // When
      String addressToString = estateModel.addressToString();

      // Then
      String addressObjectAsString = ADDRESS.toString();
      String expectedAddressForModel = addressObjectAsString.replace("-", ", ");
      assertEquals(expectedAddressForModel, addressToString);
   }

   @Test
   public void whenRequestingAddressUrlShouldReturnAddressForUrlString() {
      // Given no changes

      // When
      String addressToUrl = estateModel.addressToUrl();

      // Then
      assertEquals(ADDRESS.toString(), addressToUrl);
   }
}
