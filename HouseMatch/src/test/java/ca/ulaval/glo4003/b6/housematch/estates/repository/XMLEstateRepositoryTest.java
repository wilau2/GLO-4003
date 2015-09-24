package ca.ulaval.glo4003.b6.housematch.estates.repository;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Estate;
import ca.ulaval.glo4003.b6.housematch.persistance.RepositoryToPersistenceDtoFactory;
import ca.ulaval.glo4003.b6.housematch.persistance.XMLFileEditor;

public class XMLEstateRepositoryTest {

   private static final String VALID_TYPE = "VALID_TYPE";

   private static final String VALID_ADDRESS = "VALID_ADDRESS";

   private static final Integer VALID_PRICE = 99999;

   private String XML_FILE_PATH = "persistence/estates.xml";

   @Mock
   private RepositoryToPersistenceDtoFactory dtoFactory;

   @Mock
   private RepositoryToPersistenceEstateDto estateDto;

   @Mock
   private Estate estate;

   @Mock
   private XMLFileEditor xmlFileEditor;

   @Mock
   private Document usedDocument;

   @InjectMocks
   private XMLEstateRepository xmlEstateRepository;

   @Before
   public void setUp() throws DocumentException {
      MockitoAnnotations.initMocks(this);
      configureXmlFileEditor();
      configureFactory();
   }

   @Test
   public void givenAFirstEstateToPersistXMLFileEditorCallsReadXmlFile() throws DocumentException {
      // given

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
      verify(xmlFileEditor, times(1)).addNewElementToDocument(usedDocument, estateDto);
   }

   @Test
   public void givenAnExistingEstateInRepositoryDoNothingWhenPersisting() throws DocumentException {
      // given
      configureEstate();
      given(xmlFileEditor.elementWithCorrespondingValuesExists(usedDocument, "estates/estate", estateDto))
            .willReturn(true);
      // when
      xmlEstateRepository.addEstate(estate);
      // then
      verify(xmlFileEditor, times(0)).addNewElementToDocument(usedDocument, estateDto);
   }

   private void configureFactory() {
      given(dtoFactory.getRepositoryDto(estate)).willReturn(estateDto);
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
