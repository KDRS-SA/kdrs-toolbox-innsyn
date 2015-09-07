package no.arkivlab.innsyn.controllers.exceptionHandling;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.arkivlab.innsyn.utils.Constants;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestController
public class ExceptionHandlingController implements ErrorController {

	private static final Logger logger = Logger
			.getLogger(ExceptionHandlingController.class);
	private static final String PATH = "/error";

	@Autowired
	private ErrorAttributes errorAttributes;

	@RequestMapping(value = PATH)
	public String error(HttpServletRequest request, HttpServletResponse response) {
		RequestAttributes requestAttributes = new ServletRequestAttributes(
				request);

		Map<String, Object> errors = errorAttributes.getErrorAttributes(
				requestAttributes, true);
		String error = (String) errors.get("error");
		String message = (String) errors.get("message");
		//String timeStamp = errors.get("timestamp").toString();		
		String trace = (String) errors.get("trace");
		logger.error(Constants.TOOL_NAME + " " + error + message + trace);
		return error + message + trace;
	}
	 
	@Override
	public String getErrorPath() {
		return PATH;
	}
}
