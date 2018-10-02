package org.james.apollo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
@RefreshScope
public class RedisController {

  @Autowired
  private StringRedisTemplate stringRedisTemplate;

  @RequestMapping("/{key}/{value}")
  public String setVal(@PathVariable String key, @PathVariable String value){
    stringRedisTemplate.opsForValue().set(key, value);

    return value;
  }

}
