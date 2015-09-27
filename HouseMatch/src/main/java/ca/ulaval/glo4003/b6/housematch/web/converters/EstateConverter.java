package ca.ulaval.glo4003.b6.housematch.web.converters;

import ca.ulaval.glo4003.b6.housematch.estates.dto.AddressDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.web.viewModel.EstateModel;

public class EstateConverter {

   public EstateDto convertToDto(EstateModel estateModel) {
      EstateDto estateDto = new EstateDto();

      AddressDto addressDto = extractAddressInformation(estateModel);
      estateDto.setAddress(addressDto);

      estateDto.setPrice(estateModel.getPrice());
      estateDto.setType(estateModel.getType());
      estateDto.setSellerId(estateModel.getSeller());

      return estateDto;
   }

   private AddressDto extractAddressInformation(EstateModel estateModel) {
      AddressDto addressDto = new AddressDto();

      addressDto.setPostalCode(estateModel.getPostalCode());
      addressDto.setCivicNumber(estateModel.getCivicNumber());
      addressDto.setCountry(estateModel.getCountry());
      addressDto.setState(estateModel.getState());
      addressDto.setStreet(estateModel.getStreet());
      addressDto.setAppartment(estateModel.getAppartment());

      return addressDto;
   }

   public EstateModel convertToModel(EstateDto estateDto) {
      EstateModel estateModel = new EstateModel();

      estateModel.setType(estateDto.getType());
      estateModel.setPrice(estateDto.getPrice());
      estateModel.setSeller(estateDto.getSeller());

      AddressDto addressDto = estateDto.getAddress();

      estateModel.setAppartment(addressDto.getAppartment());
      estateModel.setCivicNumber(addressDto.getCivicNumber());
      estateModel.setStreet(addressDto.getStreet());
      estateModel.setState(addressDto.getState());
      estateModel.setCountry(addressDto.getCountry());
      estateModel.setPostalCode(addressDto.getPostalCode());

      return estateModel;
   }

}
