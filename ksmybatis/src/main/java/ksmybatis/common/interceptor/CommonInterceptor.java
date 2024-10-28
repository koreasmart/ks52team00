package ksmybatis.common.interceptor;

import java.util.Set;
import java.util.StringJoiner;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CommonInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		Set<String> paramMap = request.getParameterMap().keySet();
		
		StringJoiner param = new StringJoiner(", ");
		
		for(String paramKey : paramMap) {
			param.add(paramKey + " : " + request.getParameter(paramKey));
		}
		
		log.info("ACCESS INFO START=============================================");
		log.info("PORT 	::::::: {}", request.getLocalPort());
		log.info("SERVERNAME 	::::::: {}", request.getServerName());
		log.info("HTTP METHOD ::::::: {}", request.getMethod());
		log.info("URI 	::::::: {}", request.getRequestURI());
		log.info("CLIENT IP 	::::::: {}", request.getRemoteAddr());
		log.info("PARAMETER 	::::::: {}", param);
		log.info("ACCESS INFO END=============================================");
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}
