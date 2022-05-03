package com.example.demo.controller;

import com.example.demo.domain.Test;
import com.example.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class test {

//    @Value("${name}")
//    private String name;

    @Resource
    private TestService testService;

    // @GetMapping("/test")
    // @PostMapping("/test")
    // @PutMapping("/test")
    // @DeleteMapping("/test")
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String hello() {
        return "hello";
    }

    @PostMapping("/test/post")
    public String hello2(Map<String, Object> map) {
        return map.toString();
    }
    // 来一个map
    // 接受的json格式为:
    // {
    //     "name": "zhangsan",
    //     "age": 18
    // }
    @PostMapping("/test/post2")
    public String hello3(Map<String, Object> map) {
        return map.toString();
    }

//    @GetMapping("/test/list")
//    public List<Test> list() {
//        return testService.list();
//    }

    @Autowired
    public void setTestService(TestService testService) {
        this.testService = testService;
    }
}
