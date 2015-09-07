package no.arkivlab.innsyn.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import no.arkivlab.innsyn.models.n5.ClassificationSystem;
import no.arkivlab.innsyn.operations.iface.IClassificationSystemService;
import no.arkivlab.innsyn.repositories.IClassificationSystemRepository;


@Transactional
@Service
public class ClassificationSystemService implements IClassificationSystemService {
	
	@Autowired
	IClassificationSystemRepository classificationSystemRepository;

	@Override
	public Iterable<ClassificationSystem> findAll(Integer pageNumber, Integer pageSize) {		
		Page<ClassificationSystem> page = classificationSystemRepository.findAll(new PageRequest(pageNumber, pageSize));
		Iterable <ClassificationSystem> pagedClassificationSystem = page.getContent();	 	
		return pagedClassificationSystem;
	}

	@Override
	public Iterable<ClassificationSystem> findAll() {
		return classificationSystemRepository.findAll();
	}
	
	@Override
	public ClassificationSystem findBySystemId(String systemId) {
		return classificationSystemRepository.findBySystemId(systemId);
	}

	@Override
	public Iterable <ClassificationSystem> findByCreatedDate(String createdDate) {
		return classificationSystemRepository.findByCreatedDate(createdDate);
	}
	
//	@PreAuthorize("hasRole('ROLE_USER')")
	@Override
	public Iterable <ClassificationSystem> findByCreatedBy(String createdBy) {
		return classificationSystemRepository.findByCreatedBy(createdBy);
	}
}
