package fun.huanghai.myrule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;


@Configuration
public class MySelfRule {
	
	@Bean
	public IRule myRule() {
		//RoundRobinRule  轮询算法
		//RandomRule      随机算法
		//RetryRule       先轮询,如果某一个微服务两次失败,则跳过
		return new RoundRule_HH(); 
	}
}