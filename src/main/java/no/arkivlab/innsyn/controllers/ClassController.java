package no.arkivlab.innsyn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import no.arkivlab.innsyn.models.n5.Class;
import no.arkivlab.innsyn.operations.iface.IClassService;

@RestController
public class ClassController {
	@Autowired 
	IClassService classService;
	
    @RequestMapping(value="/class", method=RequestMethod.GET)
    public Iterable<Class> getAllClass() {
    	return classService.findAll();
    }
    
    @RequestMapping(value="/class/{systemId}", method=RequestMethod.GET)
    public Class getClassBySystemId(@PathVariable("systemId") String systemId) {
    	return classService.findBySystemId(systemId);
    }
    
    @RequestMapping(value="/class", params="startDate", method=RequestMethod.GET)
    public Iterable<Class> getClassByStartDate(@RequestParam(value = "startDate", required = false) String createdDate) {
    	return classService.findByCreatedDate(createdDate);
    }
}