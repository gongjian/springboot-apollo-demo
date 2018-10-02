package org.james.apollo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class RedisConfig {
  private static final Logger logger = LoggerFactory.getLogger(RedisConfig.class);

  @Autowired
  private RedisProperties redisProperties;

  /*@Bean
  @RefreshScope
  public JedisPool getJedisPool() {
    JedisPoolConfig config = getRedisConfig();
    JedisPool pool = new JedisPool(config, redisProperties.getHostName(), redisProperties.getPort(),
        redisProperties.getTimeout(), redisProperties.getPassword());
    return pool;
  }*/

  @Bean
  @RefreshScope
  public JedisConnectionFactory redisConnectionFactory() {
    JedisConnectionFactory factory = new JedisConnectionFactory();
    factory.setHostName(redisProperties.getHostName());
    factory.setPort(redisProperties.getPort());
    factory.setPassword(redisProperties.getPassword());
    factory.setTimeout(redisProperties.getTimeout());

    logger.info("initialize JedisConnectionFactory: " + factory.hashCode());
    return factory;
  }

  @Bean
  @RefreshScope
  public StringRedisTemplate stringRedisTemplate() {
    StringRedisTemplate template = new StringRedisTemplate();
    template.setConnectionFactory(redisConnectionFactory());

    logger.info("initialize StringRedisTemplate: " + template.hashCode());
    return template;
  }

}
