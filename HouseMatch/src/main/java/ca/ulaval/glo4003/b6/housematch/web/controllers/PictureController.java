package ca.ulaval.glo4003.b6.housematch.web.controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PictureController {

   @RequestMapping(value = "/picture/{address}/{pictureName}", method = RequestMethod.GET, produces = "image/jpg")
   public @ResponseBody byte[] getFile(@PathVariable("address") String address,
         @PathVariable("pictureName") String name, HttpServletRequest request) {
      try {

         File dir = new File("./persistence/uploadedPictures/" + address);
         if (!dir.exists()) {
            dir.mkdirs();
         }

         // Create the file on server
         File serverFile = new File(dir.getAbsolutePath() + File.separator + name + ".jpg");
         InputStream inputStream = new FileInputStream(serverFile);
         BufferedImage picture = ImageIO.read(inputStream);

         ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

         ImageIO.write(picture, "jpg", outputStream);

         return outputStream.toByteArray();
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

}
