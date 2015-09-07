package no.arkivlab.innsyn.repositories;

import java.util.List;

import no.arkivlab.innsyn.models.n5.Fonds;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true)
public interface IFondsRepository extends CrudRepository<Fonds, Long> {

	@Query("FROM Fonds")
	public List<Fonds> getAllFonds();
	public Fonds findBySystemId(String systemId);
	
}
 