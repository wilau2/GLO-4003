package ca.ulaval.glo4003.b6.housematch.persistance.estate.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;

import org.dom4j.Element;

import ca.ulaval.glo4003.b6.housematch.domain.estate.Description;
import ca.ulaval.glo4003.b6.housematch.domain.estate.Estate;
import ca.ulaval.glo4003.b6.housematch.dto.AddressDto;
import ca.ulaval.glo4003.b6.housematch.dto.DescriptionDto;
import ca.ulaval.glo4003.b6.housematch.dto.EstateDto;

public class EstateElementConverter {

   private static final String DATE_FORMAT = "EEE MMM dd kk:mm:ss z yyyy";

   private static final String SELLER = "seller";

   private static final String PRICE = "price";

   private static final String TYPE = "type";

   private static final String ADDRESS = "address";

   private static final String DATE_REGISTERED = "date_registered";
   
   private DateFormat dateFormat;

   private DescriptionElementConverter descriptionElementAssembler;

   public EstateElementConverter(DescriptionElementConverter descriptionElementAssembler) {
      this.descriptionElementAssembler = descriptionElementAssembler;
      dateFormat = new SimpleDateFormat(DATE_FORMAT);
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
      
      LocalDateTime dateRegistered = LocalDateTime.parse(element.elementText(DATE_REGISTERED));
      estateDto.setDateRegistered(dateRegistered);

      return estateDto;
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

      return attributes;
   }

   public EstateDto convertAttributesToDto(HashMap<String, String> attributes) throws ParseException {
      EstateDto estateDto = new EstateDto();

      estateDto.setSellerId(attributes.get(SELLER));
      estateDto.setType(attributes.get(TYPE));

      Integer price = Integer.parseInt(attributes.get(PRICE));
      estateDto.setPrice(price);

      AddressDto addressDto = constructAddressDtoFromString(attributes.get(ADDRESS));
      estateDto.setAddress(addressDto);
      
      
      String date = attributes.get(DATE_REGISTERED);
      LocalDateTime dateRegistered = LocalDateTime.parse(attributes.get(DATE_REGISTERED));
      estateDto.setDateRegistered(dateRegistered);

      return estateDto;
   }

   public HashMap<String, String> convertDescriptionToAttributes(Description description) {
      return descriptionElementAssembler.convertToAttributes(description);
   }

   public DescriptionDto convertDescriptionAttributesToDto(HashMap<String, String> attributes) {
      return descriptionElementAssembler.convertAttributesToDto(attributes);
   }
}
