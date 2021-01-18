 package cn.isc.iscResourceServer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class sampleController {

     private static final Logger log = LoggerFactory.getLogger(sampleController.class);
     
     @GetMapping(value="/hello/{name}")
     public String hello(@PathVariable String name) {
         log.info("hello");
         return "hello :" + name;
     }
     
     @GetMapping(value="/api/home")
     public String apitoken() {
         log.info("welcome to home land!!");
         return "welcome to home land!!";
     }
}
