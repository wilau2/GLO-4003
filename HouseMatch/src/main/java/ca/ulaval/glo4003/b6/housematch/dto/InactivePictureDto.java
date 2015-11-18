package ca.ulaval.glo4003.b6.housematch.dto;

import java.util.ArrayList;
import java.util.List;

public class InactivePictureDto {

   private String[] uids;

   private String address;

   private String name;

   private String active;

   public InactivePictureDto() {

   }

   public InactivePictureDto(String uid, String address, String name, String active) {
      this.address = address;
      this.name = name;
      this.uid = uid;
      this.active = active;
   }

   private String uid;

   public String[] getUids() {
      return this.uids;
   }

   public List<String> getUidsToList() {
      List<String> uidsList = new ArrayList<String>();
      int size = uids.length;
      for (int i = 0; i < size; i++) {
         uidsList.add(uids[i]);
      }
      return uidsList;
   }

   public void setUids(String[] uids) {
      this.uids = uids;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getUid() {
      return uid;
   }

   public String getActive() {
      return active;
   }

   public void setUid(String uid) {
      this.uid = uid;
   }

}
