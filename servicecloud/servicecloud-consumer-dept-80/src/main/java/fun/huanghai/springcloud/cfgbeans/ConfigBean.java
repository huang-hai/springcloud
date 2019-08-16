package fun.huanghai.springcloud.cfgbeans;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;

@Configuration
public class ConfigBean {

	@Bean
	@LoadBalanced //SpringCloud Ribbon是基于Netflix Ribbon实现的一套客户端  负载均衡的工具
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	public IRule myRule() {
		//RoundRobinRule  轮询算法 默认的
		//RandomRule      随机算法
		//RetryRule       先轮询,如果某一个微服务两次失败,则跳过
		return new RoundRobinRule(); 
	}
}
