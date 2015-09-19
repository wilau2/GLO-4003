package ca.ulaval.glo4003.b6.housematch.estates.repository;

import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.AssertTrue;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;

import org.dom4j.Attribute;
import org.dom4j.Branch;
import org.dom4j.CDATA;
import org.dom4j.Comment;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Entity;
import org.dom4j.InvalidXPathException;
import org.dom4j.Namespace;
import org.dom4j.Node;
import org.dom4j.ProcessingInstruction;
import org.dom4j.QName;
import org.dom4j.Text;
import org.dom4j.Visitor;
import org.dom4j.XPath;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Estate;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.persistance.XMLFileEditor;

public class XMLEstateRepositoryTest {

      private static final String VALID_TYPE = "VALID_TYPE";
      private static final String VALID_ADDRESS = "VALID_ADDRESS";
      private static final Integer VALID_PRICE = 99999;
     
      private static final String ELEMENT_NAME = "estate";

      private String XML_FILE_PATH = "persistence/estates.xml";
      
      @Mock
      private Element element;
      
      @Mock
      private Estate estate;
      
      @Mock
      private XMLFileEditor xmlFileEditor;
      
      @Mock
      private Document usedDocument;
 
      @InjectMocks
      private XMLEstateRepository xmlEstateRepository;
      
      
      @Before
      public void setUp() throws DocumentException{
         MockitoAnnotations.initMocks(this);
         configureXmlFileEditor();
      }
      
      @Test
      public void givenAFirstEstateToPersistXMLFileEditorCallsReadXmlFile() throws DocumentException {
         //given
         
         //when
         xmlEstateRepository.addEstate(estate);
         //then
         verify(xmlFileEditor, times(1)).readXMLFile(XML_FILE_PATH);
      }
      
      @Test
      public void givenAFirstEstateToPersistXMLFileEditorCallsAddNewElementToDocument() throws DocumentException {
         //given
         configureEstate();
         HashMap<String, String> attributes = xmlEstateRepository.createHashMapFromEstate(estate);
         //when
         xmlEstateRepository.addEstate(estate);
         //then
         verify(xmlFileEditor, times(1)).addNewElementToDocument(usedDocument, "estate", attributes);
      }
      
      @Test
      public void givenAnExistingEstateInRepositoryDoNothingWhenPersisting() throws DocumentException {
         //given
         configureEstate();
         HashMap<String, String> attributes = xmlEstateRepository.createHashMapFromEstate(estate);
         given(xmlFileEditor.elementWithCorrespondingValuesExists(usedDocument, "estates/estate/price", attributes.get("price"))).willReturn(true);
         //when
         xmlEstateRepository.addEstate(estate);
         //then
         verify(xmlFileEditor, times(0)).addNewElementToDocument(usedDocument, "estate", attributes);
      }
      
      @Test
      public void gettingAllEstatesShouldReadEstateXMLFile() throws DocumentException {
         //Given

         //When
         xmlEstateRepository.getAllEstatesDto();
         //Then
         verify(xmlFileEditor, times(1)).readXMLFile(XML_FILE_PATH);
      }
      
      @Test
      public void gettingAllEstatesShouldCallExtractAllElementsFromDocument() {
         //Given

         //When
         xmlEstateRepository.getAllEstatesDto();
         //Then
         verify(xmlFileEditor, times(1)).getAllElementsFromDocument(usedDocument, ELEMENT_NAME);
      }
      
      @Test
      public void gettingAllEstatesReturnListOfAllEstatesDTO(){
         //Given
         configureElement();
         Collection<Element> dumbEstateDtoList = new ArrayList<>();
         dumbEstateDtoList.add(element);
         given(xmlFileEditor.getAllElementsFromDocument(usedDocument, ELEMENT_NAME)).willReturn(dumbEstateDtoList);
         //When
         Collection<?> estateDtoList = xmlEstateRepository.getAllEstatesDto();
         //Then
         assertTrue(estateDtoList.iterator().next().getClass().equals(EstateDto.class));
      }
      

      private void configureElement() {
         given(element.attributeValue("address")).willReturn(VALID_ADDRESS);
         given(element.attributeValue("price")).willReturn(VALID_PRICE.toString());
         given(element.attributeValue("type")).willReturn(VALID_TYPE);
         
      }

      private void configureEstate() throws DocumentException {
         given(estate.getAddress()).willReturn(VALID_ADDRESS);
         given(estate.getPrice()).willReturn(VALID_PRICE);
         given(estate.getType()).willReturn(VALID_TYPE);
      }
      
      private void configureXmlFileEditor() throws DocumentException{
         given(xmlFileEditor.readXMLFile(XML_FILE_PATH)).willReturn(usedDocument);
      }
      
}
