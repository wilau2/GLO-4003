package ca.ulaval.glo4003.b6.housematch.persistence.estate.converter;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import org.dom4j.Element;

import ca.ulaval.glo4003.b6.housematch.domain.estate.Description;
import ca.ulaval.glo4003.b6.housematch.domain.estate.Estate;
import ca.ulaval.glo4003.b6.housematch.dto.AddressDto;
import ca.ulaval.glo4003.b6.housematch.dto.DescriptionDto;
import ca.ulaval.glo4003.b6.housematch.dto.EstateDto;

public class EstateElementConverter {

   private static final String PURCHASE_DATE = "purchase_date";

   private static final String BOUGHT = "bought";

   private static final String SELLER = "seller";

   private static final String PRICE = "price";

   private static final String TYPE = "type";

   private static final String ADDRESS = "address";

   private static final String PRICE_HISTORY = "price_history";

   private static final String DATE_REGISTERED = "date_registered";

   private DescriptionElementConverter descriptionElementAssembler;

   public EstateElementConverter(DescriptionElementConverter descriptionElementAssembler) {
      this.descriptionElementAssembler = descriptionElementAssembler;
   }

   public EstateDto convertToDto(Element element) throws ParseException {
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

      LocalDateTime dateRegistered = LocalDateTime.parse(element.elementText(DATE_REGISTERED));
      estateDto.setDateRegistered(dateRegistered);

      Boolean bought = Boolean.parseBoolean(element.elementText(BOUGHT));
      estateDto.setBought(bought);

      String purchaseDateFromElement = element.elementText(PURCHASE_DATE);
      setDateOfPurchaseToEstateDto(estateDto, purchaseDateFromElement);

      return estateDto;
   }

   private void setDateOfPurchaseToEstateDto(EstateDto estateDto, String purchaseDateFromElement) {
      if (purchaseDateFromElement == null || purchaseDateFromElement.isEmpty()) {
         estateDto.setDateOfPurchase(null);
      } else {
         LocalDateTime dateOfPurchase = LocalDateTime.parse(purchaseDateFromElement);
         estateDto.setDateOfPurchase(dateOfPurchase);
      }
   }

   private ArrayList<Integer> constructPriceHistoryFromString(String priceHistoryFromElement) {
      ArrayList<Integer> priceHistory = new ArrayList<Integer>();

      String[] splittedPriceAttributes = priceHistoryFromElement.split("-");

      for (String price : splittedPriceAttributes) {
         if (!price.isEmpty()) {
            priceHistory.add(Integer.parseInt(price));
         }
      }

      return priceHistory;
   }

   private String constructStringFromPriceHistory(Estate estate) {
      String formattedPriceHistory = new String();
      ArrayList<Integer> priceHistory = estate.getPriceHistory();

      if (priceHistory != null) {
         for (int i = 0; i < priceHistory.size(); i++) {
            formattedPriceHistory = formattedPriceHistory + priceHistory.get(i).toString() + "-";
         }
      } else {
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
      attributes.put(DATE_REGISTERED, estate.getDateRegistered().toString());
      attributes.put(ADDRESS, estate.getAddress().toString());
      attributes.put(PRICE_HISTORY, constructStringFromPriceHistory(estate));
      attributes.put(BOUGHT, estate.hasBeenBought().toString());

      addDateOfPurchaseIfEstateBought(estate, attributes);

      return attributes;
   }

   private void addDateOfPurchaseIfEstateBought(Estate estate, HashMap<String, String> attributes) {
      if (estate.hasBeenBought()) {
         attributes.put(PURCHASE_DATE, estate.getDateOfPurchase().toString());
      }
   }

   public EstateDto convertAttributesToDto(HashMap<String, String> attributes) throws ParseException {
      EstateDto estateDto = new EstateDto();

      estateDto.setSellerId(attributes.get(SELLER));
      estateDto.setType(attributes.get(TYPE));

      Integer price = Integer.parseInt(attributes.get(PRICE));
      estateDto.setPrice(price);

      AddressDto addressDto = constructAddressDtoFromString(attributes.get(ADDRESS));
      estateDto.setAddress(addressDto);

      ArrayList<Integer> priceHistory = constructPriceHistoryFromString(attributes.get(PRICE_HISTORY));
      estateDto.setPriceHistory(priceHistory);

      LocalDateTime dateRegistered = LocalDateTime.parse(attributes.get(DATE_REGISTERED));
      estateDto.setDateRegistered(dateRegistered);

      Boolean bought = Boolean.parseBoolean(attributes.get(BOUGHT));
      estateDto.setBought(bought);

      String purchaseDateAttributes = attributes.get(PURCHASE_DATE);
      setDateOfPurchaseToEstateDto(estateDto, purchaseDateAttributes);

      return estateDto;
   }

   public HashMap<String, String> convertDescriptionToAttributes(Description description) {
      return descriptionElementAssembler.convertToAttributes(description);
   }

   public DescriptionDto convertDescriptionAttributesToDto(HashMap<String, String> attributes) {
      return descriptionElementAssembler.convertAttributesToDto(attributes);
   }

}
