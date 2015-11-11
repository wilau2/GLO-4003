package ca.ulaval.glo4003.b6.housematch.web.controllers;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PictureController {

   @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
   public @ResponseBody String uploadFileHandler(@RequestParam("name") String name,
         @RequestParam("file") MultipartFile file) {
      if (!file.isEmpty()) {
         try {
            byte[] bytes = file.getBytes();
            // Creating the directory to store file
            File dir = new File("./persistence/uploadedPictures");
            if (!dir.exists()) {
               dir.mkdirs();
            }

            // Create the file on server
            File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();

            return "You successfully uploaded file=" + name;
         } catch (Exception e) {
            return "You failed to upload " + name + " => " + e.getMessage();
         }
      } else {
         return "You failed to upload " + name + " because the file was empty.";
      }
   }

   @RequestMapping(value = "/picture", method = RequestMethod.GET, produces = "image/jpg")
   public @ResponseBody byte[] getFile() {
      try {
         // Retrieve image from the classpath.
         InputStream is = this.getClass().getResourceAsStream("house.jpg");

         // Prepare buffered image.
         BufferedImage img = ImageIO.read(is);

         // Create a byte array output stream.
         ByteArrayOutputStream bao = new ByteArrayOutputStream();

         // Write to output stream
         ImageIO.write(img, "jpg", bao);

         return bao.toByteArray();
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

}
