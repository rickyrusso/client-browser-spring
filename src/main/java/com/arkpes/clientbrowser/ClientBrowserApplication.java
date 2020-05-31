package com.arkpes.clientbrowser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

@SpringBootApplication
public class ClientBrowserApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientBrowserApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins(getCrossOrigin());
			}
		};
	}

	private String getCrossOrigin(){
		InputStream inputStream = null;
		String crossOrigin = null;

		try {
			Properties prop = new Properties();
			String propFileName = "application.properties";

			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}

			crossOrigin = prop.getProperty("clientBrowser.allowedcrossorgin");
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			if(inputStream != null)
				try {
					inputStream.close();
				}catch(Exception ex){}
		}

		return crossOrigin;
	}

}
