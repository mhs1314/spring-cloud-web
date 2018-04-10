package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RefreshScope
public class VueController {

	@Value("${cloud.sample.msg:World}")
	String msg;

	@Autowired
	private ServiceClient serviceClient;

	@RequestMapping("/index")
	public String index() {
		System.out.println(msg);
		return msg;
	}

	@RequestMapping("/test/{id}")
	public String test(@PathVariable Long id) {
		return serviceClient.printf(id);
	}

	@HystrixCommand(fallbackMethod = "addback") // 断路器注解
	@RequestMapping("/add")
	public String add() {
		// 模拟出错
		int a = Integer.parseInt("ee");
		a = a + 1;
		return "erro";
	}

	// 备用方法
	@RequestMapping("/addback")
	public String addback() {
		return "ok";
	}
}
