package no.arkivlab.innsyn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import no.arkivlab.innsyn.models.n5.DocumentDescription;
import no.arkivlab.innsyn.operations.iface.IDocumentDescriptionService;

@RestController
public class DocumentDescriptionController {
	@Autowired 
	IDocumentDescriptionService documentDescriptionService;
	
    @RequestMapping(value="/documentDescription", method=RequestMethod.GET)
    public Iterable<DocumentDescription> getAllDocumentDescription() {
    	return documentDescriptionService.findAll();
    }
    
    @RequestMapping(value="/documentDescription/{systemId}", method=RequestMethod.GET)
    public DocumentDescription getDocumentDescriptionBySystemId(@PathVariable("systemId") String systemId) {
    	return documentDescriptionService.findBySystemId(systemId);
    }
    
    @RequestMapping(value="/documentDescription", params="startDate", method=RequestMethod.GET)
    public Iterable<DocumentDescription> getDocumentDescriptionByStartDate(@RequestParam(value = "startDate", required = false) String createdDate) {
    	return documentDescriptionService.findByCreatedDate(createdDate);
    }
}