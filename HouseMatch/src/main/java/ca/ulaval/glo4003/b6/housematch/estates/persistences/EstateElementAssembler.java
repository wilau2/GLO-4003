package ca.ulaval.glo4003.b6.housematch.estates.persistences;

import org.dom4j.Element;

import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;

public class EstateElementAssembler {

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

      return estateDto;
   }

}
