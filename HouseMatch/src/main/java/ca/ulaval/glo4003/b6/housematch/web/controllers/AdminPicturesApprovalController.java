package ca.ulaval.glo4003.b6.housematch.web.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.EstateNotFoundException;
import ca.ulaval.glo4003.b6.housematch.domain.picture.Picture;
import ca.ulaval.glo4003.b6.housematch.domain.user.Role;
import ca.ulaval.glo4003.b6.housematch.dto.InformationPictureDto;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.persistence.picture.UUIDAlreadyExistsException;
import ca.ulaval.glo4003.b6.housematch.services.picture.PictureApprobationService;
import ca.ulaval.glo4003.b6.housematch.services.user.UserSessionAuthorizationService;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.InvalidAccessException;

@Controller
public class AdminPicturesApprovalController {

   private static final String EXPECTED_ROLE = Role.ADMIN;

   private PictureApprobationService inactivePictureApprover;

   private UserSessionAuthorizationService userSessionAuthorizationService;

   @Autowired
   public AdminPicturesApprovalController(PictureApprobationService inactivePictureApprover,
         UserSessionAuthorizationService userSessionAuthorizationService) {
      this.inactivePictureApprover = inactivePictureApprover;
      this.userSessionAuthorizationService = userSessionAuthorizationService;
   }

   @RequestMapping("/admin")
   public String admin(HttpServletRequest request) throws InvalidAccessException {
      userSessionAuthorizationService.verifySessionIsAllowed(request, EXPECTED_ROLE);
      return "admin_dashboard";
   }

   @RequestMapping(value = "/admin/pictures", method = RequestMethod.GET)
   public ModelAndView getInactivePictures(HttpServletRequest request)
         throws CouldNotAccessDataException, InvalidAccessException {
      userSessionAuthorizationService.verifySessionIsAllowed(request, EXPECTED_ROLE);

      List<Picture> inactivePictures = inactivePictureApprover.getAllInactivePictures();

      ModelAndView adminInactivePicturesViewModel = new ModelAndView("admin_inactive_pictures");
      adminInactivePicturesViewModel.addObject("pictures", inactivePictures);
      adminInactivePicturesViewModel.addObject("album", new InformationPictureDto());

      return adminInactivePicturesViewModel;
   }

   @RequestMapping(value = "/admin/pictures", params = "approve", method = RequestMethod.POST)
   public String approveInactivesPictures(HttpServletRequest request, InformationPictureDto inactivePictureDto)
         throws CouldNotAccessDataException, InvalidAccessException, UUIDAlreadyExistsException,
         EstateNotFoundException {
      userSessionAuthorizationService.verifySessionIsAllowed(request, EXPECTED_ROLE);

      inactivePictureApprover.approvePictures(inactivePictureDto.getUidsToList());

      return "redirect:/admin/pictures/";
   }

   @RequestMapping(value = "/admin/pictures", params = "delete", method = RequestMethod.POST)
   public String deleteInactivesPictures(HttpServletRequest request, InformationPictureDto inactivePictureDto)
         throws CouldNotAccessDataException, InvalidAccessException {
      userSessionAuthorizationService.verifySessionIsAllowed(request, EXPECTED_ROLE);

      inactivePictureApprover.unapprovePictures(inactivePictureDto.getUidsToList());

      return "redirect:/admin/pictures/";
   }

   @RequestMapping(value = "/admin/pictures/{uid}", method = RequestMethod.GET, produces = "image/jpg")
   public @ResponseBody byte[] getInactivePicture(@PathVariable("uid") String uid, HttpServletRequest request)
         throws CouldNotAccessDataException, InvalidAccessException {
      userSessionAuthorizationService.verifySessionIsAllowed(request, EXPECTED_ROLE);

      return inactivePictureApprover.getInactivePictureContent(uid);
   }

}
