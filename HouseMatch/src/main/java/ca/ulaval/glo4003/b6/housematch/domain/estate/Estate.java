package ca.ulaval.glo4003.b6.housematch.domain.estate;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

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

   public void addPicture(String name, MultipartFile file) throws IOException {
      if (!file.isEmpty()) {
         byte[] bytes = file.getBytes();
         // Creating the directory to store file
         File dir = new File("./persistence/uploadedPictures/" + address);
         if (!dir.exists()) {
            dir.mkdirs();
         }
         // Create the file on server
         File serverFile = new File(dir.getAbsolutePath() + File.separator + name + ".jpg");
         BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
         stream.write(bytes);
         stream.close();
      }
   }

   public List<Picture> getEveryPictures() {
      List<Picture> manyPictures = new ArrayList<Picture>();
      File dir = new File("./persistence/uploadedPictures/" + address);
      if (!dir.exists()) {
         dir.mkdirs();
      }
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
