package no.arkivlab.innsyn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import no.arkivlab.innsyn.models.n5.File;
import no.arkivlab.innsyn.operations.iface.IFileService;

@RestController
public class FileController {
	@Autowired 
	IFileService fileService;
	
    @RequestMapping(value="/file", method=RequestMethod.GET)
    public Iterable <File> getAllFile(@RequestParam(value = "pageNumber", required = true) Integer pageNumber, 
    							  	   @RequestParam(value = "pageSize", required = true) Integer pageSize) {
    	return fileService.findAll(pageNumber, pageSize);
    }
    
    @RequestMapping(value="/file/{systemId}", method=RequestMethod.GET)
    public File getFileBySystemId(@PathVariable("systemId") String systemId) {
    	return fileService.findBySystemId(systemId);
    }
    
    @RequestMapping(value="/files", params="startDate", method=RequestMethod.GET)
    public File getFileByStartDate(
    		String startDate) {
    	return new File();
    }
}