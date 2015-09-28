package ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.factory;

import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.DescriptionAssembler;
import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.LandAssembler;
import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.RoomAssembler;

public class DescriptionAssemblerFactory {

   //Est-ce que c'est mauvaise de lui pousser Room & Land Assembler?
   public DescriptionAssembler createDescriptionAssembler() {
      return new DescriptionAssembler(new RoomAssembler(), new LandAssembler());
   }
}
