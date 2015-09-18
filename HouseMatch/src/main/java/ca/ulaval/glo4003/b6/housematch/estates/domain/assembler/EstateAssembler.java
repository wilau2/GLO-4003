package ca.ulaval.glo4003.b6.housematch.estates.domain.assembler;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Estate;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;

public class EstateAssembler {

   public Estate assembleEstate(EstateDto estateDto) {
      String address = estateDto.getAddress();
      String type = estateDto.getType();
      Integer price = estateDto.getPrice();

      Estate estate = new Estate(type, address, price);
      return estate;
   }

}
