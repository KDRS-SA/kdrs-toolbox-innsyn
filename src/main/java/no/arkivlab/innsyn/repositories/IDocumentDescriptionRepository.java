package no.arkivlab.innsyn.repositories;

import no.arkivlab.innsyn.models.n5.DocumentDescription;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true)
public interface IDocumentDescriptionRepository extends PagingAndSortingRepository <DocumentDescription, Long> {

	@Query("FROM DocumentDescription")
	public Iterable <DocumentDescription> findAll();	
	public DocumentDescription findBySystemId(String systemId);	
	public Iterable <DocumentDescription> findByCreatedDate(String createdDate);
	public Iterable<DocumentDescription> findByCreatedBy(String createdBy);	
}