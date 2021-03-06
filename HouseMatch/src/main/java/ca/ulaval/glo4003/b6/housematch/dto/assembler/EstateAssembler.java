package ca.ulaval.glo4003.b6.housematch.dto.assembler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4003.b6.housematch.domain.estate.Address;
import ca.ulaval.glo4003.b6.housematch.domain.estate.Description;
import ca.ulaval.glo4003.b6.housematch.domain.estate.Estate;
import ca.ulaval.glo4003.b6.housematch.domain.estate.Estates;
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
      boolean bought = estateDto.isBought();
      LocalDateTime dateOfPurchase = estateDto.getDateOfPurchase();

      LocalDateTime dateRegistered = estateDto.getDateRegistered();
      LocalDateTime dateModified = estateDto.getDateModified();
      Description description = descriptionAssembler.assembleDescription(estateDto.getDescriptionDto());
      ArrayList<Integer> priceHistory = estateDto.getPriceHistory();

      Estate estate = new Estate(type, address, price, seller, description, dateRegistered, priceHistory, bought,
            dateOfPurchase, dateModified);

      return estate;
   }

   public EstateDto assembleEstateDto(Estate estate) {
      AddressDto address = addressAssembler.assembleAddressDto(estate.getAddress());
      String type = estate.getType();
      Integer price = estate.getPrice();
      String sellerId = estate.getSeller();
      boolean hasBeenBought = estate.hasBeenBought();

      LocalDateTime dateRegistered = estate.getDateRegistered();
      LocalDateTime dateModified = estate.getDateModified();
      DescriptionDto descriptionDto = descriptionAssembler.assembleDescriptionDto(estate.getDescription());

      ArrayList<Integer> priceHistory = estate.getPriceHistory();

      LocalDateTime dateOfPurchase = estate.getDateOfPurchase();
      EstateDto estateDto = new EstateDto(type, address, price, sellerId, dateRegistered, descriptionDto, priceHistory,
            hasBeenBought, dateOfPurchase, dateModified);

      return estateDto;
   }

   public Description assembleDescription(DescriptionDto descriptionDto) {
      Description description = descriptionAssembler.assembleDescription(descriptionDto);
      return description;
   }

   public List<EstateDto> assembleEstatesDto(Estates estates) {

      List<EstateDto> estatesDto = new ArrayList<EstateDto>();
      for (Estate estate : estates.retreiveListOfEstate()) {
         EstateDto assembledEstateDto = assembleEstateDto(estate);
         estatesDto.add(assembledEstateDto);
      }

      return estatesDto;
   }
}
