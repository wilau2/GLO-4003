package ca.ulaval.glo4003.b6.housematch.domain.estate;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Estate {

   private Integer price;

   private Address address;

   private String type;

   private String seller;

   private Description description;

   private Album album;

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

   public void setAlbum(Album album) {
      this.album = album;
   }

   public void deletePicture(String pictureName) {
      album.deletePicture(pictureName, address.toString());
   }

   public void addPicture(String pictureName, MultipartFile file) throws IOException {
      album.addPicture(pictureName, address.toString(), file);
   }

   public List<String> getEveryPicturesNames() {
      return album.getEveryPicturesNames(address.toString());
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
