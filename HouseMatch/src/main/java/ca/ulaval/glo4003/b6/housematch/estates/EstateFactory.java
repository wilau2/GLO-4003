package ca.ulaval.glo4003.b6.housematch.estates;

import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;

public class EstateFactory {

   public Estate createEstate(EstateDto estateDto) {
      String type = estateDto.getType();
      String address = estateDto.getAddress();
      Integer price = estateDto.getPrice();

      if (type == "CONDO") {
         return new Condo(address, price);
      } else
         return new Lot(address, price);
   }

}
