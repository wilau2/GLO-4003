package ca.ulaval.glo4003.b6.housematch.persistence.estate.converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.dom4j.Element;

import ca.ulaval.glo4003.b6.housematch.domain.estate.Description;
import ca.ulaval.glo4003.b6.housematch.domain.estate.Estate;
import ca.ulaval.glo4003.b6.housematch.dto.AddressDto;
import ca.ulaval.glo4003.b6.housematch.dto.DescriptionDto;
import ca.ulaval.glo4003.b6.housematch.dto.EstateDto;

public class EstateElementConverter {

   private static final String SELLER = "seller";

   private static final String PRICE = "price";

   private static final String TYPE = "type";

   private static final String ADDRESS = "address";
   
   private static final String PRICE_HISTORY = "price_history";

   private DescriptionElementConverter descriptionElementAssembler;

   public EstateElementConverter(DescriptionElementConverter descriptionElementAssembler) {
      this.descriptionElementAssembler = descriptionElementAssembler;
   }

   public EstateDto convertToDto(Element element) {
      EstateDto estateDto = new EstateDto();

      String type = element.elementText(TYPE);
      estateDto.setType(type);

      String addressFromElement = element.elementText(ADDRESS);
      AddressDto addressDto = constructAddressDtoFromString(addressFromElement);
      estateDto.setAddress(addressDto);

      Integer price = Integer.parseInt(element.elementText(PRICE));
      estateDto.setPrice(price);

      String seller = element.elementText(SELLER);
      estateDto.setSellerId(seller);
      
      String priceHistoryFromElement = element.elementText(PRICE_HISTORY);
      ArrayList<Integer> priceHistory = constructPriceHistoryFromString(priceHistoryFromElement);
      estateDto.setPriceHistory(priceHistory);

      return estateDto;
   }
   
   private ArrayList<Integer> constructPriceHistoryFromString(String priceHistoryFromElement) {
      ArrayList<Integer> priceHistory = new ArrayList<Integer>();
         
      String[] splittedPriceAttributes = priceHistoryFromElement.split("-");
      
      for(int priceIndex = 0; priceIndex < splittedPriceAttributes.length; priceIndex++)
         priceHistory.add(Integer.parseInt(splittedPriceAttributes[priceIndex]));

      return priceHistory;
   }
   
   private String constructStringFromPriceHistory(Estate estate) {
      String formattedPriceHistory = new String();
      ArrayList<Integer> priceHistory = estate.getPriceHistory();
      
      if (priceHistory != null){
         for(int i = 0; i < priceHistory.size(); i++){
            formattedPriceHistory = formattedPriceHistory + priceHistory.get(i).toString() + "-";
         }
      }
      else{
         formattedPriceHistory = "";
      }
      return formattedPriceHistory;
   }

   private AddressDto constructAddressDtoFromString(String addressFromElement) {
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
      attributes.put(PRICE_HISTORY, constructStringFromPriceHistory(estate)); 
      
      return attributes;
   }

   public EstateDto convertAttributesToDto(HashMap<String, String> attributes) {
      EstateDto estateDto = new EstateDto();

      estateDto.setSellerId(attributes.get(SELLER));
      estateDto.setType(attributes.get(TYPE));

      Integer price = Integer.parseInt(attributes.get(PRICE));
      estateDto.setPrice(price);

      AddressDto addressDto = constructAddressDtoFromString(attributes.get(ADDRESS));
      estateDto.setAddress(addressDto);
      
      ArrayList<Integer> priceHistory = constructPriceHistoryFromString(attributes.get(PRICE_HISTORY));
      estateDto.setPriceHistory(priceHistory);

      return estateDto;
   }

   public HashMap<String, String> convertDescriptionToAttributes(Description description) {
      return descriptionElementAssembler.convertToAttributes(description);
   }

   public DescriptionDto convertDescriptionAttributesToDto(HashMap<String, String> attributes) {
      return descriptionElementAssembler.convertAttributesToDto(attributes);
   }
   
}
