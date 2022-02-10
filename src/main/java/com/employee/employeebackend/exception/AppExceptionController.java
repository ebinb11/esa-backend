package com.employee.employeebackend.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.employee.employeebackend.utils.AppResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@Slf4j
public class AppExceptionController implements ErrorController {

	@Value("${error.path:/error}")
	private String errorPath;

	@Autowired
	private ErrorAttributes errorAttributes;

	public String getErrorPath() {
		return this.errorPath;
	}

	@RequestMapping(value = "${error.path:/error}")
	@ResponseBody
	public ResponseEntity<Object> error(final HttpServletRequest request, final WebRequest webRequest) {
		HttpStatus status = getStatus(request);
		Throwable throwable = errorAttributes.getError(webRequest);
		Map<String, Object> error = errorAttributes.getErrorAttributes(webRequest, ErrorAttributeOptions.defaults());
		String message = null;
		Integer errorCode = status.value();
		if ((throwable != null)) {
			message = throwable.getMessage();
		}

		if (message == null) {
			// Check in error
			if (error.get("message") != null && error.get("message") instanceof String) {
				message = (String) error.get("message");
			}
		}

		if (message == null) {
			message = "Unable to get message";
		}

		if (errorCode == 401) {
			message = "UNAUTHORIZED";
		}

		return new ResponseEntity<>(AppResponse.builder().message(message).build(), status);
	}

	private HttpStatus getStatus(final HttpServletRequest request) {
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		if (statusCode != null) {
			try {
				return HttpStatus.valueOf(statusCode);
			} catch (Exception ex) {
				log.warn("Error decoding " + statusCode, ex);
			}
		}
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}

}
