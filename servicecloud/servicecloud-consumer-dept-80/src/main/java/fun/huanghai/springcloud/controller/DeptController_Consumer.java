package fun.huanghai.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fun.huanghai.springcloud.entity.Dept;

@RestController
public class DeptController_Consumer {

	//private static final String REST_URL_PREFIX = "http://localhost:8001";;
	private static final String REST_URL_PREFIX = "http://SERVICECLOUD-DEPT";;
	
	@Autowired
	private RestTemplate template;
	
	@RequestMapping(value="/consumer/dept/add")
	public boolean add(Dept dept) {
		String url = REST_URL_PREFIX + "/dept/add";
		return template.postForObject(url, dept, Boolean.class);
	}
	
	@RequestMapping(value="/consumer/dept/get/{id}")
	public Dept get(@PathVariable("id")Long id) {
		String url = REST_URL_PREFIX + "/dept/get/" + id;
		return template.getForObject(url, Dept.class);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/consumer/dept/list")
	public List<Dept> list() {
		String url = REST_URL_PREFIX + "/dept/list";
		return template.getForObject(url, List.class);
	}
	
	@RequestMapping(value="/consumer/dept/discovery")
	public Object discovery() {
		String url = REST_URL_PREFIX + "/dept/discovery";
		return template.getForObject(url, Object.class);
	}
}
