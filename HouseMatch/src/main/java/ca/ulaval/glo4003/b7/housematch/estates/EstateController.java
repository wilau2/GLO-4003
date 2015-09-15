package ca.ulaval.glo4003.b7.housematch.estates;

import ca.ulaval.glo4003.b7.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b7.housematch.repository.RepositoryInterface;

public class EstateController {
	
	private EstateFactory estateFactory;
	private RepositoryInterface repositoryInterface;
	
	public EstateController(EstateFactory estateFactory, RepositoryInterface repositoryInterface) {
		this.estateFactory = estateFactory;
		this.repositoryInterface = repositoryInterface;
	}
	
	public EstateController() {
	}

	public void addEstate(EstateDto estateDto) {
		estateFactory.createEstate(estateDto);
	}

	public void saveEstate(Estate estate) {
		repositoryInterface.persistEstate(estate);
		
	}

}
