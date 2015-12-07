package ca.ulaval.glo4003.b6.housematch.persistence.FilePersistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

import ca.ulaval.glo4003.b6.housematch.persistence.PersistenceDto;

class FileElementReader {

   boolean elementWithCorrespondingValueExists(Document existingDocument, String pathToValue, String wantedValue) {
      List<Node> list = existingDocument.selectNodes(pathToValue);
      for (Node node : list) {
         if (node.getStringValue().equals(wantedValue)) {
            return true;
         }
      }
      return false;
   }

   boolean elementWithCorrespondingValuesExists(Document existingDocument, String pathToValues,
         PersistenceDto receivedDto) {
      List<Node> list = existingDocument.selectNodes(pathToValues);
      Collection<String> attributesValues = receivedDto.getAttributes().values();
      for (Node node : list) {
         boolean hasAllAttributes = true;

         for (String value : attributesValues) {
            if (!node.getStringValue().contains(value)) {
               hasAllAttributes = false;
            }
         }

         if (hasAllAttributes) {
            return true;
         }
      }
      return false;
   }

   HashMap<String, String> returnAttributesOfElementWithCorrespondingValue(Document existingDocument,
         String pathToValue, String wantedValue) {

      List<Node> list = existingDocument.selectNodes(pathToValue);
      for (Node node : list) {
         if (node.getStringValue().equals(wantedValue)) {
            Element element = (Element) node;

            return returnMapOfAttributes(element.getParent());
         }
      }

      return new HashMap<String, String>();
   }

   private HashMap<String, String> returnMapOfAttributes(Element element) {
      HashMap<String, String> attributes = new HashMap<String, String>();

      for (Iterator i = element.elementIterator(); i.hasNext();) {
         Element attribute = (Element) i.next();
         attributes.put(attribute.getQualifiedName(), attribute.getStringValue());
      }
      return attributes;
   }

   List<Element> getAllElementsFromDocument(Document usedDocument, String pathToElement) {

      List<Node> nodes = usedDocument.selectNodes(pathToElement);
      List<Element> elements = new ArrayList<Element>();
      for (Node node : nodes) {
         Element element = (Element) node;
         elements.add(element);
      }
      return elements;
   }

   HashMap<String, String> returnChildAttributesOfElementWithCorrespondingValue(Document existingDocument,
         String pathToValue, String wantedValue, String childValue) {

      List<Node> list = existingDocument.selectNodes(pathToValue);
      for (Node node : list) {
         if (node.getStringValue().equals(wantedValue)) {
            Element childElement = (Element) node.getParent().selectSingleNode(childValue);
            if (childElement != null) {
               return returnMapOfAttributes(childElement);
            }
         }
      }
      return new HashMap<String, String>();
   }
}
