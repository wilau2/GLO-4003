package ca.ulaval.glo4003.b6.housematch.persistance.XMLPersistence;

import java.util.List;
import java.util.Map.Entry;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

import ca.ulaval.glo4003.b6.housematch.persistance.RepositoryToPersistenceDto;

public class XMLElementWriter {

   public void deleteExistingElementWithCorrespondingValue(Document existingDocument, String pathToValue,
         String wantedValue) {
      List<Node> list = existingDocument.selectNodes(pathToValue);
      for (Node node : list) {
         if (node.getStringValue().equals(wantedValue)) {
            node.getParent().detach();
         }
      }
   }

   public void addNewElementToDocument(Document existingDocument, RepositoryToPersistenceDto receivedDto) {
      Element rootElement = existingDocument.getRootElement();
      Element newElement = rootElement.addElement(receivedDto.getElementName());

      for (Entry<String, String> entry : receivedDto.getAttributes().entrySet()) {
         newElement.addElement(entry.getKey()).addText(entry.getValue());
      }
   }

   public void addNewNestedElementToDocumentFromParentPath(Document existingDocument,
         RepositoryToPersistenceDto receivedDto, String wantedValue, String wantedValueName, String parentElementPath) {

      Element parentElement = getParentByValue(existingDocument, wantedValue, wantedValueName, parentElementPath);
      Element newElement = parentElement.addElement(receivedDto.getElementName());

      for (Entry<String, String> entry : receivedDto.getAttributes().entrySet()) {
         newElement.addElement(entry.getKey()).addText(entry.getValue());
      }
   }

   private Element getParentByValue(Document existingDocument, String wantedValue, String wantedValueName,
         String parentElementPath) {
      Element element = null;
      List<Node> list = existingDocument.selectNodes(parentElementPath);
      for (Node node : list) {
         Node addressNode = node.selectSingleNode(wantedValueName);
         if (addressNode.getStringValue().equals(wantedValue)) {
            element = (Element) node;
            break;
         }
      }
      return element;
   }

   public void replaceElement(Document existingDocument, String pathToValue, String matchingElement,
         String matchingElementName, RepositoryToPersistenceDto receivedDto) {
      List<Node> list = existingDocument.selectNodes(pathToValue);
      for (Node node : list) {
         Node addressNode = node.selectSingleNode(matchingElementName);
         if (addressNode.getStringValue().equals(matchingElement)) {
            node.detach();
            addNewElementToDocument(existingDocument, receivedDto);
            break;
         }
      }
   }
}
