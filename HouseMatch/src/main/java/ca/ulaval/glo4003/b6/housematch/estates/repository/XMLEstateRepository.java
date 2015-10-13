package ca.ulaval.glo4003.b6.housematch.estates.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Description;
import ca.ulaval.glo4003.b6.housematch.estates.domain.Estate;
import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.EstateAssembler;
import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.estates.dto.DescriptionDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.DescriptionPersistenceDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstatePersistenceDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.factories.EstatePersistenceDtoFactory;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.EstateNotFoundException;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.SellerNotFoundException;
import ca.ulaval.glo4003.b6.housematch.estates.persistences.assemblers.EstateElementAssembler;
import ca.ulaval.glo4003.b6.housematch.estates.persistences.assemblers.EstateElementAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.persistance.XMLFileEditor;
import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;

public class XMLEstateRepository implements EstateRepository {

   private static final String PATH_TO_ESTATE = "estates/estate";

   private static final String PATH_TO_ADDRESS = "estates/estate/address";

   private static final String ADDRESS_KEY = "address";
   
   private static final String CHILD_DESCRIPTION_KEY = "description";

   private static final String XML_DIRECTORY_PATH = "persistence/estates.xml";

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
   public List<Estate> getAllEstates() throws CouldNotAccessDataException {
      List<Estate> estates = new ArrayList<Estate>();
      try {
         Document estateDocument = xmlFileEditor.readXMLFile(XML_DIRECTORY_PATH);

         List<Element> elementList = xmlFileEditor.getAllElementsFromDocument(estateDocument, PATH_TO_ESTATE);

         EstateAssembler estateAssembler = estateAssemblerFactory.createEstateAssembler();
         EstateElementAssembler estateElementAssembler = estateElementAssemblerFactory.createAssembler();
         estates = getDtoListFromElements(elementList, estateAssembler, estateElementAssembler);

      } catch (DocumentException e) {
         throw new CouldNotAccessDataException("Problem when fetching all estate", e);
      }

      return estates;
   }

   private List<Estate> getDtoListFromElements(List<Element> elementList, EstateAssembler estateAssembler,
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
   public void addEstate(Estate estate) throws CouldNotAccessDataException {
      try {

         Document estateDocument = xmlFileEditor.readXMLFile(XML_DIRECTORY_PATH);

         EstateElementAssembler estateElementAssembler = estateElementAssemblerFactory.createAssembler();
         HashMap<String, String> attributes = estateElementAssembler.convertToAttributes(estate);
         if (isEstatePersisted(estateDocument, attributes.get(ADDRESS_KEY))) {
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
      xmlFileEditor.formatAndWriteDocument(estateDocument, XML_DIRECTORY_PATH);
   }

   private boolean isEstatePersisted(Document existingDocument, String address) {
      return xmlFileEditor.elementWithCorrespondingValueExists(existingDocument, PATH_TO_ADDRESS, address);
   }

   private void addNewEstateToDocument(Document document, HashMap<String, String> attributes,
         EstatePersistenceDtoFactory estatePersistenceDtoFactory) {
      
      EstatePersistenceDto estatePersistenceDto = estatePersistenceDtoFactory.newInstanceEstate(attributes);

      xmlFileEditor.addNewElementToDocument(document, estatePersistenceDto);
   }
   
   @Override
   public void editDescription(String address, Description description) throws CouldNotAccessDataException {
         Document estateDocument;
         try {
            estateDocument = xmlFileEditor.readXMLFile(XML_DIRECTORY_PATH);
            
            EstateDto estateDto = assembleDtoFromDocumentAttributes(address, estateDocument); 
            Estate estate = assembleEstate(estateDto);
            EstateElementAssembler estateElementAssembler = estateElementAssemblerFactory.createAssembler();
            HashMap<String, String> attributes = estateElementAssembler.convertToAttributes(estate);  
            EstatePersistenceDto estatePersistenceDto = estatePersistenceDtoFactory.newInstanceEstate(attributes);
            
            xmlFileEditor.replaceElement(estateDocument, PATH_TO_ESTATE, address, ADDRESS_KEY , estatePersistenceDto);
               
            if(description != null){
               HashMap<String, String> descriptionAttributes = estateElementAssembler.convertDescriptionToAttributes(description);
               DescriptionPersistenceDto descriptionPersistenceDto = estatePersistenceDtoFactory.newInstanceDescription(descriptionAttributes);
               xmlFileEditor.addNewNestedElementToDocument2(estateDocument, descriptionPersistenceDto, address, ADDRESS_KEY, PATH_TO_ESTATE );
            }
            
             try {
                saveEstateDocument(estateDocument);
             } catch (IOException e1) {
               throw new CouldNotAccessDataException("Unable to save estate to document", e1);
            }
         } catch (DocumentException e2) {
               throw new CouldNotAccessDataException("Unable to edit description", e2);
         }
   }

   @Override
   public List<Estate> getEstateFromSeller(String sellerName)
         throws SellerNotFoundException, CouldNotAccessDataException {
      List<Estate> allEstates = getAllEstates();

      List<Estate> estatesFromSeller = new ArrayList<Estate>();
      for (Estate estate : allEstates) {
         if (estate.isFromSeller(sellerName)) {
            estatesFromSeller.add(estate);
         }
      }
      return estatesFromSeller;

   }

   @Override
   public Estate getEstateByAddress(String address) throws EstateNotFoundException, CouldNotAccessDataException {
      Estate estate = null;
      try {
         Document document = xmlFileEditor.readXMLFile(XML_DIRECTORY_PATH);

         if (!isEstatePersisted(document, address)) {
            throw new EstateNotFoundException("No estate found at this address : " + address);
         }

         EstateDto estateDto = assembleDtoFromDocumentAttributes(address, document);      
         
         estate = assembleEstate(estateDto);

      } catch (DocumentException e) {
         throw new CouldNotAccessDataException("Unable to access data", e);
      }
      return estate;
   }

   private EstateDto assembleDtoFromDocumentAttributes(String address, Document document) {
      HashMap<String, String> estateAttributes = xmlFileEditor.returnAttributesOfElementWithCorrespondingValue(document,
            PATH_TO_ADDRESS, address);
      
      HashMap<String, String> descriptionAttributes = xmlFileEditor.returnChildAttributesOfElementWithCorrespondingValue(document,
            PATH_TO_ADDRESS, address, CHILD_DESCRIPTION_KEY);

      EstateElementAssembler estateElementAssembler = estateElementAssemblerFactory.createAssembler();
      EstateDto estateDto = estateElementAssembler.convertAttributesToDto(estateAttributes);
      DescriptionDto descriptionDto = estateElementAssembler.convertDescriptionAttributesToDto(descriptionAttributes);
      
      estateDto.setDescriptionDto(descriptionDto);
      
      return estateDto;
   }
   
   private Estate assembleEstate(EstateDto estateDto) {
      EstateAssembler estateAssembler = estateAssemblerFactory.createEstateAssembler();

      return estateAssembler.assembleEstate(estateDto);

   }
}
