package ca.ulaval.glo4003.b6.housematch.estates.domain.assembler;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Description;
import ca.ulaval.glo4003.b6.housematch.estates.domain.Estate;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;

public class EstateAssembler {

   public Estate assembleEstate(EstateDto estateDto) {
      String address = estateDto.getAddress();
      String type = estateDto.getType();
      Integer price = estateDto.getPrice();
      String seller = estateDto.getSeller();
      Description description = null;

      Estate estate = new Estate(type, address, price, seller, description);
      return estate;
   }

   public EstateDto assembleEstateDto(Estate estate) {
      String address = estate.getAddress();
      String type = estate.getType();
      Integer price = estate.getPrice();
      String sellerId = estate.getSeller();

      EstateDto estateDto = new EstateDto(type, address, price, sellerId);
      return estateDto;
   }
}
