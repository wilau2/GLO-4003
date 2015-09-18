package ca.ulaval.glo4003.b6.housematch.web.converters;

import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.web.viewModel.EstateModel;

public class EstateConverter {

   public EstateDto convertToDto(EstateModel estateModel) {
      EstateDto estateDto = new EstateDto();

      estateDto.setAddress(estateModel.getAddress());
      estateDto.setPrice(estateModel.getPrice());
      estateDto.setType(estateModel.getType());

      return estateDto;
   }

}
