package no.arkivlab.innsyn.repositories;

import no.arkivlab.innsyn.models.n5.Series;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true)
public interface ISeriesRepository extends PagingAndSortingRepository <Series, Long> {

	@Query("FROM Series")
	public Iterable <Series> findAll();	
	public Series findBySystemId(String systemId);	
	public Iterable <Series> findByCreatedDate(String createdDate);
	public Iterable<Series> findByCreatedBy(String createdBy);	
}
 