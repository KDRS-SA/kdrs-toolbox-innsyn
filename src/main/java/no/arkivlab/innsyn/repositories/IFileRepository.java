package no.arkivlab.innsyn.repositories;

import no.arkivlab.innsyn.models.n5.File;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true)
public interface IFileRepository extends PagingAndSortingRepository <File, Long> {

	@Query("FROM File")
	public Iterable <File> findAll();	
	public File findBySystemId(String systemId);	
	public Iterable <File> findByCreatedDate(String createdDate);
	public Iterable<File> findByCreatedBy(String createdBy);
	
}
 