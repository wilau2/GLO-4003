package ca.ulaval.glo4003.b6.housematch.persistance.XMLPersistence;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import ca.ulaval.glo4003.b6.housematch.persistance.PersistenceDto;

public class XMLFileEditor {

   XMLFileAccesser fileAccesser;

   XMLElementReader elementReader;

   XMLElementWriter elementWriter;

   @Inject
   public XMLFileEditor(XMLFileAccesser xmlFileAccesser, XMLElementReader xmlElementReader,
         XMLElementWriter xmlElementWriter) {
      this.fileAccesser = xmlFileAccesser;
      this.elementReader = xmlElementReader;
      this.elementWriter = xmlElementWriter;
   }

   public XMLFileEditor() {
      fileAccesser = new XMLFileAccesser();
      elementReader = new XMLElementReader();
      elementWriter = new XMLElementWriter();
   }

   public Document readXMLFile(String pathToXMLFile) throws DocumentException {
      return fileAccesser.readXMLFile(pathToXMLFile);
   }

   public void formatAndWriteDocument(Document existingDocument, String pathToXML) throws IOException {
      fileAccesser.formatAndWriteDocument(existingDocument, pathToXML);
   }

   public boolean elementWithCorrespondingValueExists(Document existingDocument, String pathToValue,
         String wantedValue) {
      return elementReader.elementWithCorrespondingValueExists(existingDocument, pathToValue, wantedValue);
   }

   public boolean elementWithCorrespondingValuesExists(Document existingDocument, String pathToValues,
         PersistenceDto receivedDto) {
      return elementReader.elementWithCorrespondingValuesExists(existingDocument, pathToValues, receivedDto);
   }

   public HashMap<String, String> returnAttributesOfElementWithCorrespondingValue(Document existingDocument,
         String pathToValue, String wantedValue) {
      return elementReader.returnAttributesOfElementWithCorrespondingValue(existingDocument, pathToValue, wantedValue);
   }

   public HashMap<String, String> returnChildAttributesOfElementWithCorrespondingValue(Document existingDocument,
         String pathToValue, String wantedValue, String childValue) {
      return elementReader.returnChildAttributesOfElementWithCorrespondingValue(existingDocument, pathToValue,
            wantedValue, childValue);
   }

   public List<Element> getAllElementsFromDocument(Document usedDocument, String pathToElement) {
      return elementReader.getAllElementsFromDocument(usedDocument, pathToElement);
   }

   public void deleteExistingElementWithCorrespondingValue(Document existingDocument, String pathToValue,
         String wantedValue) {
      elementWriter.deleteExistingElementWithCorrespondingValue(existingDocument, pathToValue, wantedValue);
   }

   public void addNewElementToDocument(Document existingDocument, PersistenceDto receivedDto) {
      elementWriter.addNewElementToDocument(existingDocument, receivedDto);
   }

   public void addNewNestedElementToDocumentFromParentPath(Document existingDocument,
         PersistenceDto receivedDto, String wantedValue, String wantedValueName, String parentElementPath) {
      elementWriter.addNewNestedElementToDocumentFromParentPath(existingDocument, receivedDto, wantedValue,
            wantedValueName, parentElementPath);
   }

   public void replaceElement(Document existingDocument, String pathToValue, String matchingElement,
         String matchingElementName, PersistenceDto receivedDto) {
      elementWriter.replaceElement(existingDocument, pathToValue, matchingElement, matchingElementName, receivedDto);
   }
}
