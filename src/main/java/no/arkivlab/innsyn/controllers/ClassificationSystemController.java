package no.arkivlab.innsyn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import no.arkivlab.innsyn.models.n5.ClassificationSystem;
import no.arkivlab.innsyn.operations.iface.IClassificationSystemService;

@RestController
public class ClassificationSystemController {
	@Autowired 
	IClassificationSystemService classificationSystemService;
	
    @RequestMapping(value="/classificationSystem", method=RequestMethod.GET)
    public Iterable<ClassificationSystem> getAllClassificationSystem() {
    	return classificationSystemService.findAll();
    }
    
    @RequestMapping(value="/classificationSystem/{systemId}", method=RequestMethod.GET)
    public ClassificationSystem getClassificationSystemBySystemId(@PathVariable("systemId") String systemId) {
    	return classificationSystemService.findBySystemId(systemId);
    }
    
    @RequestMapping(value="/classificationSystem", params="startDate", method=RequestMethod.GET)
    public Iterable<ClassificationSystem> getClassificationSystemByStartDate(@RequestParam(value = "startDate", required = false) String createdDate) {
    	return classificationSystemService.findByCreatedDate(createdDate);
    }
}