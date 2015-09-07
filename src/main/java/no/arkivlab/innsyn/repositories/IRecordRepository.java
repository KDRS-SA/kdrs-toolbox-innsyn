package no.arkivlab.innsyn.repositories;

import no.arkivlab.innsyn.models.n5.Record;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true)
public interface IRecordRepository extends PagingAndSortingRepository <Record, Long> {

	@Query("FROM Record")
	public Iterable <Record> findAll();	
	public Record findBySystemId(String systemId);	
	public Iterable <Record> findByCreatedDate(String createdDate);
	public Iterable<Record> findByCreatedBy(String createdBy);	
}