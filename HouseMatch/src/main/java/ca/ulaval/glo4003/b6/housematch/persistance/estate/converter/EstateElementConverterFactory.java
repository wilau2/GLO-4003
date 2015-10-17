package ca.ulaval.glo4003.b6.housematch.persistance.estate.converter;

public class EstateElementConverterFactory {

   public EstateElementConverter createAssembler() {
      DescriptionElementConverter descriptionElementAssembler = new DescriptionElementConverter();
      return new EstateElementConverter(descriptionElementAssembler);
   }

}
