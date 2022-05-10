package com.example.service.impl;

import com.example.entity.Category;
import com.example.mapper.CategoryMapper;
import com.example.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import com.example.req.CategoryReq;
import com.example.resp.CategoryResp;
import com.example.utils.SnowFlake;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import org.springframework.beans.BeanUtils;

/**
 * <p>
 * 分类 服务实现类
 * </p>
 *
 * @author Administrator
 * @since 2022-03-28
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Resource
    private SnowFlake snowFlake;

    @Override
    public List<CategoryResp> all() {
        LambdaQueryWrapper<Category> lqw = new LambdaQueryWrapper<>();
        lqw.orderByAsc(Category::getSort);
        List<CategoryResp> categoryResps = baseMapper.selectList(lqw).stream()
                .map(category -> {
                    CategoryResp categoryResp = new CategoryResp();
                    BeanUtils.copyProperties(category, categoryResp);
                    return categoryResp;
                }).collect(Collectors.toList());

        return categoryResps;
    }

    public List<CategoryResp> findCategory(CategoryReq categoryReq) {
        LambdaQueryWrapper<Category> lqw = new LambdaQueryWrapper<>();
        lqw.eq(!StringUtils.isEmpty(categoryReq.getName()), Category::getName, categoryReq.getName())
                .eq(!ObjectUtils.isNull(categoryReq.getSort()), Category::getSort, categoryReq.getSort());

        List<CategoryResp> categoryResps = baseMapper.selectList(lqw).stream().map(category -> {
            CategoryResp categoryResp = new CategoryResp();
            BeanUtils.copyProperties(category, categoryResp);
            return categoryResp;
        }).collect(Collectors.toList());

        return categoryResps;
    }

    public boolean save(CategoryResp categoryResp) {
        Category category = new Category();
        boolean flag = false;
        BeanUtils.copyProperties(categoryResp, category);
        if (ObjectUtils.isEmpty(categoryResp.getId())) {
            // 新增
            category.setId(snowFlake.nextId());
            flag = baseMapper.insert(category) > 0 ? true : false;
        } else {
            // 更新
            flag = baseMapper.updateById(category) > 0 ? true : false;
        }
        return flag;
    }

    public boolean deleteById(Long id) {
        return baseMapper.deleteById(id) > 0;
    }

}
