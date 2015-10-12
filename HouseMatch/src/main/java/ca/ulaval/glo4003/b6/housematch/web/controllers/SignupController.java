package ca.ulaval.glo4003.b6.housematch.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.ulaval.glo4003.b6.housematch.user.anticorruption.UserSignupCorruptionVerificator;
import ca.ulaval.glo4003.b6.housematch.user.anticorruption.exceptions.InvalidContactInformationFieldException;
import ca.ulaval.glo4003.b6.housematch.user.anticorruption.exceptions.InvalidUserSignupFieldException;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserSignupDto;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.CouldNotAccessUserDataException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UsernameAlreadyExistsException;
import ca.ulaval.glo4003.b6.housematch.user.services.UserLoginService;
import ca.ulaval.glo4003.b6.housematch.user.services.exceptions.InvalidPasswordException;
import ca.ulaval.glo4003.b6.housematch.user.services.exceptions.UserActivationException;
import ca.ulaval.glo4003.b6.housematch.user.services.exceptions.UserNotifyingException;
import ca.ulaval.glo4003.b6.housematch.web.converters.SignupUserConverter;
import ca.ulaval.glo4003.b6.housematch.web.viewModel.SignupUserModel;

@Controller
public class SignupController {

   private SignupUserConverter converter;

   private UserSignupCorruptionVerificator userSignupCorruptionVerificator;

   private UserLoginService userLoginService;

   @Autowired
   public SignupController(SignupUserConverter converter,
         UserSignupCorruptionVerificator userSignupCorruptionVerificator, UserLoginService userLoginService) {
      this.converter = converter;
      this.userSignupCorruptionVerificator = userSignupCorruptionVerificator;
      this.userLoginService = userLoginService;
   }

   @RequestMapping(value = "/signup", method = RequestMethod.POST)
   public String signup(HttpServletRequest request, SignupUserModel viewModel)
         throws InvalidUserSignupFieldException, UserNotFoundException, CouldNotAccessUserDataException,
         InvalidPasswordException, UsernameAlreadyExistsException, InvalidContactInformationFieldException,
         UserNotifyingException, UserActivationException {

      UserSignupDto userSignupDto = converter.convertViewModelToSignupDto(viewModel);
      userSignupCorruptionVerificator.signup(userSignupDto);

      return "need_email_confirmation";

   }

   @RequestMapping(value = "/signup", method = RequestMethod.GET)
   public String signup(Model model) {
      model.addAttribute("user", new SignupUserModel());
      return "signup";
   }
}
