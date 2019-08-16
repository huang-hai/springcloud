package fun.huanghai.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

import fun.huanghai.myrule.MySelfRule;

@SpringBootApplication
@EnableEurekaClient
//MySelfRule这个类不能@ComponentScan注解所在包下,@ComponentScan这个注解在@SpringBootApplication里面,
//所以这个自定义算法不能在fun.huanghai.springcloud这个包和子包下面,需要建另外一个包
@RibbonClient(name="SERVICECLOUD-DEPT",configuration=MySelfRule.class)
public class DeptConsumer80_App {

	public static void main(String[] args) {
		SpringApplication.run(DeptConsumer80_App.class, args);
	}
}
