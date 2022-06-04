package com.mytests.spring.springCloudGatewayTest0;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component @ConfigurationProperties("my.server")
class UriConfiguration {
  
  String url = "http://localhost:8888";

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}