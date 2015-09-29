package ca.ulaval.glo4003.b6.housematch.persistance;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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

import ca.ulaval.glo4003.b6.housematch.user.dto.RepositoryToPersistenceDto;

// TODO Make better the comparison and modify estate persistence
// accordingly
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

   public void formatAndWriteDocument(Document existingDocument, String pathToXML) throws IOException {
      OutputFormat format = OutputFormat.createPrettyPrint();

      XMLWriter writer = new XMLWriter(new FileWriter(pathToXML), format);
      writer.write(existingDocument);
      writer.close();
   }

   public boolean elementWithCorrespondingValuesExists(Document existingDocument, String pathToValue,
         String wantedValue) {
      List<Node> list = existingDocument.selectNodes(pathToValue);
      for (Node node : list) {
         if (node.getStringValue().equals(wantedValue))
            return true;
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

      return returnMapOfAttributes(null);
   }

   public HashMap<String, String> returnMapOfAttributes(Element element) {
      HashMap<String, String> attributes = new HashMap<String, String>();

      for (Iterator i = element.elementIterator(); i.hasNext();) {
         Element attribute = (Element) i.next();
         attributes.put(attribute.getQualifiedName(), attribute.getStringValue());
      }
      return attributes;
   }

   public List<Element> getAllElementsFromDocument(Document usedDocument, String elementName) {

      List<Node> nodes = usedDocument.selectNodes(elementName);
      List<Element> elements = new ArrayList<Element>();
      for (Node node : nodes) {
         Element element = (Element) node;
         elements.add(element);
      }
      return elements;
   }
}
