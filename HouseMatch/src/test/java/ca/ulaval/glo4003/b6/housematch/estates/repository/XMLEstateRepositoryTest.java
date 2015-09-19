package ca.ulaval.glo4003.b6.housematch.estates.repository;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import org.dom4j.DocumentException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Estate;
import ca.ulaval.glo4003.b6.housematch.persistance.XMLFileEditor;

public class XMLEstateRepositoryTest {

      private String XML_FILE_PATH = "persistence/estates.xml";
      
      @Mock
      private Estate estate;
      
      @Mock
      private XMLFileEditor xmlFileEditor;
 
      @InjectMocks
      private XMLEstateRepository xmlEstateRepository;
      
      @Before
      public void setUp(){
         MockitoAnnotations.initMocks(this);
      }
      @Test
      public void givenAFirstEstateToPersistXMLFileEditorCallsReadXmlFile() throws DocumentException {
         //given
         
         //when
         xmlEstateRepository.addEstate(estate);
         //then
         verify(xmlFileEditor, times(1)).readXMLFile(XML_FILE_PATH);
      }
      
}
