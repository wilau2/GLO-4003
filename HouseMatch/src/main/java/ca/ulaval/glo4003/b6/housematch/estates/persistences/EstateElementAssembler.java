package ca.ulaval.glo4003.b6.housematch.estates.persistences;

import java.util.HashMap;

import org.dom4j.Element;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Estate;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;

public class EstateElementAssembler {

   private static final String SELLER = "seller";

   private static final String PRICE = "price";

   private static final String ADDRESS = "address";

   private static final String TYPE = "type";

   public EstateDto convertToDto(Element element) {

      EstateDto estateDto = new EstateDto();

      String type = element.attributeValue(TYPE);
      estateDto.setType(type);

      String address = element.attributeValue(ADDRESS);
      estateDto.setAddress(address);

      Integer price = Integer.parseInt(element.attributeValue(PRICE));
      estateDto.setPrice(price);

      String seller = element.attributeValue(SELLER);
      estateDto.setSellerId(seller);

      return estateDto;
   }

   public HashMap<String, String> convertToAttributes(Estate estate) {
      HashMap<String, String> attributes = new HashMap<String, String>();

      attributes.put(SELLER, estate.getSeller());
      attributes.put(PRICE, estate.getPrice().toString());
      attributes.put(ADDRESS, estate.getAddress());
      attributes.put(TYPE, estate.getType());

      return attributes;
   }

}
