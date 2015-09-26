package ca.ulaval.glo4003.b6.housematch.estates.domain.assembler;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Address;
import ca.ulaval.glo4003.b6.housematch.estates.dto.AddressDto;

public class AddressAssembler {
   public Address assembleAddress(AddressDto addressDto) {
      
      Integer appartment = addressDto.getAppartment();
      Integer civicNumber = addressDto.getCivicNumber();
      String street = addressDto.getStreet();
      String postalCode = addressDto.getPostalCode();
      String province = addressDto.getProvince();
      String country = addressDto.getCountry();
      
      return new Address(appartment,civicNumber, street, postalCode, province, country);
   }
   
   public AddressDto assembleAddressDto(Address address){
      Integer appartment = address.getAppartment();
      Integer civicNumber = address.getCivicNumber();
      String street = address.getStreet();
      String postalCode = address.getPostalCode();
      String province = address.getProvince();
      String country = address.getCountry();
      
      return new AddressDto(appartment, civicNumber, street, postalCode, province, country);
   }
}
