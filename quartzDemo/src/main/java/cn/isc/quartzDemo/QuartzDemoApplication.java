package cn.isc.quartzDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuartzDemoApplication {

	public static void main(String[] args) {
	    try {
	        SpringApplication.run(QuartzDemoApplication.class, args);
        } catch (Exception e) {
            // TODO: handle exception
        }
		
	}

}
