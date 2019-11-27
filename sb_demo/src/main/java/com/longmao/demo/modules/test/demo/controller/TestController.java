package com.longmao.demo.modules.test.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.longmao.demo.modules.test.demo.vo.ApplicationTestBean;

@Controller
public class TestController {
	@Value("${server.port}")
	private int port;
	
	@Autowired
	private ApplicationTestBean an;
	
private final static Logger LOGGER=LoggerFactory.getLogger(TestController.class);
	
	@RequestMapping("test1")
	@ResponseBody
	public String testDemo() {
		StringBuffer sBuffer=new StringBuffer();
		sBuffer.append("port:").append(port);
		return sBuffer.toString();
	}
	@RequestMapping("text")
	@ResponseBody
	public String  textDemo() {
		StringBuffer sb=new StringBuffer();
		sb.append(an.getName()).append("<br/>")
		.append(an.getAge()).append("<br/>")
		.append(an.getDescription()).append("<br/>")
		.append(an.getRandom()).append("<br/>");
		return sb.toString();
	}
	@RequestMapping("logger")
	@ResponseBody
	public String  LogTest() {
		StringBuffer sb=new StringBuffer("日志测试控制器");
		LOGGER.trace("teace");
		LOGGER.debug("debug");
		LOGGER.info("info");
		LOGGER.warn("warn");
		LOGGER.error("error");
		return sb.toString();
	}
}
