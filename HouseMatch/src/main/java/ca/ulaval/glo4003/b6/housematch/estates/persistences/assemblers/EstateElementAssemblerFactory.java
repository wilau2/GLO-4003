package ca.ulaval.glo4003.b6.housematch.estates.persistences.assemblers;

public class EstateElementAssemblerFactory {

   public EstateElementAssembler createAssembler() {
      DescriptionElementAssembler descriptionElementAssembler = new DescriptionElementAssembler();
      return new EstateElementAssembler(descriptionElementAssembler);
   }

}
