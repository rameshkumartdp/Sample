package com.sears.services.commercial.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;

@SpringBootApplication
//@EnableDiscoveryClient
//@RestController
public class ServiceClient {
    /*@Inject
    private RestTemplate restTemplate;
*/
  /*  @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }*/
    public static void mainTest(String[] args) {
        SpringApplication.run(ServiceClient.class, args);
    }
    public void run(String... string) throws Exception {
//        ResponseEntity<ServiceResponse> response = this.restTemplate.getForEntity("http://commercial/commercial/search?q=*:*", ServiceResponse.class);
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + response);
    }
}
