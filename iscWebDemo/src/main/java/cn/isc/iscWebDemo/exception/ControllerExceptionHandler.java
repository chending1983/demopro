package cn.isc.iscWebDemo.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * global controller error handler 
 * set exception handler and resource mappings 
 * mvc: throw-exception-if-no-handler-found: true 
 * web: resources: add-mappings: false
 * 
 * 
 * @author isc
 * @date 2021/01/11
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    
    private final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);
    
    /**
     * return response map detail
     * 
     * @param exception
     * @param request
     * @return
     *  timestamp
     *  status code
     *  error
     *  message
     *  request path
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Map<String, Object> handleException(RuntimeException exception, HttpServletRequest request) {
        logger.error("message", exception);
        Integer statusCode = (Integer)request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        logger.error("statusCode : " + statusCode);
        HttpStatus status = getStatus(request);        
        Map<String, Object> result = new HashMap<>();
        result.put("timestamp", new Date().getTime());
        result.put("status", status.value());
        result.put("error", status.getReasonPhrase());
        result.put("message", exception.getMessage());
        result.put("path", request.getRequestURI());
        logger.error("path: " + request.getRequestURI());
        return result;
    }
    
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer)request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        } catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }


}
