package ca.ulaval.glo4003.b6.housematch.estates.domain;

import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;

public class EstateFactory {

   public Estate createEstate(EstateDto estateDto) {
      String type = estateDto.getType();
      String address = estateDto.getAddress();
      Integer price = estateDto.getPrice();
      return null;

   }

}
