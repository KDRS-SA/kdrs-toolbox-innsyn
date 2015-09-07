package no.arkivlab.innsyn.operations.iface;

import no.arkivlab.innsyn.models.n5.DocumentObject;

public interface IDocumentObjectService {
	public Iterable<DocumentObject> findAll();
	public Iterable <DocumentObject> findAll(Integer pageNumber, Integer pageSize);	
	public DocumentObject findBySystemId(String systemId);	
	public Iterable <DocumentObject> findByCreatedDate(String createdDate);
	public Iterable <DocumentObject> findByCreatedBy(String createdBy);
}
