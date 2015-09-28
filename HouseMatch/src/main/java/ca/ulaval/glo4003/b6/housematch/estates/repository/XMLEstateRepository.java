package ca.ulaval.glo4003.b6.housematch.estates.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Description;
import ca.ulaval.glo4003.b6.housematch.estates.domain.Estate;
import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.EstateAssembler;
import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstatePersistenceDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.factories.EstatePersistenceDtoFactory;
import ca.ulaval.glo4003.b6.housematch.estates.persistences.EstateElementAssembler;
import ca.ulaval.glo4003.b6.housematch.estates.persistences.EstateElementAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.persistance.XMLFileEditor;
import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;

public class XMLEstateRepository implements EstateRepository {

   private static final String ADDRESS_KEY = "address";

   private static final String ESTATE = "estate";

   private static final String XML_FILE_PATH = "persistence/estates.xml";

   private XMLFileEditor xmlFileEditor;

   private EstateAssemblerFactory estateAssemblerFactory;

   private EstateElementAssemblerFactory estateElementAssemblerFactory;

   private EstatePersistenceDtoFactory estatePersistenceDtoFactory;

   public XMLEstateRepository(EstateAssemblerFactory estateAssemblerFactory,
         EstatePersistenceDtoFactory estatePersistenceDtoFactory,
         EstateElementAssemblerFactory estateElementAssemblerFactory) {

      this.estateAssemblerFactory = estateAssemblerFactory;
      this.estatePersistenceDtoFactory = estatePersistenceDtoFactory;
      this.estateElementAssemblerFactory = estateElementAssemblerFactory;
      this.xmlFileEditor = new XMLFileEditor();

   }

   protected XMLEstateRepository(EstateAssemblerFactory estateAssemblerFactory,
         EstatePersistenceDtoFactory estatePersistenceDtoFactory,
         EstateElementAssemblerFactory estateElementAssemblerFactory, XMLFileEditor xmlFileEditor) {
      this.estateAssemblerFactory = estateAssemblerFactory;
      this.estatePersistenceDtoFactory = estatePersistenceDtoFactory;
      this.estateElementAssemblerFactory = estateElementAssemblerFactory;
      this.xmlFileEditor = xmlFileEditor;
   }

   @Override
   public List<Estate> getAllEstates() {
      List<Estate> estates = new ArrayList<Estate>();
      try {
         Document estateDocument = xmlFileEditor.readXMLFile(XML_FILE_PATH);

         Collection<Element> elementList = xmlFileEditor.getAllElementsFromDocument(estateDocument, ESTATE);

         EstateAssembler estateAssembler = estateAssemblerFactory.createEstateAssembler();
         EstateElementAssembler estateElementAssembler = estateElementAssemblerFactory.createAssembler();
         estates = getDtoListFromElements(elementList, estateAssembler, estateElementAssembler);

      } catch (DocumentException e) {
         throw new CouldNotAccessDataException("Problem when fetching all estate", e);
      }

      return estates;
   }

   private List<Estate> getDtoListFromElements(Collection<Element> elementList, EstateAssembler estateAssembler,
         EstateElementAssembler estateElementAssembler) {

      List<Estate> estates = new ArrayList<Estate>();
      for (Element element : elementList) {
         EstateDto convertedEstateDto = estateElementAssembler.convertToDto(element);
         Estate estate = estateAssembler.assembleEstate(convertedEstateDto);
         estates.add(estate);
      }
      return estates;
   }

   @Override
   public void addEstate(Estate estate) {
      try {
         Document estateDocument = xmlFileEditor.readXMLFile(XML_FILE_PATH);

         EstateElementAssembler estateElementAssembler = estateElementAssemblerFactory.createAssembler();
         HashMap<String, String> attributes = estateElementAssembler.convertToAttributes(estate);
         if (isEstatePersisted(estateDocument, attributes)) {
            return;
         }
         addNewEstateToDocument(estateDocument, attributes, estatePersistenceDtoFactory);
         saveEstateDocument(estateDocument);
      } catch (DocumentException e) {
         throw new CouldNotAccessDataException("Unable to add an estate", e);
      } catch (IOException e) {
         throw new CouldNotAccessDataException("Unable to save the added estate", e);
      }
   }

   private void saveEstateDocument(Document estateDocument) throws IOException {

      xmlFileEditor.formatAndWriteDocument(estateDocument, XML_FILE_PATH);
   }

   private boolean isEstatePersisted(Document existingDocument, HashMap<String, String> attributes) {
      return xmlFileEditor.elementWithCorrespondingValuesExists(existingDocument, "estates/estate/address",
            attributes.get(ADDRESS_KEY));
   }

   private void addNewEstateToDocument(Document document, HashMap<String, String> attributes,
         EstatePersistenceDtoFactory estatePersistenceDtoFactory) {
      // TODO BORIS doit faire l'integration
      EstatePersistenceDto estatePersistenceDto = estatePersistenceDtoFactory.newInstance(attributes);
      System.out.println(document);
      xmlFileEditor.addNewElementToDocument(document, estatePersistenceDto);
   }

   @Override
   public void editEstate(Estate estate) {
      // TODO Auto-generated method stub
      // On fetch le estate puis on le re-persiste avec ses details?
   }

   @Override
   public void addDescription(Description description) {
      // TODO Auto-generated method stub
      
   }
}
