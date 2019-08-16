package fun.huanghai.myrule;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

/**
 * 自定义算法需求:
 * 每一个微服务执行5次,执行完5次以后换下一个微服务,
 * 每个都执行完5次后重新回到第一个再次循环
 * @author Administrator
 *
 */
public class RoundRule_HH extends AbstractLoadBalancerRule {

	//当前微服务执行的次数
    private Integer total = 0;
    //当前执行的微服务下标(第几个微服务)
    private Integer currentIndex = 0;

    private static Logger log = LoggerFactory.getLogger(MySelfRule.class);


    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            log.warn("no load balancer");
            return null;
        }

        Server server = null;
        int count = 0;
        while (server == null && count++ < 10) {
            List<Server> reachableServers = lb.getReachableServers();
            List<Server> allServers = lb.getAllServers();
            int upCount = reachableServers.size();
            int serverCount = allServers.size();

            if ((upCount == 0) || (serverCount == 0)) {
                log.warn("No up servers available from load balancer: " + lb);
                return null;
            }

            //int nextServerIndex = incrementAndGetModulo(serverCount);
            //server = allServers.get(nextServerIndex);

            if(total < 5) {
            	total++;
            } else {
            	currentIndex++;
            	if(currentIndex >= upCount) {
            		currentIndex = 0;
            	}
            	total = 0;
            }
            server = allServers.get(currentIndex);
            
            if (server == null) {
                /* Transient. */
                Thread.yield();
                continue;
            }

            if (server.isAlive() && (server.isReadyToServe())) {
                return (server);
            }

            // Next.
            server = null;
        }

        if (count >= 10) {
            log.warn("No available alive servers after 10 tries from load balancer: "
                    + lb);
        }
        return server;
    }


    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

	@Override
	public void initWithNiwsConfig(IClientConfig arg0) {
		
	}
}
