package com.longmao.demo.modules.test.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.longmao.demo.modules.test.demo.vo.ApplicationTestBean;

@Controller
@RequestMapping("test")
public class TestController {
	private final static Logger LOGGER = LoggerFactory.getLogger(TestController.class);

	@Value("${server.port}")
	private int port;
	@Autowired
	private ApplicationTestBean an;
	
//	@RequestMapping("downloadIo")
//	@ResponseBody
//	public void downloadIo(@RequestParam String fileName) {
//		String path="f:/upload/"+fileName;
//		try {
//			FileInputStream in=new FileInputStream(path);
//			in.read();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}

	@RequestMapping("download")
	@ResponseBody
	public ResponseEntity<Resource> download(@RequestParam String fileName) {
		try {
			Resource resource = new UrlResource(Paths.get("f:/upload/" + fileName).toUri());
			if (resource.exists() && resource.isReadable()) {
				return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + resource.getFilename() +"\"")
						.body(resource);
			}
		} catch (MalformedURLException e) {
			LOGGER.debug(e.getMessage());
		}
		return null;
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = "multipart/form-data")
	public String upload(@RequestParam MultipartFile file, RedirectAttributes redirectAttributes) {
		String message = null;
		if (file.isEmpty()) {
			message = "please select file.";
		} else {
			try {
				String fileName = file.getOriginalFilename();
				String dercPath = "f:/upload" + File.separator + fileName;
				File dercFile = new File(dercPath);
				file.transferTo(dercFile);
				message = "uplode file success.";
			} catch (IllegalStateException e) {
				LOGGER.debug(e.getMessage());
				message = "uplode file success.";
			} catch (IOException e) {
				LOGGER.debug(e.getMessage());
				message = "uplode file success.";
			}
		}
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:index";
	}

	@RequestMapping("/test")
	@ResponseBody
	public String test(HttpServletRequest request, @RequestParam String key) {
		String value = request.getParameter("key");
		return "key:" + key + "value:" + value;
	}

	@RequestMapping("page")
	public String pageTest(ModelMap modelMap) {
		modelMap.addAttribute("template", "test/index");
		return "index";
	}

	@RequestMapping("index")
	public String index() {
		return "index";
	}

	@RequestMapping("test1")
	@ResponseBody
	public String testDemo() {
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("port:").append(port);
		return sBuffer.toString();
	}

	@RequestMapping("text")
	@ResponseBody
	public String textDemo() {
		StringBuffer sb = new StringBuffer();
		sb.append(an.getName()).append("<br/>").append(an.getAge()).append("<br/>").append(an.getDescription())
				.append("<br/>").append(an.getRandom()).append("<br/>");
		return sb.toString();
	}

	@RequestMapping("logger")
	@ResponseBody
	public String LogTest() {
		StringBuffer sb = new StringBuffer("日志测试控制器");
		LOGGER.trace("teace");
		LOGGER.debug("debug");
		LOGGER.info("info");
		LOGGER.warn("warn");
		LOGGER.error("error");
		return sb.toString();
	}
}
