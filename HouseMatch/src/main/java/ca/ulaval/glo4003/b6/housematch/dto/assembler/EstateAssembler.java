package ca.ulaval.glo4003.b6.housematch.dto.assembler;

import java.time.LocalDateTime;
import java.util.Date;

import ca.ulaval.glo4003.b6.housematch.domain.estate.Address;
import ca.ulaval.glo4003.b6.housematch.domain.estate.Description;
import ca.ulaval.glo4003.b6.housematch.domain.estate.Estate;
import ca.ulaval.glo4003.b6.housematch.dto.AddressDto;
import ca.ulaval.glo4003.b6.housematch.dto.DescriptionDto;
import ca.ulaval.glo4003.b6.housematch.dto.EstateDto;

public class EstateAssembler {

   private AddressAssembler addressAssembler;
   private DescriptionAssembler descriptionAssembler;

   public EstateAssembler(AddressAssembler addressAssembler, DescriptionAssembler descriptionAssembler) {
      this.addressAssembler = addressAssembler;
      this.descriptionAssembler = descriptionAssembler;
   }

   public Estate assembleEstate(EstateDto estateDto) {
      Address address = addressAssembler.assembleAddress(estateDto.getAddress());
      String type = estateDto.getType();
      Integer price = estateDto.getPrice();
      String seller = estateDto.getSeller();
      LocalDateTime dateRegistered = estateDto.getDateRegistered();
      Description description = descriptionAssembler.assembleDescription(estateDto.getDescriptionDto());

      Estate estate = new Estate(type, address, price, seller, description, dateRegistered);
      return estate;
   }

   public EstateDto assembleEstateDto(Estate estate) {
      AddressDto address = addressAssembler.assembleAddressDto(estate.getAddress());
      String type = estate.getType();
      Integer price = estate.getPrice();
      String sellerId = estate.getSeller();
      LocalDateTime dateRegistered = estate.getDateRegistered();
      DescriptionDto descriptionDto = descriptionAssembler.assembleDescriptionDto(estate.getDescription());

      EstateDto estateDto = new EstateDto(type, address, price, sellerId, dateRegistered, descriptionDto);
      return estateDto;
   }
   
   public Description assembleDescription(DescriptionDto descriptionDto) {
      Description description = descriptionAssembler.assembleDescription(descriptionDto);
      return description;
   }
}
