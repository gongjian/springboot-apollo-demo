package org.james.apollo.refresh;

import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.james.apollo.config.RedisProperties;
import org.james.apollo.config.SampleConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.scope.refresh.RefreshScope;
import org.springframework.stereotype.Component;

@Component
public class ApolloRefreshConfig {

  private static final Logger logger = LoggerFactory.getLogger(ApolloRefreshConfig.class);

  @Autowired
  private SampleConfig sampleConfig;

  @Autowired
  private RedisProperties redisProperties;

  @Autowired
  private RefreshScope refreshScope;

  @ApolloConfigChangeListener
  public void onChange(ConfigChangeEvent changeEvent) {
    boolean keyChanged = false;
    for (String changedKey : changeEvent.changedKeys()) {
      logger.info("changedKey: {}", changedKey);
      if (changedKey.startsWith("apollo.demo") || changedKey.startsWith("redis")) {
        keyChanged = true;
        break;
      }
    }

    if (keyChanged) {
      // logger.info("before refresh {}", sampleConfig.toString());
      logger.info("before refresh {}", redisProperties.toString());
      refreshScope.refresh("sampleConfig");
      refreshScope.refresh("redisProperties");
      refreshScope.refresh("redisConnectionFactory");
      refreshScope.refresh("stringRedisTemplate");
      // logger.info("after refresh {}", sampleConfig.toString());
      logger.info("after refresh {}", redisProperties.toString());
    }
  }

}
