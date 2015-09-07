package no.arkivlab.innsyn.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import no.arkivlab.innsyn.models.n5.Series;
import no.arkivlab.innsyn.operations.iface.ISeriesService;
import no.arkivlab.innsyn.utils.Constants;

@RestController
public class SeriesController {
	
	@Autowired 
	ISeriesService seriesService;
	
	private static final Logger logger = Logger.getLogger(SeriesController.class);
	
    @RequestMapping(value="/series", method=RequestMethod.GET)
    public Iterable <Series> getAllSeries() {
    	logger.info(Constants.TOOL_NAME + " : Request GET /fonds");    
    	return seriesService.findAll();
    }
    
    @RequestMapping(value="/series/{systemId}", method=RequestMethod.GET)
    public Series getSeriesBySystemId(@PathVariable("systemId") String systemId) {
    	return seriesService.findBySystemId(systemId);
    }
    
    @RequestMapping(value="/series", params="startDate", method=RequestMethod.GET)
    public Iterable <Series> getSeriesByStartDate(@RequestParam(value = "startDate", required = false) String createdDate) {
    	return seriesService.findByCreatedBy(createdDate); 
    }
}