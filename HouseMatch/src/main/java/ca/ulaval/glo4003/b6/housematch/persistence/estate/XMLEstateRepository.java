package ca.ulaval.glo4003.b6.housematch.persistence.estate;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import ca.ulaval.glo4003.b6.housematch.domain.estate.Description;
import ca.ulaval.glo4003.b6.housematch.domain.estate.Estate;
import ca.ulaval.glo4003.b6.housematch.domain.estate.EstateRepository;
import ca.ulaval.glo4003.b6.housematch.domain.estate.Estates;
import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.EstateNotFoundException;
import ca.ulaval.glo4003.b6.housematch.dto.DescriptionDto;
import ca.ulaval.glo4003.b6.housematch.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.EstateAssembler;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.persistence.FilePersistence.FileEditor;
import ca.ulaval.glo4003.b6.housematch.persistence.estate.converter.EstateElementConverter;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;

public class XMLEstateRepository implements EstateRepository {

   private static final String PATH_TO_ESTATE = "estates/estate";

   private static final String PATH_TO_ADDRESS = "estates/estate/address";

   private static final String ADDRESS_KEY = "address";

   private static final String CHILD_DESCRIPTION_KEY = "description";

   private static final String XML_DIRECTORY_PATH = "persistence/estates.xml";

   private FileEditor FileEditor;

   private EstateAssemblerFactory estateAssemblerFactory;

   private EstateElementConverter estateElementAssembler;

   private EstatePersistenceDtoFactory estatePersistenceDtoFactory;

   public XMLEstateRepository(EstateAssemblerFactory estateAssemblerFactory,
         EstatePersistenceDtoFactory estatePersistenceDtoFactory, EstateElementConverter estateElementAssembler,
         FileEditor FileEditor) {

      this.estateAssemblerFactory = estateAssemblerFactory;
      this.estatePersistenceDtoFactory = estatePersistenceDtoFactory;
      this.estateElementAssembler = estateElementAssembler;
      this.FileEditor = FileEditor;

   }

   @Override
   public Estates getAllEstates() throws CouldNotAccessDataException {
      List<Estate> listEstates = new ArrayList<Estate>();
      try {
         Document estateDocument = FileEditor.readXMLFile(XML_DIRECTORY_PATH);

         List<Element> elementList = FileEditor.getAllElementsFromDocument(estateDocument, PATH_TO_ESTATE);

         EstateAssembler estateAssembler = estateAssemblerFactory.createEstateAssembler();

         listEstates = getDtoListFromElements(elementList, estateAssembler, estateElementAssembler);

      } catch (DocumentException e) {
         throw new CouldNotAccessDataException("Problem when fetching all estate", e);
      } catch (ParseException e2) {
         throw new CouldNotAccessDataException("Problem when fetching all estate dates", e2);
      }
      Estates estates = new Estates(listEstates);
      return estates;
   }

   private List<Estate> getDtoListFromElements(List<Element> elementList, EstateAssembler estateAssembler,
         EstateElementConverter estateElementAssembler) throws ParseException {
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

         Document estateDocument = FileEditor.readXMLFile(XML_DIRECTORY_PATH);

         HashMap<String, String> attributes = estateElementAssembler.convertToAttributes(estate);
         if (isEstatePersisted(estateDocument, attributes.get(ADDRESS_KEY))) {
            return;
         }

         addNewEstateToDocument(estateDocument, attributes, estatePersistenceDtoFactory);
         Description description = estate.getDescription();

         if (description != null) {
            HashMap<String, String> descriptionAttributes = estateElementAssembler
                  .convertDescriptionToAttributes(description);
            DescriptionPersistenceDto descriptionPersistenceDto = estatePersistenceDtoFactory
                  .newInstanceDescription(descriptionAttributes);
            FileEditor.addNewNestedElementToDocumentFromParentPath(estateDocument, descriptionPersistenceDto,
                  attributes.get(ADDRESS_KEY), ADDRESS_KEY, PATH_TO_ESTATE);
         }
         saveEstateDocument(estateDocument);

      } catch (DocumentException e) {
         throw new CouldNotAccessDataException("Unable to add an estate", e);

      } catch (IOException e) {
         throw new CouldNotAccessDataException("Unable to save the added estate", e);

      }
   }

   private void saveEstateDocument(Document estateDocument) throws IOException {
      FileEditor.formatAndWriteDocument(estateDocument, XML_DIRECTORY_PATH);
   }

   private boolean isEstatePersisted(Document existingDocument, String address) {
      return FileEditor.elementWithCorrespondingValueExists(existingDocument, PATH_TO_ADDRESS, address);
   }

   private void addNewEstateToDocument(Document document, HashMap<String, String> attributes,
         EstatePersistenceDtoFactory estatePersistenceDtoFactory) {

      EstatePersistenceDto estatePersistenceDto = estatePersistenceDtoFactory.newInstanceEstate(attributes);

      FileEditor.addNewElementToDocument(document, estatePersistenceDto);

   }

   @Override
   public Estate getEstateByAddress(String address) throws EstateNotFoundException, CouldNotAccessDataException {
      Estate estate = null;
      try {
         Document document = FileEditor.readXMLFile(XML_DIRECTORY_PATH);

         if (!isEstatePersisted(document, address)) {
            throw new EstateNotFoundException("No estate found at this address : " + address);
         }

         EstateDto estateDto = assembleDtoFromDocumentAttributes(address, document);

         estate = assembleEstate(estateDto);

      } catch (DocumentException e) {
         throw new CouldNotAccessDataException("Unable to access data", e);
      } catch (ParseException e) {
         throw new CouldNotAccessDataException("Unable to access date data", e);
      }
      return estate;
   }

   @Override
   public void updateEstate(Estate estate) throws CouldNotAccessDataException {
      try {
         Document estateDocument = readEstateXML();
         FileEditor.deleteExistingElementWithCorrespondingValue(estateDocument, PATH_TO_ADDRESS,
               estate.getAddress().toString());
         try {
            saveEstateDocument(estateDocument);
         } catch (IOException e1) {
            throw new CouldNotAccessDataException("Unable to save estate to document", e1);
         }
         addEstate(estate);

      } catch (DocumentException exception) {
         throw new CouldNotAccessDataException("Something wrong happenned trying to access the data", exception);
      }

   }

   private EstateDto assembleDtoFromDocumentAttributes(String address, Document document) throws ParseException {
      HashMap<String, String> estateAttributes = FileEditor.returnAttributesOfElementWithCorrespondingValue(document,
            PATH_TO_ADDRESS, address);

      HashMap<String, String> descriptionAttributes = FileEditor.returnChildAttributesOfElementWithCorrespondingValue(
            document, PATH_TO_ADDRESS, address, CHILD_DESCRIPTION_KEY);

      EstateDto estateDto = estateElementAssembler.convertAttributesToDto(estateAttributes);
      DescriptionDto descriptionDto = estateElementAssembler.convertDescriptionAttributesToDto(descriptionAttributes);

      estateDto.setDescriptionDto(descriptionDto);

      return estateDto;
   }

   private Estate assembleEstate(EstateDto estateDto) {
      EstateAssembler estateAssembler = estateAssemblerFactory.createEstateAssembler();

      return estateAssembler.assembleEstate(estateDto);

   }

   private Document readEstateXML() throws DocumentException {
      return FileEditor.readXMLFile(XML_DIRECTORY_PATH);
   }

   @Override
   public void updateEstateModifiedDate(String address) throws CouldNotAccessDataException, EstateNotFoundException {
      Estate estate = getEstateByAddress(address);
      estate.udateModifiedDate();
      updateEstate(estate);
   }

}
