package hanghae99.reboot.review.common.cache.redis.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {

    private final static String REDIS_HOST_PREFIX = "redis://";

    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private String redisPort;

    @Bean
    public RedissonClient redissonClient() {
        RedissonClient redisson = null;
        Config config = new Config();
        config.useSingleServer().setAddress(REDIS_HOST_PREFIX + redisHost + ":" + redisPort);
        redisson = Redisson.create(config);
        return redisson;
    }

}
