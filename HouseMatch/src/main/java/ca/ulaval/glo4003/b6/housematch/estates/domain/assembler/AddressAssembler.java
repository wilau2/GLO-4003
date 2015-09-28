package ca.ulaval.glo4003.b6.housematch.estates.domain.assembler;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Address;
import ca.ulaval.glo4003.b6.housematch.estates.dto.AddressDto;

public class AddressAssembler {

   public AddressDto assembleAddressDto(Address address) {
      AddressDto addressDto = new AddressDto();

      addressDto.setAppartment(address.getAppartment());
      addressDto.setCivicNumber(address.getCivicNumber());
      addressDto.setCountry(address.getCountry());
      addressDto.setPostalCode(address.getPostalCode());
      addressDto.setState(address.getState());
      addressDto.setStreet(address.getStreet());

      return addressDto;
   }

   public Address assembleAddress(AddressDto addressDto) {
      Integer appartment = addressDto.getAppartment();
      Integer civiNumber = addressDto.getCivicNumber();
      String street = addressDto.getStreet();
      String postalCode = addressDto.getPostalCode();
      String province = addressDto.getState();
      String country = addressDto.getCountry();

      Address address = new Address(appartment, civiNumber, street, postalCode, province, country);

      return address;
   }

}
