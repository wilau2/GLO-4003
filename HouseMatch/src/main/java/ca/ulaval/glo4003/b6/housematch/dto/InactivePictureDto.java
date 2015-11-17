package ca.ulaval.glo4003.b6.housematch.dto;

public class InactivePictureDto {

   private String uid;

   private String address;

   private String name;

   public InactivePictureDto(String uid, String address, String name) {
      this.name = name;
      this.address = address;
      this.uid = uid;
   }

   public String getName() {
      return name;
   }

   public String getAddress() {
      return address;
   }

   public String getUid() {
      return uid;
   }

}
