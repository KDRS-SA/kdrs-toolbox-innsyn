package no.arkivlab.innsyn.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import no.arkivlab.innsyn.models.n5.DocumentDescription;
import no.arkivlab.innsyn.operations.iface.IDocumentDescriptionService;
import no.arkivlab.innsyn.repositories.IDocumentDescriptionRepository;


@Transactional
@Service
public class DocumentDescriptionService implements IDocumentDescriptionService {
	
	@Autowired
	IDocumentDescriptionRepository documentDescriptionRepository;

	@Override
	public Iterable<DocumentDescription> findAll(Integer pageNumber, Integer pageSize) {		
		Page<DocumentDescription> page = documentDescriptionRepository.findAll(new PageRequest(pageNumber, pageSize));
		Iterable <DocumentDescription> pagedDocumentDescription = page.getContent();	 	
		return pagedDocumentDescription;
	}

	@Override
	public Iterable<DocumentDescription> findAll() {
		return documentDescriptionRepository.findAll();
	}
	
	@Override
	public DocumentDescription findBySystemId(String systemId) {
		return documentDescriptionRepository.findBySystemId(systemId);
	}

	@Override
	public Iterable <DocumentDescription> findByCreatedDate(String createdDate) {
		return documentDescriptionRepository.findByCreatedDate(createdDate);
	}
	
//	@PreAuthorize("hasRole('ROLE_USER')")
	@Override
	public Iterable <DocumentDescription> findByCreatedBy(String createdBy) {
		return documentDescriptionRepository.findByCreatedBy(createdBy);
	}
}
