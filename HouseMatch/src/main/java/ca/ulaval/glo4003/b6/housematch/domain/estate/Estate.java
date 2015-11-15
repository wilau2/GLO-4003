package ca.ulaval.glo4003.b6.housematch.domain.estate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Estate {

   private Integer price;

   private Address address;

   private String type;

   private String seller;

   private Description description;

   public Estate(String type, Address address, Integer price, String seller, Description description) {
      this.type = type;
      this.address = address;
      this.price = price;
      this.seller = seller;
      this.description = description;
   }

   public String getType() {
      return this.type;
   }

   public Address getAddress() {
      return this.address;
   }

   public Integer getPrice() {
      return this.price;
   }

   public String getSeller() {
      return seller;
   }

   public Description getDescription() {
      return description;
   }

   public List<Picture> getEveryPictures() {

      List<Picture> manyPictures = new ArrayList<Picture>();
      File dir = new File("./persistence/uploadedPictures");

      File[] filesList = dir.listFiles();
      for (File f : filesList) {
         if (f.isFile()) {
            String nomDuFichier = f.getName();
            if (nomDuFichier.indexOf(".") > 0)
               nomDuFichier = nomDuFichier.substring(0, nomDuFichier.lastIndexOf("."));
            manyPictures.add(new Picture("/picture/", nomDuFichier));
         }
      }

      Album temporaryAlbum = new Album(manyPictures);
      return temporaryAlbum.getAllPictures();
   }

   public boolean isFromSeller(String sellerName) {
      if (seller.equals(sellerName)) {
         return true;
      }
      return false;
   }

   public void editDescription(Description description) {
      this.description = description;
   }
}
