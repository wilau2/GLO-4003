package ca.ulaval.glo4003.b6.housematch.estates.repository;

import static org.junit.Assert.assertEquals;
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

import ca.ulaval.glo4003.b6.housematch.estates.domain.Address;
import ca.ulaval.glo4003.b6.housematch.estates.domain.Description;
import ca.ulaval.glo4003.b6.housematch.estates.domain.Estate;
import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.AddressAssembler;
import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.EstateAssembler;
import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.estates.dto.DescriptionPersistenceDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstatePersistenceDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.factories.EstatePersistenceDtoFactory;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.EstateNotFoundException;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.SellerNotFoundException;
import ca.ulaval.glo4003.b6.housematch.estates.persistences.assemblers.EstateElementAssembler;
import ca.ulaval.glo4003.b6.housematch.estates.persistences.assemblers.EstateElementAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.persistance.RepositoryToPersistenceDto;
import ca.ulaval.glo4003.b6.housematch.persistance.XMLFileEditor;
import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;

public class XMLEstateRepositoryTest {

   private static final String PATH_TO_ADDRESS = "estates/estate/address";

   private static final String VALID_TYPE = "VALID_TYPE";

   private static final Address VALID_ADDRESS = new Address(1, 1, "STREET", "POSTAL_CODE", "STATE", "COUNTRY");

   private static final Integer VALID_PRICE = 99999;

   private static final String ELEMENT_NAME = "estates/estate";

   private static final String XML_FILE_PATH = "persistence/estates.xml";

   private static final String USER_ID = "USER_ID";

   private static final String SELLER_NAME = "SELLERs";

   private static final String ESTATE = "estates";

   private static final String ADDRESS_KEY = "address";
   

   @Mock
   private Element element;

   @Mock
   private Estate estate;

   @Mock
   private XMLFileEditor xmlFileEditor;

   @Mock
   private Document usedDocument;

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

   @Mock
   private HashMap<String, String> attributes;
   
   @Mock
   private HashMap<String, String> descriptionAttributes;

   @Mock
   private EstatePersistenceDto estatePersistenceDto;

   @Mock
   private AddressAssembler addressAssembler;
   
   @Mock
   private DescriptionPersistenceDto descriptionPersistanceDto;
   
   @Mock
   private Description description;
   

   private List<Element> estateElementList;


   @InjectMocks
   private XMLEstateRepository xmlEstateRepository;

   

   @Before
   public void setUp() throws DocumentException {
      MockitoAnnotations.initMocks(this);

      configureXmlFileEditor();

      configureAssemblerBehavior();

      when(estatePersistenceDtoFactory.newInstanceEstate(any(HashMap.class))).thenReturn(estatePersistenceDto);
   }

   private void configureAssemblerBehavior() {
      when(estateAssemblerFactory.createEstateAssembler()).thenReturn(estateAssembler);
      when(estateElementAssemblerFactory.createAssembler()).thenReturn(estateElementAssembler);

      when(estateAssembler.assembleEstate(estateDto)).thenReturn(estate);

      when(estateElementAssembler.convertToDto(element)).thenReturn(estateDto);
      when(estateElementAssembler.convertAttributesToDto(attributes)).thenReturn(estateDto);
   }

   @Test
   public void givenAFirstEstateToPersistXMLFileEditorCallsReadXmlFile()
         throws DocumentException, CouldNotAccessDataException {
      // given no changes

      // when
      xmlEstateRepository.addEstate(estate);

      // then
      verify(xmlFileEditor, times(1)).readXMLFile(XML_FILE_PATH);
   }

   @Test
   public void whenAddingAnEstateShouldCallEstateElementAssemblerForCreatingAttributes()
         throws CouldNotAccessDataException {
      // Given no changes

      // When
      xmlEstateRepository.addEstate(estate);

      // Then
      verify(estateElementAssembler, times(1)).convertToAttributes(estate);
   }

   @Test
   public void givenAFirstEstateToPersistXMLFileEditorCallsAddNewElementToDocument()
         throws DocumentException, CouldNotAccessDataException {
      // given
      configureEstate();

      // when
      xmlEstateRepository.addEstate(estate);

      // then
      verify(xmlFileEditor, times(1)).addNewElementToDocument(usedDocument, estatePersistenceDto);
   }

