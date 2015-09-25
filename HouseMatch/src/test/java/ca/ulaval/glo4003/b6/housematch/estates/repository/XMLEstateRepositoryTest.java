package ca.ulaval.glo4003.b6.housematch.estates.repository;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
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
import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.EstateAssembler;
import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstatePersistenceDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.factories.EstatePersistenceDtoFactory;
import ca.ulaval.glo4003.b6.housematch.estates.persistences.EstateElementAssembler;
import ca.ulaval.glo4003.b6.housematch.estates.persistences.EstateElementAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.persistance.XMLFileEditor;
import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;

public class XMLEstateRepositoryTest {

   private static final String VALID_TYPE = "VALID_TYPE";

   private static final String VALID_ADDRESS = "VALID_ADDRESS";

   private static final Integer VALID_PRICE = 99999;

   private static final String ELEMENT_NAME = "estate";

   private static final String XML_FILE_PATH = "persistence/estates.xml";

   private static final String USER_ID = "USER_ID";

   @Mock
   private Element element;

   @Mock
   private Estate estate;

   @Mock
   private XMLFileEditor xmlFileEditor;

   @Mock
   private Document usedDocument;

   @Mock
   private EstatePersistenceDto estatePersisenceDto;

   @Mock
   private EstateAssembler estateAssembler;

   @Mock
   private EstateDto estateDto;

   @Mock
   private EstateAssemblerFactory estateAssemblerFactory;

   @Mock
   private EstateElementAssembler estateElementAssembler;

   @Mock
   private EstateElementAssemblerFactory estateElementAssemblerFactory;

   @Mock
   private EstatePersistenceDtoFactory estatePersistenceDtoFactory;

   @InjectMocks
   private XMLEstateRepository xmlEstateRepository;

   @Before
   public void setUp() throws DocumentException {
      MockitoAnnotations.initMocks(this);

      configureXmlFileEditor();

      configureAssemblerBehavior();

      when(estatePersistenceDtoFactory.newInstance(any(HashMap.class))).thenReturn(estatePersisenceDto);
   }

