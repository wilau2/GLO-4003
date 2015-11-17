package ca.ulaval.glo4003.b6.housematch.web.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ca.ulaval.glo4003.b6.housematch.domain.user.Role;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.admin.InactivePictureApprover;
import ca.ulaval.glo4003.b6.housematch.services.user.UserAuthorizationService;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.InvalidAccessException;

@Controller
public class AdminPicturesApprouvalController {

   private static final String EXPECTED_ROLE = Role.ADMIN;

   private InactivePictureApprover inactivePictureApprover;

   private UserAuthorizationService userAuthorizationService;

   @Autowired
   public AdminPicturesApprouvalController(InactivePictureApprover inactivePictureApprover,
         UserAuthorizationService userAuthorizationService) {
      this.inactivePictureApprover = inactivePictureApprover;
      this.userAuthorizationService = userAuthorizationService;
   }

   @RequestMapping(value = "/admin/inactivePictures", method = RequestMethod.GET, produces = "image/jpg")
   public ModelAndView getInactivePictures(HttpServletRequest request)
         throws CouldNotAccessDataException, InvalidAccessException {
      userAuthorizationService.verifySessionIsAllowed(request, EXPECTED_ROLE);
      List<byte[]> inactivePictures = inactivePictureApprover.getAllInactivePictures();

      ModelAndView adminInactivePicturesViewModel = new ModelAndView("admin_inactive_pictures");
      adminInactivePicturesViewModel.addObject("pictures", inactivePictures);
      return adminInactivePicturesViewModel;
   }
}
