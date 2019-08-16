package fun.huanghai.springcloud.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fun.huanghai.springcloud.entity.Dept;

//@FeignClient("SERVICECLOUD-DEPT") //服务熔断
//服务降级 如果调用服务出错,找fallbackFactory对应的方法返回信息
@FeignClient(value="SERVICECLOUD-DEPT",fallbackFactory=DeptClientServiceFallbackFactory.class)
public interface DeptClientService {
	
	@RequestMapping(value="/dept/add",method=RequestMethod.POST)
	public boolean add(@RequestBody Dept dept);
	
	@RequestMapping(value="/dept/get/{id}",method=RequestMethod.GET)
	public Dept get(@PathVariable("id") Long id);
	
	@RequestMapping(value="/dept/list",method=RequestMethod.GET)
	public List<Dept> list() ;
}
