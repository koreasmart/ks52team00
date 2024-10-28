package ksmybatis.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * @ControllerAdvice : 웹프로젝트의 예외처리 기능을 담당하는 어노테이션(Spring MVC 예외처리, 예외메세지를 뷰로 전달하는 기능)
 *  옵션(
 *  	basePackages = "com.example.user"  : 특정 패키지 하위에 적용
 *  	annotations = "Controller.class, RestController.class" : 특정 어노테이션에 적용
 *  	assignableTypes = "UserController.class" : 특정 클래스타입에 적용
 *  )
 */
@ControllerAdvice
@Slf4j
public class GrobalExceptionHandler {
	
	/**
	 * @ExceptionHandler(Exception.class) : 예외 종류 맞는 메소드를 매핑하는 역할
	 * @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) : 응답시 에러코드를 반환하는 어노테이션
	 * @return String 에러메세지를 전달하는 논리적인 뷰 경로
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String exceptionAllHandler(Exception ex, HttpServletRequest request) {
		
		log.error("[url:{}] error message : {}", request.getRequestURI(), ex.getStackTrace());
		
		return "error/500";
	}
	
	@ExceptionHandler(NoResourceFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String exceptionNotFoundHandler(NoResourceFoundException ex, HttpServletRequest request) {
		
		log.error("[url:{}] error message : {}", request.getRequestURI(), ex.getMessage());
		
		return "error/404";
	}
	
	@ExceptionHandler({IllegalArgumentException.class, MethodArgumentTypeMismatchException.class, MissingServletRequestParameterException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String exceptionIllegalArgsHandler(Exception ex, HttpServletRequest request) {
		log.error("[url:{}] error message : {}", request.getRequestURI(), ex.getMessage());
		return "error/400";
	}
	
}





















