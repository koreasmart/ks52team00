package ksmybatis.login.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		String sessionId = (String) session.getAttribute("SID");
		boolean isProcess = true;
		
		if(sessionId == null) {
			response.sendRedirect("/login");
			isProcess = false;
		}else {
			String sessionGrade = (String) session.getAttribute("SGRD");
			String requestUri = request.getRequestURI();
			if("mbr_grd_2".equals(sessionGrade)) {
				if(requestUri.indexOf("/member") > -1) {
					response.sendRedirect("/");
					isProcess = false;
				}
			}
		}

		return isProcess;
	}
}








