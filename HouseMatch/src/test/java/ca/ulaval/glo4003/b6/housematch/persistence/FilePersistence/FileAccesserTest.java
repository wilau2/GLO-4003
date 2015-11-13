package ca.ulaval.glo4003.b6.housematch.persistence.FilePersistence;

import static org.junit.Assert.assertTrue;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.persistence.PersistenceDto;

public class FileAccesserTest {

   FileAccesser accesser;

   final String pathToXmlFileStatic = "persistenceTestData/testData.xml";

   final String pathToXmlFileModified = "persistenceTestData/changingTestData.xml";

   final String pathToXmlFileDelete = "persistenceTestData/deleteElementTest.xml";

   Document document;

   @Mock
   PersistenceDto dtoContainingNewElement;

   @Mock
   PersistenceDto dtoContainingExistingElement;

   @Before
   public void setup() throws DocumentException {
      MockitoAnnotations.initMocks(this);
      accesser = new FileAccesser();
   }

   @Test
   public void shouldBeAbleToReadTheContentOfAnXmlFile() throws DocumentException {
      // Given
      document = accesser.readXMLFile(pathToXmlFileStatic);

      // When
      Element fileContent = accesser.readXMLFile(pathToXmlFileStatic).getRootElement();

      // Then
      assertTrue(fileContent.getText().contains("This is the content of the test data"));
   }
}
