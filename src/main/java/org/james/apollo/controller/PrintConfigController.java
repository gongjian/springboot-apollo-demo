package org.james.apollo.controller;

import org.james.apollo.config.SampleConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrintConfigController {

  @Autowired
  private SampleConfig sampleConfig;

  @RequestMapping("/print")
  public String print() {
    return sampleConfig.toString();
  }

}
