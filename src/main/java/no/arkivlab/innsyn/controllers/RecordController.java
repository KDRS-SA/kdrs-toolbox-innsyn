package no.arkivlab.innsyn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import no.arkivlab.innsyn.models.n5.Record;
import no.arkivlab.innsyn.operations.iface.IRecordService;

@RestController
public class RecordController {
	@Autowired 
	IRecordService recordService;
	
    @RequestMapping(value="/record", method=RequestMethod.GET)
    public Iterable<Record> getAllRecord() {
    	return recordService.findAll();
    }
    
    @RequestMapping(value="/record/{systemId}", method=RequestMethod.GET)
    public Record getRecordBySystemId(@PathVariable("systemId") String systemId) {
    	return recordService.findBySystemId(systemId);
    }
    
    @RequestMapping(value="/record", params="startDate", method=RequestMethod.GET)
    public Iterable<Record> getRecordByStartDate(@RequestParam(value = "startDate", required = false) String createdDate) {
    	return recordService.findByCreatedDate(createdDate);
    }
}