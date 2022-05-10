package com.example.demo.controller;

import com.example.demo.req.EbookQueryReq;
import com.example.demo.req.EbookSaveReq;
import com.example.demo.resp.CommonResp;
import com.example.demo.resp.EbookQueryResp;
import com.example.demo.resp.PageResp;
import com.example.demo.service.EbookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    public CommonResp list(@Valid EbookQueryReq req) {  // @Valid 表示校验请求参数 EbookQueryReq
        CommonResp<PageResp<EbookQueryResp>> resp = new CommonResp<>(); // 创建返回对象
        PageResp<EbookQueryResp> list = ebookService.list(req);         // 调用服务
        resp.setContent(list);                                        // 设置返回内容
        return resp;
    }

    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody EbookSaveReq req) { // @RequestBody 表示请求参数是一个json
        CommonResp resp = new CommonResp<>(); // 创建返回对象
        ebookService.save(req);              // 调用服务
        return resp;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id) { // @PathVariable 表示请求参数是一个路径变量
        CommonResp resp = new CommonResp<>(); // 创建返回对象
        ebookService.delete(id);            // 调用服务
        return resp; // 返回结果
    }
}
