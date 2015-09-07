package no.arkivlab.innsyn.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import no.arkivlab.innsyn.models.n5.Class;
import no.arkivlab.innsyn.operations.iface.IClassService;
import no.arkivlab.innsyn.repositories.IClassRepository;


@Transactional
@Service
public class ClassService implements IClassService {
	
	@Autowired
	IClassRepository classRepository;

	@Override
	public Iterable<Class> findAll(Integer pageNumber, Integer pageSize) {		
		Page<Class> page = classRepository.findAll(new PageRequest(pageNumber, pageSize));
		Iterable <Class> pagedClass = page.getContent();	 	
		return pagedClass;
	}

	@Override
	public Iterable<Class> findAll() {
		return classRepository.findAll();
	}
	
	@Override
	public Class findBySystemId(String systemId) {
		return classRepository.findBySystemId(systemId);
	}

	@Override
	public Iterable <Class> findByCreatedDate(String createdDate) {
		return classRepository.findByCreatedDate(createdDate);
	}
	
	@Override
	public Iterable <Class> findByCreatedBy(String createdBy) {
		return classRepository.findByCreatedBy(createdBy);
	}
}
