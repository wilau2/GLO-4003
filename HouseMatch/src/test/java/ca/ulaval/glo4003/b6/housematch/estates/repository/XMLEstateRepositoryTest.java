package ca.ulaval.glo4003.b6.housematch.estates.repository;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Estate;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.persistance.XMLFileEditor;
import ca.ulaval.glo4003.b6.housematch.user.dto.RepositoryToPersistenceDto;

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

   @Mock
   private RepositoryToPersistenceDto estatePersisenceDto;

   @InjectMocks
   private XMLEstateRepository xmlEstateRepository;

   @Before
   public void setUp() throws DocumentException {
      MockitoAnnotations.initMocks(this);
      configureXmlFileEditor();
   }

   @Test
   public void givenAFirstEstateToPersistXMLFileEditorCallsReadXmlFile() throws DocumentException {
      // given no changes

      // when
      xmlEstateRepository.addEstate(estate);

      // then
      verify(xmlFileEditor, times(1)).readXMLFile(XML_FILE_PATH);
   }

   @Test
   public void givenAFirstEstateToPersistXMLFileEditorCallsAddNewElementToDocument() throws DocumentException {
      // given
      configureEstate();

      // when
      xmlEstateRepository.addEstate(estate);

      // then
      verify(xmlFileEditor, times(1)).addNewElementToDocument(usedDocument, estatePersisenceDto);
   }

   @Test
   public void givenAnExistingEstateInRepositoryDoNothingWhenPersisting() throws DocumentException {
      // given
      configureEstate();
      HashMap<String, String> attributes = xmlEstateRepository.createHashMapFromEstate(estate);
      given(xmlFileEditor.elementWithCorrespondingValuesExists(usedDocument, "estates/estate/price",
            attributes.get("price"))).willReturn(true);

      // when
      xmlEstateRepository.addEstate(estate);

      // then
      verify(xmlFileEditor, times(0)).addNewElementToDocument(usedDocument, estatePersisenceDto);
   }

   @Test
   public void gettingAllEstatesShouldReadEstateXMLFile() throws DocumentException {
      // Given

      // When
      xmlEstateRepository.getAllEstatesDto();

      // Then
      verify(xmlFileEditor, times(1)).readXMLFile(XML_FILE_PATH);
   }

   @Test
   public void gettingAllEstatesShouldCallExtractAllElementsFromDocument() {
      // Given

      // When
      xmlEstateRepository.getAllEstatesDto();

      // Then
      verify(xmlFileEditor, times(1)).getAllElementsFromDocument(usedDocument, ELEMENT_NAME);
   }

   @Test
   public void gettingAllEstatesReturnListOfAllEstatesDTO() {
      // Given
      configureElement();
      List<Element> estateDtoList = new ArrayList<Element>();
      estateDtoList.add(element);
      given(xmlFileEditor.getAllElementsFromDocument(usedDocument, ELEMENT_NAME)).willReturn(estateDtoList);

      // When
      List<?> returnedEstateDtoList = xmlEstateRepository.getAllEstatesDto();

      // Then
      assertTrue(returnedEstateDtoList.get(0) instanceof EstateDto);
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

   private void configureXmlFileEditor() throws DocumentException {
      given(xmlFileEditor.readXMLFile(XML_FILE_PATH)).willReturn(usedDocument);
   }

}