   private void configureAssemblerBehavior() {
      when(estateAssemblerFactory.createEstateAssembler()).thenReturn(estateAssembler);
      when(estateElementAssemblerFactory.createAssembler()).thenReturn(estateElementAssembler);

      when(estateElementAssembler.convertToDto(element)).thenReturn(estateDto);
      when(estateAssembler.assembleEstate(estateDto)).thenReturn(estate);
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
   public void whenAddingAnEstateShouldCallEstateElementAssemblerForCreatingAttributes() {
      // Given no changes

      // When
      xmlEstateRepository.addEstate(estate);

      // Then
      verify(estateElementAssembler, times(1)).convertToAttributes(estate);
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
   public void whenAddingAnEstateShouldCallNewInstanceOfEstateElementAssebler() {
      // Given no changes

      // When
      xmlEstateRepository.addEstate(estate);

      // Then
      verify(estateElementAssemblerFactory, times(1)).createAssembler();
   }

   @Test
   public void addingAnEstateWhenEstateIsUniqueShouldCreateEstateToPersistenceDto() throws DocumentException {
      // Given
      configureEstate();
      HashMap<String, String> attributes = createHashMapFromEstate(estate);
      when(estateElementAssembler.convertToAttributes(estate)).thenReturn(attributes);

      // When
      xmlEstateRepository.addEstate(estate);

      // Then
      verify(estatePersistenceDtoFactory, times(1)).newInstance(attributes);
   }

   @Test
   public void whenAddingAnEstateShouldSaveOpenedXMlFile() throws DocumentException, IOException {
      // Given
      configureEstate();

      // When
      xmlEstateRepository.addEstate(estate);

      // Then
      int expectedNumberOfCall = 1;
      verify(xmlFileEditor, times(expectedNumberOfCall)).formatAndWriteDocument(eq(usedDocument), anyString());
   }

   @Test(expected = CouldNotAccessDataException.class)
   public void addingAnEstateWhenWrittingEstateThrowIOExceptionShouldThrowExceptio()
         throws DocumentException, IOException {
      // Given
      configureEstate();
      doThrow(new IOException()).when(xmlFileEditor).formatAndWriteDocument(usedDocument, XML_FILE_PATH);

      // When
      xmlEstateRepository.addEstate(estate);

      // Then a CouldNotAccessDataException
   }

   @Test
   public void givenAnExistingEstateInRepositoryDoNothingWhenPersisting() throws DocumentException {
      // given
      configureEstate();
      HashMap<String, String> attributes = createHashMapFromEstate(estate);
      when(estateElementAssembler.convertToAttributes(estate)).thenReturn(attributes);
      given(xmlFileEditor.elementWithCorrespondingValuesExists(usedDocument, "estates/estate/address",
            attributes.get("address"))).willReturn(true);

      // when
      xmlEstateRepository.addEstate(estate);

      // then
      verify(xmlFileEditor, never()).addNewElementToDocument(usedDocument, estatePersisenceDto);
   }

   @Test
   public void gettingAllEstatesShouldReadEstateXMLFile() throws DocumentException {
      // Given

      // When
      xmlEstateRepository.getAllEstates();

      // Then
      verify(xmlFileEditor, times(1)).readXMLFile(XML_FILE_PATH);
   }

   @Test
   public void gettingAllEstatesShouldCallExtractAllElementsFromDocument() {
      // Given

      // When
      xmlEstateRepository.getAllEstates();

      // Then
      verify(xmlFileEditor, times(1)).getAllElementsFromDocument(usedDocument, ELEMENT_NAME);
   }

   @Test
   public void gettingAllEstatesReturnListOfAllEstatesDTO() {
      // Given
      configureGetAllEstate();

      // When
      List<?> returnedEstateDtoList = xmlEstateRepository.getAllEstates();

      // Then
      assertTrue(returnedEstateDtoList.get(0) instanceof Estate);
   }

   @Test
   public void whenGettingAllEstateShouldCallEstateAssemblerForAllReturnedEstateDto() {
      // Given
      int numberOfReturnedDto = 1;
      configureGetAllEstate();

      // When
      xmlEstateRepository.getAllEstates();

      // Then
      verify(estateAssemblerFactory, times(1)).createEstateAssembler();
      verify(estateAssembler, times(numberOfReturnedDto)).assembleEstate(estateDto);
   }

   @Test(expected = CouldNotAccessDataException.class)
   public void gettingAllEstateWhenPersistenceThrowExceptionShouldThrowException() throws DocumentException {
      // Given
      configureGetAllEstate();
      when(xmlFileEditor.readXMLFile(any(String.class))).thenThrow(new DocumentException());

      // When
      xmlEstateRepository.getAllEstates();

      // Then a couldNotAccessDataExceptionIsThrownl
   }

   @Test
   public void whenGettingAllEstateShouldCallEstateElementAssemblerForAllReturnedXmlElement() {
      // Given
      int numberOfReturnedDto = 1;
      configureGetAllEstate();

      // When
      xmlEstateRepository.getAllEstates();

      // Then
      verify(estateElementAssemblerFactory, times(1)).createAssembler();
      verify(estateElementAssembler, times(numberOfReturnedDto)).convertToDto(element);
   }

   @Test(expected = CouldNotAccessDataException.class)
   public void addingAnEstateWhenPersistanceThrowAnExceptionShouldThrowException() throws DocumentException {
      // Given
      configureEstate();
      when(xmlFileEditor.readXMLFile(anyString())).thenThrow(new DocumentException());

      // When
      xmlEstateRepository.addEstate(estate);

      // Then a couldNotAccessDataException is thrown
   }

   @Test
   public void shouldDoThisWhenThat() {
      // Given

      // When

      // Then
   }

   private void configureGetAllEstate() {
      configureElement();
      List<Element> estateDtoList = new ArrayList<Element>();
      estateDtoList.add(element);
      given(xmlFileEditor.getAllElementsFromDocument(usedDocument, ELEMENT_NAME)).willReturn(estateDtoList);
   }

   private void configureElement() {
      given(element.attributeValue("address")).willReturn(VALID_ADDRESS);
      given(element.attributeValue("price")).willReturn(VALID_PRICE.toString());
      given(element.attributeValue("type")).willReturn(VALID_TYPE);
      given(element.attributeValue("seller")).willReturn(USER_ID);

   }

   private void configureEstate() throws DocumentException {
      given(estate.getAddress()).willReturn(VALID_ADDRESS);
      given(estate.getPrice()).willReturn(VALID_PRICE);
      given(estate.getType()).willReturn(VALID_TYPE);
      given(estate.getSeller()).willReturn(USER_ID);
   }

   private void configureXmlFileEditor() throws DocumentException {
      given(xmlFileEditor.readXMLFile(XML_FILE_PATH)).willReturn(usedDocument);
   }

   private HashMap<String, String> createHashMapFromEstate(Estate estate) {
      HashMap<String, String> attributes = new HashMap<String, String>();

      attributes.put("type", estate.getType());
      attributes.put("address", estate.getAddress());
      attributes.put("price", estate.getPrice().toString());

      return attributes;
   }

}
