package ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.factory;

import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.AddressAssembler;
import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.DescriptionAssembler;
import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.EstateAssembler;

public class EstateAssemblerFactory {

   public EstateAssembler createEstateAssembler() {
      AddressAssembler addressAssembler = new AddressAssemblerFactory().createAddressAssembler();
      DescriptionAssembler descriptionAssembler = new DescriptionAssemblerFactory().createDescriptionAssembler();
      return new EstateAssembler(addressAssembler, descriptionAssembler);
   }

}
