package ca.ulaval.glo4003.b6.housematch.dto.assembler.factory;

import ca.ulaval.glo4003.b6.housematch.dto.assembler.AddressAssembler;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.DescriptionAssembler;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.EstateAssembler;

public class EstateAssemblerFactory {

   public EstateAssembler createEstateAssembler() {
      AddressAssembler addressAssembler = new AddressAssembler();
      DescriptionAssembler descriptionAssembler = new DescriptionAssembler();
      return new EstateAssembler(addressAssembler, descriptionAssembler);
   }

}
