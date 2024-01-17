package config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {RootConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {ServletConfiguration.class};
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] {"/"};
	}

	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encoding = new CharacterEncodingFilter();
		encoding.setEncoding("UTF-8");
		encoding.setForceEncoding(true); 

		return new Filter[] {encoding};
	}

	@Override
	protected void customizeRegistration(Dynamic registration) {
		String uploadLocation ="C:\\HS\\_myFiles\\_java\\_fileUpload";
		int maxFileSize= 1024*1024*20;//20M
		int maxReqSize = maxFileSize*2; //40M
		int fileSizeThreshold = maxFileSize; //20M	
		
		//multipartConfig 설정
		MultipartConfigElement multipartConfig=
				new MultipartConfigElement(uploadLocation,maxFileSize,maxReqSize,fileSizeThreshold);
				registration.setMultipartConfig(multipartConfig);
	}

	
}
