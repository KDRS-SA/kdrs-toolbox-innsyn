package no.arkivlab.innsyn.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import no.arkivlab.innsyn.models.n5.Record;
import no.arkivlab.innsyn.operations.iface.IRecordService;
import no.arkivlab.innsyn.repositories.IRecordRepository;


@Transactional
@Service
public class RecordService implements IRecordService {
	
	@Autowired
	IRecordRepository recordRepository;

	@Override
	public Iterable<Record> findAll(Integer pageNumber, Integer pageSize) {		
		Page<Record> page = recordRepository.findAll(new PageRequest(pageNumber, pageSize));
		Iterable <Record> pagedRecord = page.getContent();	 	
		return pagedRecord;
	}

	@Override
	public Iterable<Record> findAll() {
		return recordRepository.findAll();
	}
	
	@Override
	public Record findBySystemId(String systemId) {
		return recordRepository.findBySystemId(systemId);
	}

	@Override
	public Iterable <Record> findByCreatedDate(String createdDate) {
		return recordRepository.findByCreatedDate(createdDate);
	}
	
//	@PreAuthorize("hasRole('ROLE_USER')")
	@Override
	public Iterable <Record> findByCreatedBy(String createdBy) {
		return recordRepository.findByCreatedBy(createdBy);
	}
}
