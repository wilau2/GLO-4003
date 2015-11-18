package ca.ulaval.glo4003.b6.housematch.domain.picture;

import java.util.UUID;

public class Picture {

   private String address;

   private String name;

   private String uid;

   private Boolean active;

   public Picture(String uid, String address, String name, String active) {
      if (uid == null || uid.isEmpty()) {
         this.uid = UUID.randomUUID().toString();
      } else {
         this.uid = uid;
      }
      this.address = address;
      this.name = name;
      if (active == null || active.isEmpty()) {
         this.active = false;
      }
      if (active.equals("true")) {
         this.active = true;
      } else {
         this.active = false;
      }

   }

   public String getAddress() {
      return this.address;
   }

   public String getName() {
      return this.name;
   }

   public String getUid() {
      return this.uid;
   }

   public void activate() {
      this.active = true;
   }

   public Boolean isActive() {
      return this.active;
   }

   public boolean isFromEstate(String address) {
      if (this.address.equals(address)) {
         return true;
      } else {
         return false;
      }
   }

   public boolean isValidUid(String uid) {
      if (this.uid.equals(uid)) {
         return true;
      } else {
         return false;
      }
   }

   public Boolean getActive() {
      return this.active;
   }

}
