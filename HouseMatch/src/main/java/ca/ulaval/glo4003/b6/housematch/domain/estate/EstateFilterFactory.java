package ca.ulaval.glo4003.b6.housematch.domain.estate;


public class EstateFilterFactory {
   
   private String PRICE_FILTER = "PRICE";

   public EstateFilter getFilter(String price) throws WrongFilterTypeException {
      
      if(price.equals(PRICE_FILTER)){
         return new EstatePriceFilter();
      }else{
        throw new WrongFilterTypeException("Wrong parameter type pass to the factory : " + price);
      }
   }

}
