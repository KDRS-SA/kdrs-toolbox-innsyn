package no.arkivlab.innsyn.repositories;

import no.arkivlab.innsyn.models.n5.Class;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true)
public interface IClassRepository extends PagingAndSortingRepository <Class, Long> {

	@Query("FROM Class")
	public Iterable <Class> findAll();	
	public Class findBySystemId(String systemId);	
	public Iterable <Class> findByCreatedDate(String createdDate);
	public Iterable<Class> findByCreatedBy(String createdBy);	
}