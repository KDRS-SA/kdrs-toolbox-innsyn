package no.arkivlab.innsyn.operations.iface;

import no.arkivlab.innsyn.models.n5.FondsCreator;

public interface IFondsCreatorService {
	public Iterable<FondsCreator> findAll();
	public Iterable <FondsCreator> findAll(Integer pageNumber, Integer pageSize);	
	public FondsCreator findBySystemId(String systemId);	
}
