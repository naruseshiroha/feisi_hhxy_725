package com.example.service;

import com.example.entity.Ebook;
import com.example.req.EbookReq;
import com.example.resp.EbookResp;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 电子书 服务类
 * </p>
 *
 * @author lixun
 * @since 2022-02-23
 */
public interface IEbookService extends IService<Ebook> {
    List<EbookResp> findAllEbook(EbookReq ebookReq);

    boolean save(EbookResp ebookResp);

    boolean deleteById(Long id);

}
