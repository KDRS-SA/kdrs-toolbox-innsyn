package no.arkivlab.innsyn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import no.arkivlab.innsyn.models.n5.FondsCreator;
import no.arkivlab.innsyn.operations.iface.IFondsCreatorService;

@RestController
public class FondsCreatorController {
	@Autowired 
	IFondsCreatorService fondsCreatorService;
	
    @RequestMapping(value="/fondsCreator", method=RequestMethod.GET)
    public Iterable<FondsCreator> getAllFondsCreator() {
    	return fondsCreatorService.findAll();
    }
    
    @RequestMapping(value="/fondsCreator/{systemId}", method=RequestMethod.GET)
    public FondsCreator getFondsCreatorBySystemId(@PathVariable("systemId") String systemId) {
    	return fondsCreatorService.findBySystemId(systemId);
    }
}