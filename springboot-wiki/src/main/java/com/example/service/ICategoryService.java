package com.example.service;

import com.example.entity.Category;
import com.example.req.CategoryReq;
import com.example.resp.CategoryResp;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 分类 服务类
 * </p>
 *
 * @author Administrator
 * @since 2022-03-28
 */
public interface ICategoryService extends IService<Category> {
    List<CategoryResp> all();

    List<CategoryResp> findCategory(CategoryReq categoryReq);

    boolean save(CategoryResp categoryResp);

    boolean deleteById(Long id);
}
