package ca.ulaval.glo4003.b6.housematch.estates.persistences.assemblers;

import java.util.HashMap;

import org.dom4j.Element;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Estate;
import ca.ulaval.glo4003.b6.housematch.estates.dto.AddressDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;

public class EstateElementAssembler {

   private static final String SELLER = "seller";

   private static final String PRICE = "price";

   private static final String TYPE = "type";

   private static final String ADDRESS = "address";

   public EstateDto convertToDto(Element element) {
      EstateDto estateDto = new EstateDto();

      String type = element.elementText(TYPE);
      estateDto.setType(type);

      String addressFromElement = element.elementText(ADDRESS);
      AddressDto addressDto = constructAddressDtoFromElement(addressFromElement);
      estateDto.setAddress(addressDto);

      Integer price = Integer.parseInt(element.elementText(PRICE));
      estateDto.setPrice(price);

      String seller = element.elementText(SELLER);
      estateDto.setSellerId(seller);

      return estateDto;
   }

   private AddressDto constructAddressDtoFromElement(String addressFromElement) {
      AddressDto addressDto = new AddressDto();

      String[] splittedAddressAttributes = addressFromElement.split("-");
      int addressIndex = 0;
      int appartmentNumber = Integer.parseInt(splittedAddressAttributes[addressIndex++]);
      addressDto.setAppartment(appartmentNumber);

      Integer civicNumber = Integer.parseInt(splittedAddressAttributes[addressIndex++]);
      addressDto.setCivicNumber(civicNumber);

      addressDto.setStreet(splittedAddressAttributes[addressIndex++]);
      addressDto.setState(splittedAddressAttributes[addressIndex++]);
      addressDto.setCountry(splittedAddressAttributes[addressIndex++]);
      addressDto.setPostalCode(splittedAddressAttributes[addressIndex++]);

      return addressDto;
   }

   public HashMap<String, String> convertToAttributes(Estate estate) {
      HashMap<String, String> attributes = new HashMap<String, String>();

      attributes.put(SELLER, estate.getSeller());
      attributes.put(PRICE, estate.getPrice().toString());
      attributes.put(TYPE, estate.getType());

      attributes.put(ADDRESS, estate.getAddress().toString());

      return attributes;
   }

}
