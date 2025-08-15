package com.example.demo.Hello;

import org.springframework.web.bind.annotation.GetMapping;

public class HelloController {
  @GetMapping("/public/hello")
  public String hello() {
    return "Hello world!";
  }
}
