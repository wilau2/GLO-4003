package ca.ulaval.glo4003.b6.housematch.web.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ca.ulaval.glo4003.b6.housematch.domain.estate.WrongFilterTypeException;
import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.EstateAlreadyBoughtException;
import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.EstateNotFoundException;
import ca.ulaval.glo4003.b6.housematch.domain.estate.filters.InconsistentFilterParamaterException;
import ca.ulaval.glo4003.b6.housematch.domain.user.Role;
import ca.ulaval.glo4003.b6.housematch.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.dto.PictureDto;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.estate.EstatesFetcher;
import ca.ulaval.glo4003.b6.housematch.services.estate.EstatesService;
import ca.ulaval.glo4003.b6.housematch.services.picture.EstatePicturesService;
import ca.ulaval.glo4003.b6.housematch.services.user.UserSessionAuthorizationService;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.InvalidAccessException;

@Controller
public class BuyerSearchEstatesController {

   private static final String EXPECTED_ROLE = Role.BUYER;

   private EstatesFetcher estatesFetcher;

   private UserSessionAuthorizationService userSessionAuthorizationService;

   private EstatePicturesService estatePicturesService;

   private List<EstateDto> allEstates;

   private EstatesService estateService;

   @Autowired
   public BuyerSearchEstatesController(EstatesFetcher estatesFetcher,
         UserSessionAuthorizationService userSessionAuthorizationService, EstatePicturesService estatePicturesService,
         EstatesService estateService) {
      this.estatesFetcher = estatesFetcher;
      this.userSessionAuthorizationService = userSessionAuthorizationService;
      this.estatePicturesService = estatePicturesService;
      this.estateService = estateService;
   }

   @RequestMapping(method = RequestMethod.GET, path = "/buyer/{userId}/estates")
   public ModelAndView searchAllEstates(HttpServletRequest request)
         throws CouldNotAccessDataException, InvalidAccessException {
      userSessionAuthorizationService.verifySessionIsAllowed(request, EXPECTED_ROLE);

      ModelAndView modelAndView = new ModelAndView("buyer_search");

      allEstates = estatesFetcher.getAllEstates();
      modelAndView.addObject("estates", allEstates);

      return modelAndView;
   }

   @RequestMapping(method = RequestMethod.GET, path = "/buyer/{userId}/estates", params = "sort")
   public ModelAndView searchAllEstatesSort(HttpServletRequest request, @RequestParam("sort") String sortType)
         throws CouldNotAccessDataException, InvalidAccessException {
      userSessionAuthorizationService.verifySessionIsAllowed(request, EXPECTED_ROLE);

      ModelAndView modelAndView = new ModelAndView("buyer_search");

      allEstates = estatesFetcher.getSortedEstates(sortType);

      modelAndView.addObject("estates", allEstates);

      return modelAndView;
   }

   @RequestMapping(method = RequestMethod.GET, path = "/buyer/{userId}/estates", params = "filtered")

   public ModelAndView searchAllEstatesFilter(HttpServletRequest request, @RequestParam("type") String filterType,
         @RequestParam("minPrice") int minPrice, @RequestParam("maxPrice") int maxPrice)
               throws CouldNotAccessDataException, InvalidAccessException, WrongFilterTypeException,
               InconsistentFilterParamaterException {
      userSessionAuthorizationService.verifySessionIsAllowed(request, EXPECTED_ROLE);

      ModelAndView modelAndView = new ModelAndView("buyer_search");
      allEstates = estatesFetcher.filter(filterType, minPrice, maxPrice);
      modelAndView.addObject("estates", allEstates);

      return modelAndView;
   }

   @RequestMapping(method = RequestMethod.GET, path = "/buyer/{userId}/estates/{address}")
   public ModelAndView getEstateByAddress(@PathVariable("address") String address, HttpServletRequest request)
         throws EstateNotFoundException, CouldNotAccessDataException, InvalidAccessException {
      userSessionAuthorizationService.verifySessionIsAllowed(request, EXPECTED_ROLE);

      ModelAndView modelAndView = new ModelAndView("estate");

      EstateDto estateByAddress = estatesFetcher.getEstateByAddress(address);

      List<PictureDto> pictures = estatePicturesService.getPublicPicturesOfEstate(address);
      modelAndView.addObject("estate", estateByAddress);
      modelAndView.addObject("description", estateByAddress.getDescriptionDto());
      modelAndView.addObject("pictures", pictures);

      return modelAndView;
   }

   @RequestMapping(method = RequestMethod.POST, path = "/buyer/{userId}/estates/{address}")
   public String buyAnEstate(@PathVariable("address") String address, HttpServletRequest request)
         throws InvalidAccessException, EstateNotFoundException, CouldNotAccessDataException,
         EstateAlreadyBoughtException {

      userSessionAuthorizationService.verifySessionIsAllowed(request, EXPECTED_ROLE);

      estateService.buyEstate(address);

      return "redirect:/buyer/{userId}/estates/" + address;
   }

}
