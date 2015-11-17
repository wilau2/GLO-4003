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

import ca.ulaval.glo4003.b6.housematch.domain.picture.InactivePicture;
import ca.ulaval.glo4003.b6.housematch.domain.user.Role;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.admin.InactivePictureApproverService;
import ca.ulaval.glo4003.b6.housematch.services.user.UserAuthorizationService;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.InvalidAccessException;

@Controller
public class AdminPicturesApprovalController {

   private static final String EXPECTED_ROLE = Role.ADMIN;

   private InactivePictureApproverService inactivePictureApprover;

   private UserAuthorizationService userAuthorizationService;

   @Autowired
   public AdminPicturesApprovalController(InactivePictureApproverService inactivePictureApprover,
         UserAuthorizationService userAuthorizationService) {
      this.inactivePictureApprover = inactivePictureApprover;
      this.userAuthorizationService = userAuthorizationService;
   }

   @RequestMapping(value = "/admin/pictures", method = RequestMethod.GET)
   public ModelAndView getInactivePictures(HttpServletRequest request)
         throws CouldNotAccessDataException, InvalidAccessException {
      userAuthorizationService.verifySessionIsAllowed(request, EXPECTED_ROLE);
      List<InactivePicture> inactivePictures = inactivePictureApprover.getAllInactivePictures();
      ModelAndView adminInactivePicturesViewModel = new ModelAndView("admin_inactive_pictures");
      adminInactivePicturesViewModel.addObject("pictures", inactivePictures);

      return adminInactivePicturesViewModel;
   }

   @RequestMapping(value = "/admin/pictures/{uid}", method = RequestMethod.GET, produces = "image/jpg")
   public @ResponseBody byte[] getInactivePicture(@PathVariable("uid") String uid, HttpServletRequest request)
         throws CouldNotAccessDataException, InvalidAccessException {
      userAuthorizationService.verifySessionIsAllowed(request, EXPECTED_ROLE);

      return inactivePictureApprover.getInactivePictureByte(uid);
   }

}
