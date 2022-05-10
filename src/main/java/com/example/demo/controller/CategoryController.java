package com.example.demo.controller;

import com.example.demo.req.CategoryQueryReq;
import com.example.demo.req.CategorySaveReq;
import com.example.demo.resp.CategoryQueryResp;
import com.example.demo.resp.CommonResp;
import com.example.demo.resp.PageResp;
import com.example.demo.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping("/all") // 获取全部分类
    public CommonResp all() { // 返回类型为CommonResp
        CommonResp<List<CategoryQueryResp>> resp = new CommonResp<>(); // 创建CommonResp对象
        List<CategoryQueryResp> list = categoryService.all(); // 调用service层方法
        resp.setContent(list); // 设置返回内容
        return resp;
    }

    @GetMapping("/list")
    public CommonResp list(@Valid CategoryQueryReq req) {
        CommonResp<PageResp<CategoryQueryResp>> resp = new CommonResp<>();
        PageResp<CategoryQueryResp> list = categoryService.list(req);
        resp.setContent(list);
        return resp;
    }

    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody CategorySaveReq req) {
        CommonResp resp = new CommonResp<>();
        categoryService.save(req);
        return resp;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id) {
        CommonResp resp = new CommonResp<>();
        categoryService.delete(id);
        return resp;
    }
}
