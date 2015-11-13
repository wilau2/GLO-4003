package ca.ulaval.glo4003.b6.housematch.web.controllers;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PictureController {

   @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
   public String uploadFileHandler(@RequestParam("name") final String name, @RequestParam("file") MultipartFile file,
         HttpServletRequest request) throws IOException {
      if (!file.isEmpty()) {
         byte[] bytes = file.getBytes();
         // Creating the directory to store file
         File dir = new File("./persistence/uploadedPictures");
         if (!dir.exists()) {
            dir.mkdirs();
         }

         // Create the file on server
         File serverFile = new File(dir.getAbsolutePath() + File.separator + name + ".jpg");
         BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
         stream.write(bytes);
         stream.close();
      }
      return "index";
   }

   @RequestMapping(value = "/picture/{pictureId}", method = RequestMethod.GET, produces = "image/jpg")
   public @ResponseBody byte[] getFile(@PathVariable("pictureId") String id, HttpServletRequest request) {
      try {
         InputStream inputStream = this.getClass().getResourceAsStream("picture" + id + ".jpg");

         BufferedImage picture = ImageIO.read(inputStream);

         ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

         ImageIO.write(picture, "jpg", outputStream);

         return outputStream.toByteArray();
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

}
