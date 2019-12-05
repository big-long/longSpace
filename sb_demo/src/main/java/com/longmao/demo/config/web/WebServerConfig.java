package com.longmao.demo.config.web;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter({WebMvcAutoConfiguration.class})
public class WebServerConfig {
	
	@Value("${server.http.port}")
	private int httpPort;
	
	@Bean
	public Connector connector() {
		Connector connector =new Connector();
		connector.setScheme("http");
		connector.setPort(httpPort);
		return connector;
	}
	@Bean
	public ServletWebServerFactory servletWebServerFactory() {
		TomcatServletWebServerFactory tomcat=new TomcatServletWebServerFactory() {

			@Override
			protected void postProcessContext(Context context) {
				SecurityCollection collection =new SecurityCollection();
				collection.addPattern("/**");
				
				SecurityConstraint constraint =new SecurityConstraint();
				constraint.addCollection(collection);
				constraint.setUserConstraint("CONFIDENTIAL");
				
				context.addConstraint(constraint);
			}
			
		};
		tomcat.addAdditionalTomcatConnectors(connector());
		return tomcat;
	}
}
