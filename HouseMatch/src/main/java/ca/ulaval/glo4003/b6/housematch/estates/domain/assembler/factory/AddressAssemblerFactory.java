package ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.factory;

import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.AddressAssembler;

public class AddressAssemblerFactory {
   
   public AddressAssembler createAddressAssembler(){
      return new AddressAssembler();
   }

}
