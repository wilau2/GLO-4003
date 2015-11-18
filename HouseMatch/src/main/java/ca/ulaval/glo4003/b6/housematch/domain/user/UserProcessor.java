package ca.ulaval.glo4003.b6.housematch.domain.user;

import java.util.List;

public class UserProcessor {

   public int getNumberOfActiveBuyer(List<User> users) {

      int numberOfActiveBuyer = 0;
      for (User user : users) {
         if (user.isBuyer() && user.wasActiveInTheLastSixMonths()) {
            numberOfActiveBuyer++;
         }
      }
      return numberOfActiveBuyer;
   }

}
