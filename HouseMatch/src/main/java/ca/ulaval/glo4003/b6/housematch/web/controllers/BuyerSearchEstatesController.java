package ca.ulaval.glo4003.b6.housematch.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.estates.repository.factory.EstateRepositoryFactory;
import ca.ulaval.glo4003.b6.housematch.estates.services.EstatesFetcher;
import ca.ulaval.glo4003.b6.housematch.estates.services.EstatesFetcherFactory;

public class BuyerSearchEstatesController {

   private EstatesFetcherFactory estatesFetcherFactory;

   private EstateAssemblerFactory estateAssemblerFactory;

   private EstateRepositoryFactory estateRepositoryFactory;

   @Autowired
   public BuyerSearchEstatesController(EstatesFetcherFactory estatesFetcherFactory,
         EstateAssemblerFactory estateAssemblerFactory, EstateRepositoryFactory estateRepositoryFactory) {
      this.estatesFetcherFactory = estatesFetcherFactory;
      this.estateAssemblerFactory = estateAssemblerFactory;
      this.estateRepositoryFactory = estateRepositoryFactory;
   }

   @RequestMapping(method = RequestMethod.GET, path = "/buyer/{userId}")
   public ModelAndView searchAllEstates() {
      ModelAndView modelAndView = new ModelAndView("buyer_search");

      EstatesFetcher estatesFetcher = estatesFetcherFactory.newInstance(estateAssemblerFactory,
            estateRepositoryFactory);

      List<EstateDto> allEstates = estatesFetcher.getAllEstates();
      modelAndView.addObject("estates", allEstates);

      return modelAndView;
   }

}
