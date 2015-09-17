package ca.ulaval.glo4003.b7.housematch.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogoutController {

  @RequestMapping(value = "/logout", method = RequestMethod.GET)
  public String logout(HttpServletRequest request) {
    request.getSession().setAttribute("loggedInUserEmail", null);
    request.getSession().setAttribute("loggedInUserRole", null);
    return "redirect:/";
  }

}
