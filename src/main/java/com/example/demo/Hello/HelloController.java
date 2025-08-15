package com.example.demo.Hello;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {
  @GetMapping("/public/hello")
  public String hello() {
    return "Hello world!";
  }

  @GetMapping("/hello")
  public String getMethodName(@AuthenticationPrincipal OAuth2User principal) {
      return "Hello, " + principal.getAttribute("name") + "!";
  }
  
}
