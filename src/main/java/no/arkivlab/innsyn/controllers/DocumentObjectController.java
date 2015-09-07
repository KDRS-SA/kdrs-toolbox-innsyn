package no.arkivlab.innsyn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import no.arkivlab.innsyn.models.n5.DocumentObject;
import no.arkivlab.innsyn.operations.iface.IDocumentObjectService;

@RestController
public class DocumentObjectController {
	@Autowired 
	IDocumentObjectService documentObjectService;
	
    @RequestMapping(value="/documentObject", method=RequestMethod.GET)
    public Iterable<DocumentObject> getAllDocumentObject() {
    	return documentObjectService.findAll();
    }
    
    @RequestMapping(value="/documentObject/{systemId}", method=RequestMethod.GET)
    public DocumentObject getDocumentObjectBySystemId(@PathVariable("systemId") String systemId) {
    	return documentObjectService.findBySystemId(systemId);
    }
    
    @RequestMapping(value="/documentObject", params="startDate", method=RequestMethod.GET)
    public Iterable<DocumentObject> getDocumentObjectByStartDate(@RequestParam(value = "startDate", required = false) String createdDate) {
    	return documentObjectService.findByCreatedDate(createdDate);
    }
}