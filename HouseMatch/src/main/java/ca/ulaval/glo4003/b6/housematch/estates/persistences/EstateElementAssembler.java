package ca.ulaval.glo4003.b6.housematch.estates.persistences;

import java.util.HashMap;

import org.dom4j.Element;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Address;
import ca.ulaval.glo4003.b6.housematch.estates.domain.Estate;
import ca.ulaval.glo4003.b6.housematch.estates.dto.AddressDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;

public class EstateElementAssembler {

   private static final String APPARTMENT = "appartment";

   private static final String STATE = "state";

   private static final String COUNTRY = "country";

   private static final String POSTAL_CODE = "postal_code";

   private static final String CIVIC_NUMBER = "civic_number";

   private static final String STREET = "street";

   private static final String SELLER = "seller";

   private static final String PRICE = "price";

   private static final String TYPE = "type";

   public EstateDto convertToDto(Element element) {

      EstateDto estateDto = new EstateDto();

      String type = element.attributeValue(TYPE);
      estateDto.setType(type);

      AddressDto addressDto = constructAddressDtoFromElement(element);
      estateDto.setAddress(addressDto);

      Integer price = Integer.parseInt(element.attributeValue(PRICE));
      estateDto.setPrice(price);

      String seller = element.attributeValue(SELLER);
      estateDto.setSellerId(seller);

      return estateDto;
   }

   private AddressDto constructAddressDtoFromElement(Element element) {
      AddressDto addressDto = new AddressDto();

      Integer civicNumber = Integer.parseInt(element.attributeValue(CIVIC_NUMBER));
      addressDto.setCivicNumber(civicNumber);

      String street = element.attributeValue(STREET);
      addressDto.setStreet(street);

      String postalCode = element.attributeValue(POSTAL_CODE);
      addressDto.setPostalCode(postalCode);

      String country = element.attributeValue(COUNTRY);
      addressDto.setCountry(country);

      String state = element.attributeValue(STATE);
      addressDto.setState(state);

      Integer appartment = Integer.parseInt(element.attributeValue(APPARTMENT));
      addressDto.setAppartment(appartment);

      return addressDto;
   }

   public HashMap<String, String> convertToAttributes(Estate estate) {
      HashMap<String, String> attributes = new HashMap<String, String>();

      attributes.put(SELLER, estate.getSeller());
      attributes.put(PRICE, estate.getPrice().toString());
      attributes.put(TYPE, estate.getType());

      Address address = estate.getAddress();
      addAddressAttributes(address, attributes);

      return attributes;
   }

   private void addAddressAttributes(Address address, HashMap<String, String> attributes) {
      attributes.put(COUNTRY, address.getCountry());
      attributes.put(STATE, address.getState());
      attributes.put(POSTAL_CODE, address.getPostalCode());
      attributes.put(STREET, address.getStreet());
      attributes.put(CIVIC_NUMBER, address.getCivicNumber().toString());
      attributes.put(APPARTMENT, address.getAppartment().toString());
   }

}
