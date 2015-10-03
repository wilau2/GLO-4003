package ca.ulaval.glo4003.b6.housematch.persistance;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XMLFileEditor {

   public Document readXMLFile(String pathToXMLFile) throws DocumentException {
      File xml = new File(pathToXMLFile);

      SAXReader reader = new SAXReader();

      return reader.read(xml);
   }

   public void addNewElementToDocument(Document existingDocument, RepositoryToPersistenceDto receivedDto) {
      Element rootElement = existingDocument.getRootElement();
      Element newElement = rootElement.addElement(receivedDto.getElementName());

      for (Entry<String, String> entry : receivedDto.getAttributes().entrySet()) {
         newElement.addElement(entry.getKey()).addText(entry.getValue());
      }
   }

   public void addNewNestedElementToDocument(Document existingDocument, String parentElementName,
         RepositoryToPersistenceDto receivedDto) {
      Element rootElement = existingDocument.getRootElement();
      Element parentElement = rootElement.element(parentElementName);
      
      Element newElement = parentElement.addElement(receivedDto.getElementName());

      for (Entry<String, String> entry : receivedDto.getAttributes().entrySet()) {
         newElement.addElement(entry.getKey()).addText(entry.getValue());
      }
   }
   
   public void addNewNestedElementToDocument2(Document existingDocument, RepositoryToPersistenceDto receivedDto, 
                                             String wantedValue, String wantedValueName, String parentElementPath) {
      
      Element parentElement = getParentByValue(wantedValue, wantedValueName, existingDocument, parentElementPath);
      Element newElement = parentElement.addElement(receivedDto.getElementName());

      for (Entry<String, String> entry : receivedDto.getAttributes().entrySet()) {
         newElement.addElement(entry.getKey()).addText(entry.getValue());
      }
   }
   

   private Element getParentByValue(String wantedValue, String wantedValueName, Document existingDocument, String parentElementPath) {
      Element element = null;
      List<Node> list = existingDocument.selectNodes(parentElementPath);
      for (Node node : list) {
         Node addressNode = node.selectSingleNode(wantedValueName);
         if (addressNode.getStringValue().equals(wantedValue)){
            element = (Element)node;
            break;
         }
      }
      return element;
   }

   public void formatAndWriteDocument(Document existingDocument, String pathToXML) throws IOException {
      OutputFormat format = OutputFormat.createPrettyPrint();

      XMLWriter writer = new XMLWriter(new FileWriter(pathToXML), format);
      writer.write(existingDocument);
      writer.close();
   }

   public boolean elementWithCorrespondingValueExists(Document existingDocument, String pathToValue,
         String wantedValue) {
      List<Node> list = existingDocument.selectNodes(pathToValue);
      for (Node node : list) {
         if (node.getStringValue().equals(wantedValue))
            return true;
      }
      return false;
   }

   public boolean elementWithCorrespondingValuesExists(Document existingDocument, String pathToValues,
         RepositoryToPersistenceDto receivedDto) {
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

   public HashMap<String, String> returnAttributesOfElementWithCorrespondingValue(Document existingDocument,
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

   public List<Element> getAllElementsFromDocument(Document usedDocument, String pathToElement) {

      List<Node> nodes = usedDocument.selectNodes(pathToElement);
      List<Element> elements = new ArrayList<Element>();
      for (Node node : nodes) {
         Element element = (Element) node;
         elements.add(element);
      }
      return elements;
   }
   
   public void replaceElement(Document existingDocument, String pathToValue, String matchingElement, String matchingElementName, RepositoryToPersistenceDto receivedDto){
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
   
   public HashMap<String, String> returnChildAttributesOfElementWithCorrespondingValue(Document existingDocument,
         String pathToValue, String wantedValue, String childValue) {

      List<Node> list = existingDocument.selectNodes(pathToValue);
      for (Node node : list) {
         if (node.getStringValue().equals(wantedValue)) {
            return returnMapOfAttributes((Element)node.selectObject(childValue));
         }
      }

      return new HashMap<String, String>();
   }
}
