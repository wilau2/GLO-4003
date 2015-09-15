package ca.ulaval.glo4003.b7.housematch.estates;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.*;

import ca.ulaval.glo4003.b7.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b7.housematch.repository.RepositoryInterface;

public class EstateControllerTests {

	@Mock
	private EstateFactory estateFactory;
	
	@Mock
	private EstateDto estateDto;
	
	@Mock
	Estate estate;
	
	@Mock 
	RepositoryInterface repositoryInterface;
	
	private EstateController estateController;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		estateController = new EstateController(estateFactory, repositoryInterface);
	}

	@Test
	public void addEstateCallsTheEstateFactory() {
		//given
		
		//when
		estateController.addEstate(estateDto);
		//then
		verify(estateFactory).createEstate(estateDto);
	}
	
	@Test
	public void saveEstateCallsTheRepositoryInterfaceSaveEstateImplementationFunction(){
		//given
		given(estateFactory.createEstate(estateDto)).willReturn(estate);
		//when
		estateController.saveEstate(estate);
		//then
		verify(repositoryInterface).persistEstate(estate);
		
	}


}
