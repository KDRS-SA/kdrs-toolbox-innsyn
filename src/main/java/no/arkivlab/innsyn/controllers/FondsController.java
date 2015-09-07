package no.arkivlab.innsyn.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import no.arkivlab.innsyn.export.IExportManager;
import no.arkivlab.innsyn.models.n5.Fonds;
import no.arkivlab.innsyn.operations.iface.IFondsService;
import no.arkivlab.innsyn.utils.Constants;

@RestController
public class FondsController {
	
	@Autowired 
	IFondsService fondsService;
	
	@Autowired 
	IExportManager exportManager;
	
	private static final Logger logger = Logger.getLogger(FondsController.class);
	
    @RequestMapping(value="/fonds", method=RequestMethod.GET)
    public List<Fonds> getAllFonds() {
    	logger.info(Constants.TOOL_NAME + " : Request GET /fonds");    
    	return fondsService.getAllFonds();
    }
    
    @RequestMapping(value="/name", method=RequestMethod.POST)
    public String currentUserNameSimple(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        return principal.getName();
    }
    
    @RequestMapping(value="/fonds/{systemId}", method=RequestMethod.GET)
    public Fonds getFondsBySystemId(@PathVariable("systemId") String systemId) {
    	return fondsService.getFondsBySystemId(systemId);
    }
    
    @RequestMapping(value="/fonds/{systemId}/createExport", method=RequestMethod.GET)
    public String exportFondsBySystemId(@PathVariable("systemId") String systemId) {
    	Fonds fonds = fondsService.getFondsBySystemId(systemId);
   	
    	exportManager.exportArkivstrukturFromFonds(fonds);
    	return "It works ... maybe";
    }
    
    //@RequestMapping(value="/fonds/{systemId}/downloadExport", method=RequestMethod.GET)
    //public @ResponseBody byte[] downloadExport()
    //        throws Exception {
    
    @RequestMapping(value="/fonds", params="startDate", method=RequestMethod.GET)
    public List<Fonds> getFondsByStartDate(@RequestParam(value = "startDate", required = false) String createdDate) {
    	return fondsService.getFondsByCreatedDate(createdDate);
    }
    

    
    
}