   @Test
   public void whenAddingAnEstateShouldCallNewInstanceOfEstateElementAssebler() throws CouldNotAccessDataException {
      // Given no changes

      // When
      xmlEstateRepository.addEstate(estate);

      // Then
      verify(estateElementAssemblerFactory, times(1)).createAssembler();
   }

   @Test
   public void addingAnEstateWhenEstateIsUniqueShouldCreateEstateToPersistenceDto()
         throws DocumentException, CouldNotAccessDataException {
      // Given
      configureEstate();
      HashMap<String, String> attributes = createHashMapFromEstate(estate);
      when(estateElementAssembler.convertToAttributes(estate)).thenReturn(attributes);

      // When
      xmlEstateRepository.addEstate(estate);

      // Then
      verify(estatePersistenceDtoFactory, times(1)).newInstanceEstate(attributes);
   }

   @Test
   public void whenAddingAnEstateShouldSaveOpenedXMlFile()
         throws DocumentException, IOException, CouldNotAccessDataException {
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
         throws DocumentException, IOException, CouldNotAccessDataException {
      // Given
      configureEstate();
      doThrow(new IOException()).when(xmlFileEditor).formatAndWriteDocument(usedDocument, XML_FILE_PATH);

      // When
      xmlEstateRepository.addEstate(estate);

      // Then a CouldNotAccessDataException
   }

   @Test
   public void givenAnExistingEstateInRepositoryDoNothingWhenPersisting()
         throws DocumentException, CouldNotAccessDataException {
      // given
      configureEstate();
      HashMap<String, String> attributes = createHashMapFromEstate(estate);
      when(estateElementAssembler.convertToAttributes(estate)).thenReturn(attributes);
      given(xmlFileEditor.elementWithCorrespondingValueExists(usedDocument, PATH_TO_ADDRESS, attributes.get("address")))
            .willReturn(true);

      // when
      xmlEstateRepository.addEstate(estate);

      // then
      verify(xmlFileEditor, never()).addNewElementToDocument(usedDocument, estatePersistenceDto);
   }

   @Test
   public void gettingAllEstatesShouldReadEstateXMLFile() throws DocumentException, CouldNotAccessDataException {
      // Given

      // When
      xmlEstateRepository.getAllEstates();

      // Then
      verify(xmlFileEditor, times(1)).readXMLFile(XML_FILE_PATH);
   }

   @Test
   public void gettingAllEstatesShouldCallExtractAllElementsFromDocument() throws CouldNotAccessDataException {
      // Given

      // When
      xmlEstateRepository.getAllEstates();

      // Then
      verify(xmlFileEditor, times(1)).getAllElementsFromDocument(usedDocument, ELEMENT_NAME);
   }

   @Test
   public void gettingAllEstatesReturnListOfAllEstatesDTO() throws CouldNotAccessDataException {
      // Given
      configureGetAllEstate();

      // When
      List<?> returnedEstateDtoList = xmlEstateRepository.getAllEstates();

      // Then
      assertTrue(returnedEstateDtoList.get(0) instanceof Estate);
   }

   @Test
   public void whenGettingAllEstateShouldCallEstateAssemblerForAllReturnedEstateDto()
         throws CouldNotAccessDataException {
      // Given
      configureGetAllEstate();
      int numberOfReturnedDto = 1;

      // When
      xmlEstateRepository.getAllEstates();

      // Then
      verify(estateAssemblerFactory, times(1)).createEstateAssembler();
      verify(estateAssembler, times(numberOfReturnedDto)).assembleEstate(estateDto);
   }

   @Test(expected = CouldNotAccessDataException.class)
   public void gettingAllEstateWhenPersistenceThrowExceptionShouldThrowException()
         throws DocumentException, CouldNotAccessDataException {
      // Given
      configureGetAllEstate();
      when(xmlFileEditor.readXMLFile(any(String.class))).thenThrow(new DocumentException());

      // When
      xmlEstateRepository.getAllEstates();

      // Then a couldNotAccessDataExceptionIsThrownl
   }

   @Test
   public void whenGettingAllEstateShouldCallEstateElementAssemblerForAllReturnedXmlElement()
         throws CouldNotAccessDataException {
      // Given
      configureGetAllEstate();
      int numberOfReturnedDto = 1;

      // When
      xmlEstateRepository.getAllEstates();

      // Then
      verify(estateElementAssemblerFactory, times(1)).createAssembler();
      verify(estateElementAssembler, times(numberOfReturnedDto)).convertToDto(element);
   }

   @Test(expected = CouldNotAccessDataException.class)
   public void addingAnEstateWhenPersistanceThrowAnExceptionShouldThrowException()
         throws DocumentException, CouldNotAccessDataException {
      // Given
      configureEstate();
      when(xmlFileEditor.readXMLFile(anyString())).thenThrow(new DocumentException());

      // When
      xmlEstateRepository.addEstate(estate);

      // Then a couldNotAccessDataException is thrown
   }

   @Test
   public void editingDescriptonShouldAskXmlForDocument() throws DocumentException, CouldNotAccessDataException {
      // given
      configureFetchingEstateByAddress();

      // when
      xmlEstateRepository.editDescription(VALID_ADDRESS.toString(), description);
      
      // then
      verify(xmlFileEditor, times(1)).readXMLFile(XML_FILE_PATH);
   }
   
   @Test
   public void editingDescriptionShouldCallReplaceEstateFromXmlFileEditor() throws DocumentException, CouldNotAccessDataException {
      // given
      configureFetchingEstateByAddress();

      // when
      xmlEstateRepository.editDescription(VALID_ADDRESS.toString(), description);

      // then
      verify(xmlFileEditor, times(1)).replaceElement(usedDocument, ELEMENT_NAME, VALID_ADDRESS.toString(),"address", estatePersistenceDto);
   }
   
   @Test
   public void editDescriptionWithNonNullDescriptionEstateShouldAddNestedElement() throws CouldNotAccessDataException, EstateNotFoundException, DocumentException {
      //given
      configureEstateWithCompleteDescription();
      when(estateElementAssembler.convertToAttributes(estate)).thenReturn(attributes);
      when(estateElementAssembler.convertDescriptionToAttributes(description)).thenReturn(descriptionAttributes);
      when(estatePersistenceDtoFactory.newInstanceDescription(descriptionAttributes)).thenReturn(descriptionPersistanceDto);
      configureFetchingEstateByAddress();
      
      //when
      xmlEstateRepository.editDescription(VALID_ADDRESS.toString(), description);
      
      //then
      verify(xmlFileEditor, times(1)).addNewNestedElementToDocument2(usedDocument, descriptionPersistanceDto, VALID_ADDRESS.toString(), "address", ELEMENT_NAME);
   }

   @Test
   public void gettingEstatesBySellerNameWhenSellerDoNotExistShouldNotThrowException()
         throws SellerNotFoundException, CouldNotAccessDataException {
      // Given
      configureGetEstatesFromSeller();
      when(estate.isFromSeller(SELLER_NAME)).thenReturn(false);

      // When
      xmlEstateRepository.getEstateFromSeller(SELLER_NAME);

      // Then no exception is thrown
   }

   @Test
   public void gettingEstatesBySellerNameWhenSellerExistShouldReturnListOfSellersEstates()
         throws SellerNotFoundException, CouldNotAccessDataException {
      // Given
      configureGetEstatesFromSeller();
      int expectedReturnedEstatesNumber = estateElementList.size();

      // When
      List<Estate> estateFromSeller = xmlEstateRepository.getEstateFromSeller(SELLER_NAME);

      // Then
      assertEquals(expectedReturnedEstatesNumber, estateFromSeller.size());

   }

   @Test
   public void gettingEstateFromSellerWhenEstateIsFromSellerShouldAddEstateToList()
         throws SellerNotFoundException, CouldNotAccessDataException {
      // Given
      configureGetEstatesFromSeller();

      // When
      xmlEstateRepository.getEstateFromSeller(SELLER_NAME);

      // Then
      verify(estate, times(1)).isFromSeller(SELLER_NAME);
   }

   @Test
   public void fetchingEstateByAddressWhenOneEstateHasTheWantedAddressShouldReturnEstate()
         throws DocumentException, EstateNotFoundException, CouldNotAccessDataException {
      // Given
      configureFetchingEstateByAddress();

      // When
      Estate estateByAddress = xmlEstateRepository.getEstateByAddress(VALID_ADDRESS.toString());

      // Then
      assertEquals(estateByAddress, estate);
   }

   @Test
   public void fetchingEstateByAddressWhenEstateIsFoundShouldCallHasCorrespondingElementMethodFromXmlFileEditor()
         throws DocumentException, EstateNotFoundException, CouldNotAccessDataException {
      // Given
      configureFetchingEstateByAddress();

      // When
      xmlEstateRepository.getEstateByAddress(VALID_ADDRESS.toString());

      // Then
      verify(xmlFileEditor, times(1)).readXMLFile(XML_FILE_PATH);
      verify(xmlFileEditor, times(1)).elementWithCorrespondingValueExists(usedDocument, PATH_TO_ADDRESS,
            VALID_ADDRESS.toString());
      verify(xmlFileEditor, times(1)).returnAttributesOfElementWithCorrespondingValue(usedDocument, PATH_TO_ADDRESS,
            VALID_ADDRESS.toString());
   }

   @Test
   public void fetchingEstateByAddressWhenEstateIsFoundShouldConvertAttributesToEstateDto()
         throws DocumentException, EstateNotFoundException, CouldNotAccessDataException {
      // Given
      configureFetchingEstateByAddress();

      // When
      xmlEstateRepository.getEstateByAddress(VALID_ADDRESS.toString());

      // Then
      verify(estateElementAssemblerFactory, times(1)).createAssembler();
      verify(estateElementAssembler, times(1)).convertAttributesToDto(attributes);
   }

   @Test
   public void fetchingEstateByAddressWhenEstateIsFoundShouldConvertEstateDtoToEstate()
         throws DocumentException, EstateNotFoundException, CouldNotAccessDataException {
      // Given
      configureFetchingEstateByAddress();

      // When
      xmlEstateRepository.getEstateByAddress(VALID_ADDRESS.toString());

      // Then
      verify(estateAssemblerFactory, times(1)).createEstateAssembler();
      verify(estateAssembler, times(1)).assembleEstate(estateDto);
   }

   @Test(expected = EstateNotFoundException.class)
   public void fetchingEstateByAddressWhenNoEstateFoundShouldThrowAnException()
         throws DocumentException, EstateNotFoundException, CouldNotAccessDataException {
      // Given
      configureFetchingEstateByAddress();
      when(xmlFileEditor.elementWithCorrespondingValueExists(usedDocument, PATH_TO_ADDRESS, VALID_ADDRESS.toString()))
            .thenReturn(false);

      // When
      xmlEstateRepository.getEstateByAddress(VALID_ADDRESS.toString());

      // Then an EstateNotFoundException is thrown
   }

   private void configureFetchingEstateByAddress() throws DocumentException {
      configureXmlFileEditor();

      when(xmlFileEditor.elementWithCorrespondingValueExists(usedDocument, PATH_TO_ADDRESS, VALID_ADDRESS.toString()))
            .thenReturn(true);
      when(xmlFileEditor.returnAttributesOfElementWithCorrespondingValue(usedDocument, PATH_TO_ADDRESS,
            VALID_ADDRESS.toString())).thenReturn(attributes);

   }

   private void configureGetEstatesFromSeller() {
      configureGetAllEstate();

      when(estate.isFromSeller(SELLER_NAME)).thenReturn(true);
   }

   private void configureGetAllEstate() {
      configureElement();
      estateElementList = new ArrayList<Element>();
      estateElementList.add(element);
      given(xmlFileEditor.getAllElementsFromDocument(usedDocument, ELEMENT_NAME)).willReturn(estateElementList);
   }

   private void configureElement() {
      given(element.attributeValue("address")).willReturn(VALID_ADDRESS.toString());
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
      attributes.put("address", estate.getAddress().toString());
      attributes.put("price", estate.getPrice().toString());

      return attributes;
   }
   
   private void configureEstateWithCompleteDescription() {
      when(estate.getDescription()).thenReturn(description);
      when(description.getMunicipalValuation()).thenReturn(200);
      when(description.getDimensionsBuilding()).thenReturn("100x20");
      when(description.getLivingSpaceAreaSquareMeter()).thenReturn(200);
      when(description.getNumberOfBathrooms()).thenReturn(200);
      when(description.getNumberOfBedRooms()).thenReturn(200);
      when(description.getNumberOfLevel()).thenReturn(200);
      when(description.getNumberOfRooms()).thenReturn(200);
      when(description.getYearsOfConstruction()).thenReturn(200);    
   }

}
