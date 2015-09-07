package no.arkivlab.innsyn.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import no.arkivlab.innsyn.models.n5.DocumentObject;
import no.arkivlab.innsyn.operations.iface.IDocumentObjectService;
import no.arkivlab.innsyn.repositories.IDocumentObjectRepository;


@Transactional
@Service
public class DocumentObjectService implements IDocumentObjectService {
	
	@Autowired
	IDocumentObjectRepository documentObjectRepository;

	@Override
	public Iterable<DocumentObject> findAll(Integer pageNumber, Integer pageSize) {		
		Page<DocumentObject> page = documentObjectRepository.findAll(new PageRequest(pageNumber, pageSize));
		Iterable <DocumentObject> pagedDocumentObject = page.getContent();	 	
		return pagedDocumentObject;
	}

	@Override
	public Iterable<DocumentObject> findAll() {
		return documentObjectRepository.findAll();
	}
	
	@Override
	public DocumentObject findBySystemId(String systemId) {
		return documentObjectRepository.findBySystemId(systemId);
	}

	@Override
	public Iterable <DocumentObject> findByCreatedDate(String createdDate) {
		return documentObjectRepository.findByCreatedDate(createdDate);
	}
	
//	@PreAuthorize("hasRole('ROLE_USER')")
	@Override
	public Iterable <DocumentObject> findByCreatedBy(String createdBy) {
		return documentObjectRepository.findByCreatedBy(createdBy);
	}
}
