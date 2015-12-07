package ca.ulaval.glo4003.b6.housematch.domain.picture;

import java.util.UUID;

public class Picture {

   private String address;

   private String name;

   private String uid;

   private Boolean active;

   public Picture(String uid, String address, String name, String active) {
      generateUid(uid);
      this.address = address;
      this.name = name;
      this.active = turnActiveStringIntoBoolean(active);
   }

   private void generateUid(String uid) {
      if (uid == null || uid.isEmpty()) {
         this.uid = UUID.randomUUID().toString();
      } else {
         this.uid = uid;
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

   void activate() {
      this.active = true;
   }

   public Boolean isActive() {
      return this.active;
   }

   boolean isFromEstate(String address) {
      if (this.address.equals(address)) {
         return true;
      } else {
         return false;
      }
   }

   boolean isValidUid(String uid) {
      if (this.uid.equals(uid)) {
         return true;
      } else {
         return false;
      }
   }

   private Boolean turnActiveStringIntoBoolean(String activeString) {
      Boolean active;
      if (activeString == null || activeString.isEmpty()) {
         active = false;
      } else {
         if (activeString.equals("true")) {
            active = true;
         } else {
            active = false;
         }
      }
      return active;
   }
}
