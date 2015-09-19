package ca.ulaval.glo4003.b6.housematch.estates.repository;

import java.util.Collection;

import org.dom4j.Document;
import org.dom4j.DocumentException;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Estate;
import ca.ulaval.glo4003.b6.housematch.persistance.XMLFileEditor;

public class XMLEstateRepository implements EstateRepository{

   private XMLFileEditor xmlFileEditor;
   
   private String XML_FILE_PATH = "persistence/estates.xml";
   
   @Override
   public Collection<Estate> getAllEstate() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public void addEstate(Estate estate) {
      try {
         Document estateDocument = xmlFileEditor.readXMLFile(XML_FILE_PATH);
      } catch (DocumentException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

}
