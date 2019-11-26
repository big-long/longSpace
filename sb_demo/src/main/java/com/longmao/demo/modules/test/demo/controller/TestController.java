package com.longmao.demo.modules.test.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	@RequestMapping("test")
	@ResponseBody
	public String testDemo() {
		return "this means sesscuse";
	}
}
