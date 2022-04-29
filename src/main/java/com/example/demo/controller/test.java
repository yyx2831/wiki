package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class test {
    // @GetMapping("/test")
    // @PostMapping("/test")
    // @PutMapping("/test")
    // @DeleteMapping("/test")
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String hello() {
        return "hello";
    }
}
