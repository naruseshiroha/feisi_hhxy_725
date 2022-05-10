package com.example.service;

import com.example.entity.Content;
import com.example.req.ContentReq;
import com.example.resp.ContentResp;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 文档内容 服务类
 * </p>
 *
 * @author Administrator
 * @since 2022-03-29
 */
public interface IContentService extends IService<Content> {
    // List<ContentResp> all();

    List<ContentResp> findContent(ContentReq contentReq);

    ContentResp selectById(Long id);

    boolean save(ContentResp contentResp);

    boolean deleteById(Long id);
}
