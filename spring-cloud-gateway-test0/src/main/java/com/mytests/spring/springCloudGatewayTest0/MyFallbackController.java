package com.mytests.spring.springCloudGatewayTest0;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * *
 * <p>Created by irina on 6/2/2022.</p>
 * <p>Project: spring-cloud-gateway-test0</p>
 * *
 */
@RestController
public class MyFallbackController {

    @RequestMapping("/fallback")
    public Mono<String> fallback() {

        return Mono.just("alas it's only me");
    }

    @RequestMapping("/redirected")
    public Mono<String> redirect() {

        return Mono.just("redirected here");
    }

    @GetMapping("/redirectedWithPathvar/{pv}")
    public String rediredtedWithPathvar(@PathVariable("pv") String pv) {
        return "redirected with " + pv;
    }
}
