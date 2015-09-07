package no.arkivlab.innsyn.repositories;

import no.arkivlab.innsyn.models.n5.FondsCreator;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true)
public interface IFondsCreatorRepository extends PagingAndSortingRepository <FondsCreator, Long> {

	@Query("FROM FondsCreator")
	public Iterable <FondsCreator> findAll();
	public FondsCreator findBySystemId(String systemId);
}