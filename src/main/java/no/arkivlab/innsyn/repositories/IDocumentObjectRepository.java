package no.arkivlab.innsyn.repositories;

import no.arkivlab.innsyn.models.n5.DocumentObject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true)
public interface IDocumentObjectRepository extends PagingAndSortingRepository <DocumentObject, Long> {

	@Query("FROM DocumentObject")
	public Iterable <DocumentObject> findAll();	
	public DocumentObject findBySystemId(String systemId);	
	public Iterable <DocumentObject> findByCreatedDate(String createdDate);
	public Iterable<DocumentObject> findByCreatedBy(String createdBy);	
}