package ca.ulaval.glo4003.b6.housematch.domain.estate;


public class EstatePriceComparator implements EstateComparator{

   @Override
   public int compare(Estate estate1, Estate estate2) {
      // TODO Auto-generated method stub
      return estate2.getPrice().compareTo(estate1.getPrice());
   }

}
