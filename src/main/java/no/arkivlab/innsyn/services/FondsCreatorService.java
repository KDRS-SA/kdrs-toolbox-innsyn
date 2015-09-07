package no.arkivlab.innsyn.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import no.arkivlab.innsyn.models.n5.FondsCreator;
import no.arkivlab.innsyn.operations.iface.IFondsCreatorService;
import no.arkivlab.innsyn.repositories.IFondsCreatorRepository;


@Transactional
@Service
public class FondsCreatorService implements IFondsCreatorService {
	
	@Autowired
	IFondsCreatorRepository fondsCreatorRepository;

	@Override
	public Iterable<FondsCreator> findAll(Integer pageNumber, Integer pageSize) {		
		Page<FondsCreator> page = fondsCreatorRepository.findAll(new PageRequest(pageNumber, pageSize));
		Iterable <FondsCreator> pagedFondsCreator = page.getContent();	 	
		return pagedFondsCreator;
	}

	@Override
	public Iterable<FondsCreator> findAll() {
		return fondsCreatorRepository.findAll();
	}
	
	@Override
	public FondsCreator findBySystemId(String systemId) {
		return fondsCreatorRepository.findBySystemId(systemId);
	}
}
