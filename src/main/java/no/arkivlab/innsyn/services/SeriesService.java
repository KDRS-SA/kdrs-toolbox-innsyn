package no.arkivlab.innsyn.services;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import no.arkivlab.innsyn.models.n5.Series;
import no.arkivlab.innsyn.operations.iface.ISeriesService;
import no.arkivlab.innsyn.repositories.ISeriesRepository;


@Transactional
@Service
public class SeriesService implements ISeriesService {
	
	@Autowired
	ISeriesRepository seriesRepository;

	@Override
	public Iterable<Series> findAll(Integer pageNumber, Integer pageSize) {		
		Page<Series> page = seriesRepository.findAll(new PageRequest(pageNumber, pageSize));
		Iterable <Series> pagedSeries = page.getContent();	 	
		return pagedSeries;
	}

	@Override
	public Iterable<Series> findAll() {
		return seriesRepository.findAll();
	}
	
	@Override
	public Series findBySystemId(String systemId) {
		return seriesRepository.findBySystemId(systemId);
	}

	@Override
	public Iterable <Series> findByCreatedDate(String createdDate) {
		return seriesRepository.findByCreatedDate(createdDate);
	}
	
//	@PreAuthorize("hasRole('ROLE_USER')")
	@Override
	public Iterable <Series> findByCreatedBy(String createdBy) {
		return seriesRepository.findByCreatedBy(createdBy);
	}
}
