package no.arkivlab.innsyn.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import no.arkivlab.innsyn.models.n5.File;
import no.arkivlab.innsyn.operations.iface.IFileService;
import no.arkivlab.innsyn.repositories.IFileRepository;

@Transactional
@Service
public class FileService implements IFileService {

	@Autowired
	IFileRepository fileRepository;

	@Override
	public Iterable<File> findAll(Integer pageNumber, Integer pageSize) {		
		Page<File> page = fileRepository.findAll(new PageRequest(pageNumber, pageSize));
		Iterable <File> pagedFile = page.getContent();	 	
		return pagedFile;
	}

	@Override
	public File findBySystemId(String systemId) {
		return fileRepository.findBySystemId(systemId);
	}

	@Override
	public Iterable <File> findByCreatedDate(String createdDate) {
		return fileRepository.findByCreatedDate(createdDate);
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@Override
	public Iterable <File> findByCreatedBy(String createdBy) {
		return fileRepository.findByCreatedBy(createdBy);
	}
}
