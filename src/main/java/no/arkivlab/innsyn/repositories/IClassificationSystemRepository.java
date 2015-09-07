package no.arkivlab.innsyn.repositories;

import no.arkivlab.innsyn.models.n5.ClassificationSystem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true)
public interface IClassificationSystemRepository extends PagingAndSortingRepository <ClassificationSystem, Long> {

	@Query("FROM ClassificationSystem")
	public Iterable <ClassificationSystem> findAll();	
	public ClassificationSystem findBySystemId(String systemId);	
	public Iterable <ClassificationSystem> findByCreatedDate(String createdDate);
	public Iterable<ClassificationSystem> findByCreatedBy(String createdBy);	
}