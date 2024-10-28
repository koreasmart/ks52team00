package ksmybatis.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import ksmybatis.common.interceptor.CommonInterceptor;
import ksmybatis.login.interceptor.LoginInterceptor;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

	private final CommonInterceptor commonInterceptor;
	private final LoginInterceptor loginInterceptor;

	@Value("${file.path}")
	private String filePath;
	
	public String getOSRootPath() {
		String rootPath = "file:///";
		String os = System.getProperty("os.name").toLowerCase();
		
		if(os.contains("window")) {
			rootPath += "d:";
		}
		return rootPath;
	}
	
	/**
	 * 외부파일 연결
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String rootPath = getOSRootPath();
		
		registry.addResourceHandler("/attachment/**")
				.addResourceLocations(rootPath + filePath + "/attachment/")
				.setCachePeriod(3600)
				.resourceChain(true)
				.addResolver(new PathResourceResolver());
		
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		
		List<String> excludePath = new ArrayList<String>();
		excludePath.add("/favicon.ico");
		excludePath.add("/manage/img/**");
		excludePath.add("/manage/js/**");
		excludePath.add("/manage/css/**");
		excludePath.add("/manage/scss/**");
		excludePath.add("/manage/vendor/**");
		excludePath.add("/error");
		excludePath.add("/error/**");
		
		
		registry.addInterceptor(commonInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns(excludePath);
		
		excludePath.add("/");
		excludePath.add("/login");
		excludePath.add("/loginProc");
		excludePath.add("/member/addMember");
		excludePath.add("/member/idCheck");
		excludePath.add("/logout");
		
		registry.addInterceptor(loginInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns(excludePath);
		
		
		WebMvcConfigurer.super.addInterceptors(registry);
	}
}






