package com.longmao.demo.config.web.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

@Configuration
public class ShiroConfig {

	@Autowired
	private MyRealm myRealm;

	@Bean
	public DefaultWebSecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(myRealm);
		securityManager.setRememberMeManager(rememberMeManager());
		securityManager.setSessionManager(sessionManager());
		return securityManager;
	}

	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean() {
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		shiroFilter.setSecurityManager(securityManager());
		shiroFilter.setLoginUrl("/account/login");
		shiroFilter.setSuccessUrl("/account/dashboard");

		Map<String, String> urlFileter = new LinkedHashMap<String, String>();
		urlFileter.put("/static/**", "anon");
		urlFileter.put("/test/getData", "anon");
		urlFileter.put("/test/toPage", "anon");
		urlFileter.put("/test/getAverage", "anon");
		urlFileter.put("/js/**", "anon");
		urlFileter.put("/css/**", "anon");
		urlFileter.put("/account/login", "anon");
		urlFileter.put("/account/captcha.jpg", "anon");
		urlFileter.put("/account/doLogin", "anon");
		urlFileter.put("/account/register", "anon");
		urlFileter.put("/account/checkUserName", "anon");
		urlFileter.put("/account/doRegister", "anon");
		urlFileter.put("/account/logout", "anon");
		urlFileter.put("/**", "user");
		shiroFilter.setFilterChainDefinitionMap(urlFileter);

		return shiroFilter;
	}

	@Bean
	public ShiroDialect shiroDialect() {
		return new ShiroDialect();
	}

	@Bean
	public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		advisorAutoProxyCreator.setProxyTargetClass(true);
		return advisorAutoProxyCreator;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
		return authorizationAttributeSourceAdvisor;
	}

	@Bean
	public SimpleCookie rememberMeCookie() {
		SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
		simpleCookie.setHttpOnly(true);
		simpleCookie.setMaxAge(7 * 24 * 60 * 60);
		return simpleCookie;
	}

	@Bean
	public CookieRememberMeManager rememberMeManager() {
		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
		byte[] cipherKey = Base64.decode("wGiHplamyXlVB11UXWol8g==");
		cookieRememberMeManager.setCipherKey(cipherKey);
		cookieRememberMeManager.setCookie(rememberMeCookie());
		return cookieRememberMeManager;
	}
	@Bean
	public DefaultWebSessionManager sessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setSessionIdUrlRewritingEnabled(false);
		sessionManager.setSessionIdCookie(rememberMeCookie());
		return sessionManager;
	}

}
