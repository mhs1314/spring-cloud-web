package com.example.demo;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * fegin api请求
 * @author mhs
 *
 */
@Component
@FeignClient(value = "data") //服务名
public interface ServiceClient {

	@RequestMapping("/user/{id}") //服务方法 +参数
	String printf(@RequestParam("id") Long id);
}