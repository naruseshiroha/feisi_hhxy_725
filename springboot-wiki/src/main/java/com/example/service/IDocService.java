package com.example.service;

import com.example.entity.Doc;
import com.example.resp.DocResp;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 文档 服务类
 * </p>
 *
 * @author Administrator
 * @since 2022-03-29
 */
public interface IDocService extends IService<Doc> {

    List<DocResp> all(Long ebookId);

    List<DocResp> all();

    String findContent(Long id);

    boolean save(DocResp docResp);

    boolean deleteById(Long id);

    boolean delete(List<String> ids);

    void increaseViewCount(Long id);

    void increaseVetoCount(Long id);

    void updateEbookInfo();
}
