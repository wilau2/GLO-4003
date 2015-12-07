package ca.ulaval.glo4003.b6.housematch.persistence.FilePersistence;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

class FileAccesser {

   Document readXMLFile(String pathToXMLFile) throws DocumentException {
      File xml = new File(pathToXMLFile);

      SAXReader reader = new SAXReader();

      return reader.read(xml);
   }

   void formatAndWriteDocument(Document existingDocument, String pathToXML) throws IOException {
      OutputFormat format = OutputFormat.createPrettyPrint();

      XMLWriter writer = new XMLWriter(new FileWriter(pathToXML), format);
      writer.write(existingDocument);
      writer.close();
   }

}
