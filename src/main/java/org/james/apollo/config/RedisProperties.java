package org.james.apollo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "redis")
@RefreshScope
public class RedisProperties {

  private String hostName;

  private int port;

  private String password;

  private int timeout;

  public String getHostName() {
    return hostName;
  }

  public void setHostName(String hostName) {
    this.hostName = hostName;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getTimeout() {
    return timeout;
  }

  public void setTimeout(int timeout) {
    this.timeout = timeout;
  }

  @Override
  public String toString() {
    return "RedisProperties{" +
        "hostName='" + hostName + '\'' +
        ", port=" + port +
        ", password='" + password + '\'' +
        ", timeout=" + timeout +
        '}';
  }
}
