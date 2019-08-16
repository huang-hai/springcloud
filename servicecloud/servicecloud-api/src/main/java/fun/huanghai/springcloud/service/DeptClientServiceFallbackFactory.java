package fun.huanghai.springcloud.service;

import java.util.List;

import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;
import fun.huanghai.springcloud.entity.Dept;

@Component //添加@Component将组件加入到spring容器中
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService> {

	@Override
	public DeptClientService create(Throwable arg0) {
		return new DeptClientService() {
			
			@Override
			public List<Dept> list() {
				
				return null;
			}
			
			@Override
			public Dept get(Long id) {
				return new Dept(id,"该ID:"+id+"没有对应的部门信息,Consumer客户端提供的降级信息,此刻provider已暂停服务","no this database in MySQL");
			}
			
			@Override
			public boolean add(Dept dept) {
				
				return false;
			}
		};
	}

}
