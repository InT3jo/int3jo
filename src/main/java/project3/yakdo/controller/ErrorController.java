/**
 * 에러 페이지 컨트롤러
 */
package project3.yakdo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ErrorController {
	public static final String ERROR_EXCEPTION = "jakarta.servlet.error.exception";
	public static final String ERROR_EXCEPTION_TYPE = "jakarta.servlet.error.exception_type";
	public static final String ERROR_MESSAGE = "jakarta.servlet.error.message";
	public static final String ERROR_REQUEST_URI = "jakarta.servlet.error.request_uri";
	public static final String ERROR_SERVLET_NAME = "jakarta.servlet.error.servlet_name";
	public static final String ERROR_STATUS_CODE = "jakarta.servlet.error.status_code";

	@RequestMapping("/error/400")
	public String err400(HttpServletRequest req, HttpServletResponse resp) {
		printError(req);
		return "error/400";
	}

	@RequestMapping("/error/404")
	public String err404(HttpServletRequest req, HttpServletResponse resp) {
		printError(req);
		return "error/404";
	}
	
	@RequestMapping("/error/405")
	public String err405(HttpServletRequest req, HttpServletResponse resp) {
		printError(req);
		return "error/405";
	}

	@RequestMapping("/error/500")
	public String err500(HttpServletRequest req, HttpServletResponse resp) {
		printError(req);
		return "error/500";
	}
	
	private void printError(HttpServletRequest req) {
		log.warn("ERROR_EXCEPTION {}", req.getAttribute(ERROR_EXCEPTION));
		log.warn("ERROR_EXCEPTION_TYPE {}", req.getAttribute(ERROR_EXCEPTION_TYPE));
		log.warn("ERROR_MESSAGE {}", req.getAttribute(ERROR_MESSAGE));
		log.warn("ERROR_REQUEST_URI {}", req.getAttribute(ERROR_REQUEST_URI));
		log.warn("ERROR_SERVLET_NAME {}", req.getAttribute(ERROR_SERVLET_NAME));
		log.warn("ERROR_STATUS_CODE {}", req.getAttribute(ERROR_STATUS_CODE));
		log.warn("DispatcherType {}", req.getDispatcherType());
	}
	
}
