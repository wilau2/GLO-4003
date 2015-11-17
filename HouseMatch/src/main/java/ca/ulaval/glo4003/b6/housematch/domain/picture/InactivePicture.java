package ca.ulaval.glo4003.b6.housematch.domain.picture;

import java.util.UUID;

public class InactivePicture {

   private String address;

   private String name;

   private String uid;

   public InactivePicture(String uid, String address, String name) {
      if (uid == null || uid.isEmpty()) {
         this.uid = UUID.randomUUID().toString();
      } else {
         this.uid = uid;
      }
      this.address = address;
      this.name = name;
   }

   public String getAddress() {
      return address;
   }

   public String getName() {
      return name;
   }

   public String getUid() {
      return uid;
   }

}
