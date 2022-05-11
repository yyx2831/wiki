package com.example.demo.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.example.demo.domain.Ebook;
import com.example.demo.domain.EbookExample;
import com.example.demo.mapper.EbookMapper;
import com.example.demo.req.EbookQueryReq;
import com.example.demo.req.EbookSaveReq;
import com.example.demo.resp.EbookQueryResp;
import com.example.demo.resp.PageResp;
import com.example.demo.util.CopyUtil;
import com.example.demo.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {

    private static final Logger LOG = LoggerFactory.getLogger(EbookService.class);

    @Resource
    private EbookMapper ebookMapper;

    @Resource
    private SnowFlake snowFlake;

    public PageResp<EbookQueryResp> list(EbookQueryReq req) {
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        if (!ObjectUtils.isEmpty(req.getName())) { // name有值就查询name
            criteria.andNameLike("%" + req.getName() + "%"); // 模糊查询
        }
        if (!ObjectUtils.isEmpty(req.getCategoryId2())) { // categoryId2有值就查询categoryId2
            criteria.andCategory2IdEqualTo(req.getCategoryId2()); // 精确查询
        }
        PageHelper.startPage(req.getPage(), req.getSize()); // 分页
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);

        PageInfo<Ebook> pageInfo = new PageInfo<>(ebookList); // 获取分页信息
        LOG.info("总行数：{}", pageInfo.getTotal()); // 总行数
        LOG.info("总页数：{}", pageInfo.getPages()); // 总页数

        // List<EbookResp> respList = new ArrayList<>();
        // for (Ebook ebook : ebookList) {
        //     // EbookResp ebookResp = new EbookResp();
        //     // BeanUtils.copyProperties(ebook, ebookResp);
        //     // 对象复制
        //     EbookResp ebookResp = CopyUtil.copy(ebook, EbookResp.class);
        //
        //     respList.add(ebookResp);
        // }

        // 列表复制
        List<EbookQueryResp> list = CopyUtil.copyList(ebookList, EbookQueryResp.class);

        PageResp<EbookQueryResp> pageResp = new PageResp();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);

        return pageResp;
    }

    /**
     * 保存
     */
    public void save(EbookSaveReq req) {
        LOG.info("------------- EbookService.save 开始 -------------");
        LOG.info("请求参数：{}", req);
        Ebook ebook = CopyUtil.copy(req, Ebook.class);
        if (ObjectUtils.isEmpty(req.getId())) {
            // 新增
            ebook.setId(snowFlake.nextId()); // 设置id
            // doc_count不能为空
            ebook.setDocCount(1);
            // view_count不能为空
            ebook.setViewCount(1);
            // vote_count不能为空
            ebook.setVoteCount(1);
            ebookMapper.insert(ebook); // 插入
        } else {
            // 更新
            ebookMapper.updateByPrimaryKey(ebook);
        }
    }

    public void delete(Long id) {
        ebookMapper.deleteByPrimaryKey(id);
    }
}

