package ca.ulaval.glo4003.b6.housematch.domain.picture;

import java.util.List;

public class Album {

   private String estateAddress;

   private List<String> activePictureNames;

   public Album(String estateAddress, List<String> activePictureNames) {
      this.estateAddress = estateAddress;
      this.activePictureNames = activePictureNames;
   }

   public String getEstateAddress() {
      return estateAddress;
   }

   public List<String> getActivePictureNames() {
      return activePictureNames;
   }

}
