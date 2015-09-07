package no.arkivlab.innsyn.operations.iface;

import no.arkivlab.innsyn.models.n5.DocumentDescription;

public interface IDocumentDescriptionService {
	public Iterable<DocumentDescription> findAll();
	public Iterable <DocumentDescription> findAll(Integer pageNumber, Integer pageSize);	
	public DocumentDescription findBySystemId(String systemId);	
	public Iterable <DocumentDescription> findByCreatedDate(String createdDate);
	public Iterable <DocumentDescription> findByCreatedBy(String createdBy);
}
